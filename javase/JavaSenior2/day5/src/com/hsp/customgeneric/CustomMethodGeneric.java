package com.hsp.customgeneric;

/**
 * @author yangda
 * @description:自定义泛型方法
 * @create 2022-11-19-15:51
 */
public class CustomMethodGeneric {
    public static void main(String[] args) {

        Car car = new Car();
        car.  fly("宝马",100);//当调用方法时，传入参数，编译器，就会确定类型

        Fish.hello1("ddd",33);

        A<Integer, String> a = new A<>();

        a.hello1("yangda",33);
        a.hello1("yangda","dd");//静态泛型方法中所使用的泛型，都是在方法调用时确定的，就算是和泛型类的泛型使用了相同的标识符也是如此
//        class A<String,Integer>{
//        public static <String,Integer> void hello1(String s,Integer i){

//        a.show();//类型和创建对象时确定的类型是一样的  <Integer, String>


        System.out.println("A.hello1");
        A.hello1("S","o");//直接通过类名调用静态泛型方法，里面相当于Object,调用时确定类型，编译器进行类型推断
        A.hello1("S",5);//直接通过类名调用静态泛型方法，里面相当于Object,调用时确定类型，编译器进行类型推断

    }
}

//泛型方法，可以定义在普通类中，也可以定义在泛型类中
class Car{//普通类

    public void run(){//普通方法

    }
    //说明：泛型方法
    //1. <T,R> 就是泛型
    //2. 是提供给 fly使用的
    public <T,R> void fly(T t,R r){//泛型方法

    }



}



class Fish<T,R>{//泛型类
    public void run(){//普通方法

    }

    public <U,M> void eat(U u,M m){//泛型方法

    }
    //说明：
    //1.下面hi方法不是泛型方法
    //2.是hi方法使用了类声明的 泛型
    public void hi(T t){//使用了泛型类的泛型

    }

//    public static void hi1(T t){//静态方法中不能使用泛型
//'com.hsp.customgeneric.Fish.this' cannot be referenced from a static context
//    static 时，类实例化时，用到的类的泛型还没确定呢
//    }

    //泛型方法，可以使用类声明的泛型，也可以使用自己声明的泛型
    //泛型方法，在方法中出现了泛型的结构，泛型参数与类的泛型参数没有任何关系
    //换句话说，泛型方法所属的的类是不是泛型类都没有关系
    //泛型方法，可以声明为静态的，原因：泛型参数是在调用方法时确定的，并非在实例化类时确定

    public <K> void hello(R r,K k){
        System.out.println(r.getClass());
        System.out.println(k.getClass());
    }



    public static <T,R> void hello1(R r,T k){//调用方法时确定类型


        System.out.println("class Fish<T,R>{//泛型类\n泛型方法与泛型类的泛型相同\npublic static <T,R> void hello1(R r,T k){");
        System.out.println(r.getClass());
        System.out.println(k.getClass());
    }
}


class A<String,Integer>{//这里只是相当于标识符而已，并不是java.lang.String

    public void show(String s,Integer i){
        System.out.println("泛型类 使用了泛型类泛型的方法");
        System.out.println(s.getClass());
        System.out.println(i.getClass());
    }

    public static <String,Integer> void hello1(String s,Integer i){//调用方法时确定类型
        System.out.println("泛型类\n泛型方法与泛型类的泛型相同");
        System.out.println(s.getClass());
        System.out.println(i.getClass());
    }
}
