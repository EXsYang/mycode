package com.atguigu.java1;

import org.junit.Test;

import java.util.Random;

/**
 * @author shkstart
 * @create 2020-09-14 17:30
 * 关于类的被动使用，即不会进行类的初始化操作，即不会调用<clinit>()
 *
 *  * 3. 引用常量不会触发此类或接口的初始化。因为常量在链接阶段就已经被显式赋值了。
 *  * 4. 调用ClassLoader类的loadClass()方法加载一个类，并不是对类的主动使用，不会导致类的初始化。
 *
 *  说明：没有初始化的类，不意味着没有加载！
 */
public class PassiveUse2 {
    @Test
    public void test1(){
//        System.out.println(Person.NUM);
        System.out.println(Person.NUM1);
    }

    @Test
    public void test2(){
//        System.out.println(SerialA.ID);
        System.out.println(SerialA.ID1);
    }

    @Test
    public void test3(){
        //测试:classLoader.loadClass()方法并不会导致类的初始化。只会执行解析阶段，链接和初始化阶段都没有执行
        // 这种方式属于类的被动使用。
        // 即属于测试类的被动使用的第4点。
        // 4. 调用ClassLoader类的loadClass()方法加载一个类，并不是对类的主动使用，不会导致类的初始化。
        // 类的被动使用，即不会进行类的初始化操作，即不会调用<clinit>()
        // 说明：没有初始化的类，不意味着没有加载！
        //
        /*
        在loadClass方法中，默认第二个参数resolve是false，
        即loadClass(name, false);。
        protected Class<?> loadClass(String name, boolean resolve)
        这意味着在调用loadClass()方法时默认不会执行下面的resolveClass(c);方法，不会立即触发链接阶段中的解析。只执行类的加载阶段。
        if (resolve) {
            resolveClass(c);
        }

        resolveClass(c): 当resolve参数为true时，resolveClass(c)方法会被调用，这个方法会完成类的链接阶段中的解析（resolution）。
        默认情况下，调用loadClass(name)只会进行类的加载和一部分验证，不会进行完整的解析和初始化。只有在需要时（例如访问静态字段或调用静态方法时）才会进行解析和初始化。
         */
        //
        // ClassLoader classLoader = ActiveUse3.class.getClassLoader();
        // classLoader.loadClass("com.atguigu.java1.Order");


        try {
            Class clazz = ClassLoader.getSystemClassLoader().loadClass("com.atguigu.java1.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

class Person{
    static{
        System.out.println("Person类的初始化");
    }
    public static final int NUM = 1;//在链接过程的准备环节就被赋值为1了。
    public static final int NUM1 = new Random().nextInt(10);//此时的赋值操作需要在<clinit>()中执行
}

interface SerialA{
    public static final Thread t = new Thread() {
        {
            System.out.println("SerialA的初始化");
        }
    };

    int ID = 1;
    int ID1 = new Random().nextInt(10);//此时的赋值操作需要在<clinit>()中执行
    // 只有到了Initialization初始化阶段,才会真正开始执行类中定义的Java代码。
    // 因为ID1的显示赋值是需要new Random().nextInt(10);代码执行的，而这段代码的执行
    // 是在<clinit>()中进行的，所以需要调用<clinit>()方法
}

