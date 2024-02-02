package com.atguigu.set_;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author yangda
 * @description:
 * @create 2022-11-15-18:25
 */
public class HashSetExer1 {
    public static void main(String[] args) {

        HashSet set = new HashSet();

//        Employee1 e1 = new Employee1("yangda", 353, new MyDate(1998, 3, 27));
//        Employee1 h = new Employee1("hsp", 33, new MyDate(1998, 5, 27));
//        Employee1 e2 = new Employee1("yangda", 33, new MyDate(1998, 3, 27));
//        Employee1 e3 = new Employee1("yangda", 33, new MyDate(1998, 4, 27));
//
//        set.add(e1);
//        set.add(e2);
//        set.add(e3);
//        set.add(h);
//
//        System.out.println(set);

        MyDate d = new MyDate(2, 2, 2);
        MyDate s = new MyDate(2, 2, 2);
        MyDate t = new MyDate(2, 2, 2);

        System.out.println("d:" + d);// 单独打开时：dd:1735600054,结果时第二组，与猜测一致

//        System.out.println("s:" + s);//
//        System.out.println("t:" + t);// 关闭后是第三组结果，打开后是第二组结果，打开后与自己的结论一致


            d = s; //修改dd的地址值
            s = t;  //修改s的地址值   打印出来的结果，后两个相同   猜测：在修改前没调用时，
                                                                    // jvm 没有单独给d分配地址值，和s公用同一个

        System.out.println("d:" + d);//1735600054  21685669   1735600054
        System.out.println("s:" + s);//  21685669    2133927002 21685669
        System.out.println("t:" + t);//  2133927002  2133927002 21685669


//        for (int i = 0; i < 199999999; i++) {
//            MyDate dd = new MyDate(2, 2, 2);
//            MyDate s = new MyDate(2, 2, 2);
//            MyDate t = new MyDate(2, 2, 2);
//
//
//            System.out.println("dd:" + dd);//
//            System.out.println("s:" + s);//
//            System.out.println("t:" + t);//
//        }


    }
}
class Employee1{
    private String name;
    private double sal;
    private MyDate birthday;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee1 employee1 = (Employee1) o;
        return Objects.equals(name, employee1.name) &&
                Objects.equals(birthday, employee1.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }

    public Employee1(String name, double sal, MyDate birthday) {
        this.name = name;
        this.sal = sal;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Emp" +"\t"+
                  name + "\t" +
                 + sal +"\t"+
                birthday
                ;
    }
}

class MyDate{

    int year;
    int month;
    int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDate myDate = (MyDate) o;
        return year == myDate.year &&
                month == myDate.month &&
                day == myDate.day;
    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(year, month, day);
//    }

    @Override
    public String toString() {
//        return "MyD" + "\t"
//                + year +"\t"
//                + month +"\t"
//                + day
//                ;
        return String.valueOf(hashCode());
    }
}