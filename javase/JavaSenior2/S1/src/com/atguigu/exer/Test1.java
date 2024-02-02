package com.atguigu.exer;

/**
 * @author yangda
 * @create 2022-10-28-6:52
 */
public class Test1 {
    public static void main(String[] args) {
        B b = new B();//默认的，b对象继承了class A的所有的属性
        //System.out.println(b.x);//此时的x=10
        b.g();//此时的x是12
        b.f();
        A a = new A();
        a.hh();
        //运行结果是：12，12，13，10
      Integer aa = 2;
        System.out.println("~-2：" + ~-2);

        int k=3;
        switch (k){
            case 3:
                System.out.println("33");
                break;
            case 4:
                System.out.println("4");
                break;
            case 5:
                System.out.println("5");
                break;
        }



    }
}
class A {
    int x = 10;
    void f() {
        x = x + 1;
        System.out.println(x);
    }
    void hh() {
        System.out.println(x);
    }
}
class B extends A {
    int y = 5;
    void g() {
        x = x + 2;
        System.out.println(x);
        System.out.println(super.x);//子类中用的super.
    }
}