package com.atguigu.exer;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author yangda
 * @create 2022-08-18-7:36
 */
public class HashSetExer {
    private String name;
    private double sal;
    private MyDate brithday;

    public HashSetExer(String name, double sal, MyDate brithday) {
        this.name = name;
        this.sal = sal;
        this.brithday = brithday;
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

    public MyDate getBrithday() {
        return brithday;
    }

    public void setBrithday(MyDate brithday) {
        this.brithday = brithday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HashSetExer that = (HashSetExer) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(brithday, that.brithday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, brithday);
    }

    @Override
    public String toString() {
        return "HashSetExer{" +
                "name='" + name + '\'' +
                ", sal=" + sal +
                ", brithday=" + brithday +
                '}';
    }
}

class Test1{
    public static void main(String[] args) {

        MyDate myDate = new MyDate(1998, 3, 27);
        MyDate myDate1 = new MyDate(2005, 1, 1);
        MyDate myDate2 = new MyDate(1998, 3, 27);
        HashSetExer yd = new HashSetExer("yd", 2000, myDate);
        HashSetExer yxt = new HashSetExer("yxt", 1000, myDate1);
        HashSetExer yd1 = new HashSetExer("yd", 3000, myDate2);

        HashSet hashSet = new HashSet();
        hashSet.add(yd);
        hashSet.add(yxt);
        hashSet.add(yd1);

    }
}