package Test_.java;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @author yangda
 * @description:
 * @create 2022-11-23-17:50
 */
public class A {
    public static void main(String[] args) {
        B.loop = true;
        System.out.println(B.loop);//true

        Vector vector = new Vector();//创建了长度为10的数组
        vector.add("a");
        vector.add("a");
        System.out.println(vector.size());//2,真实的元素个数
        vector.remove("a");
        System.out.println(vector.size());
        System.out.println("****************************************");
        Vector vector1 = new Vector(0);//创建了长度为0的数组

        vector1.add("a");//每添加一个，扩容一次数组，长度1
        vector1.add("a");//每添加一个，扩容一次数组，长度2
        System.out.println(vector1.size());
        vector1.remove("a");
        System.out.println(vector1.size());


        System.out.println("*************");

        ArrayList list = new ArrayList();//空的
        list.add("b");//创建了长度为10的数组
        list.add("b");
        System.out.println(list.size());
        list.remove("b");
        System.out.println(list.size());//长度随着数组改变
        System.out.println(list);


    }
}
class B {
    static boolean loop = false;
    public static void main(String[] args) {
        System.out.println(B.loop);//false
    }
}
class C{
    public static void main(String[] args) {
        System.out.println(B.loop);//false
    }
}
