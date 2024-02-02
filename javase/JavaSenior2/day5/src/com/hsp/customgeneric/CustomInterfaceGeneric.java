package com.hsp.customgeneric;

/**
 * @author yangda
 * @description:
 * @create 2022-11-19-14:45
 */
public class CustomInterfaceGeneric {
    public static void main(String[] args) {

    }
}
/*
泛型接口使用的说明
1.接口中，静态成员也不能使用泛型
2，泛型接口的类型，在继承接口或者实现接口时确定
3.没有指定类型，默认为Object

 */




//1，继承接口
//当我们去实现IA接口时，因为IA接口在继承Iusb 接口时，指定了T 为String，U 为Double
//因此，在实现接口 Iusb 方法时，使用String替换T，是Double替换U
class AA implements IA{

    @Override
    public void show(String s,Double d) {

    }

    @Override
    public void s() {

    }
}

interface IA extends Iusb<String,Double>{

}
interface Iusb<T,U>{
    int a = 3;
//    T t;
//    static void m(T t){
//    }
    void show(T t,U u);
    void s();
    //在jdk8 中，可以在接口中，使用默认方法，也可以使用泛型
    default T method(U u){
        return null;
    }
}

// 2.实现接口
// 直接指定泛型接口的类型
// 给T指定Integer，给 U指定Float
// 所以，当我们实现Iusb接口的方法时，会使用Integer替换T,用Float替换U
class BB implements Iusb<Integer,Float>{

    @Override
    public void show(Integer integer, Float aFloat) {

    }

    @Override
    public void s() {

    }
}