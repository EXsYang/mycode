package com.hsp.generic;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * @author yangda
 * @description:
 * @create 2022-11-18-19:41
 */
public class GenericExer {
    public static void main(String[] args) {
        //创建三个MyDate类对象放入ArrayList(用泛型）
//        对集合进行排序并输出
        MyDate a = new MyDate(2022, 3, 27);
        MyDate b = new MyDate(2025, 4, 3);
        MyDate c = new MyDate(2025, 4, 7);


        ArrayList<Employee> myDates = new ArrayList<>();
        myDates.add(new Employee("tom", 1000, a));
        myDates.add(new Employee("hsp", 1444, b));
        myDates.add(new Employee("hsp", 1555, c));

        System.out.println(myDates);
        myDates.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {

                if (o1.getName().compareTo(o2.getName()) != 0) {
                    return o1.getName().compareTo(o2.getName());
                } else {
                    return o1.getBirthday().compareTo(o2.getBirthday());
                }
            }
        });

        System.out.println("排序后：");
        System.out.println(myDates);
    }


}

class Employee {
    private String name;
    private double sal;
    private MyDate birthday;


    public Employee(String name, double sal, MyDate birthday) {
        this.name = name;
        this.sal = sal;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "\nEmployee{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", birthday=" + birthday +
                '}';
    }
}

class MyDate implements Comparable<MyDate> {
    private int year;
    private int month;
    private int day;


    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(MyDate o) {

        int y = year - o.getYear();
        if (y != 0) {
            return y;//排序方式没问题，当前对象大，返回正数；当前对象小，返回负数
        }
        int m = month - o.getMonth();
        if (m != 0) {
            return m;
        }
        return day - o.getDay();
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }
}