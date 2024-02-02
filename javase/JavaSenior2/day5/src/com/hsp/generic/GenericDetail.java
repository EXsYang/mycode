package com.hsp.generic;

import java.util.ArrayList;

/**
 * @author yangda
 * @description:
 * @create 2022-11-18-19:14
 */
public class GenericDetail {
    public static void main(String[] args) {


        ArrayList<Integer> integers = new ArrayList<Integer>();
//        ArrayList<int> ints = new ArrayList<int>();//Type argument cannot be of primitive type

        ArrayList<A> a = new ArrayList<>();
        a.add(new A());
        a.add(new B());//子类可以放进去

        Pig<A> aPig = new Pig<A>(new A());
        aPig.show();
        Pig<A> bPig = new Pig<A>(new B());
        bPig.show();

        //3,类型推断
        ArrayList<Pig> pigs = new ArrayList<Pig>();
        ArrayList<Pig> pigs1 = new ArrayList<>();

//        4.如果这样写，泛型 默认是 Object
        ArrayList object1 = new ArrayList();
        ArrayList<Object> object2 = new ArrayList<Object>();




    }
}
class A{

}
class B extends A{

}
class Pig<T>{
    T t;
    public Pig(T t) {
        this.t = t;
    }

    public void show(){
        System.out.println(t.getClass());
    }


}