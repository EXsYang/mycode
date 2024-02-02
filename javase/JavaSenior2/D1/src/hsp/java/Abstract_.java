package hsp.java;

/**
 * @author yangda
 * @create 2022-11-01-17:55
 */
 public  abstract class Abstract_ {
//    public static void main(String[] args) {//'main(String[])'已经在' hbase .java. abstract_ '中定义了
//
//    }

    public static void main(String[] args) {

    }
}
abstract class A{
    int a = 2;
    void fa(){}//一个普通的方法，这样定义是对的
     void f(){};//Unnecessary semicolon ';' 不必要的分号“;”
    //void h();//缺少方法体，或声明抽象
    abstract void h();//这样定义是对的

    //abstract public void n();
    abstract public void m();
    public abstract void m1();
    public void m2(){

    }
    //default void m3(){//Extension methods can only be used within an interface
   // }
//    abstract public void n(){//Abstract methods cannot have a body
//     }
//    public abstract void n(){//Abstract methods cannot have a body
//     }
}
//public abstract class Employee{//一个.java文件中只能有一个声明为public的类,只能有一个main方法
 //abstract public class Employee{

