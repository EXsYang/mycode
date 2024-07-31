package com.atguigu.java1;

import org.junit.Test;

import java.util.Random;

/**
 * @author shkstart
 * @create 2020-09-14 17:00
 * 测试类的主动使用：意味着会调用类的<clinit>()，即执行了类的初始化阶段
 * <p>
 * 4. 当使用java.lang.reflect包中的方法反射类的方法时。比如：Class.forName("com.atguigu.java.Test")
 *
 * 5. 当初始化子类时，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。
 * 6. 如果一个接口定义了default方法，那么直接实现或者间接实现该接口的类的初始化，该接口要在其之前被初始化。
 * 7. 当虚拟机启动时，用户需要指定一个要执行的主类（包含main()方法的那个类），虚拟机会先初始化这个主类。
 * 8. 当初次调用 MethodHandle 实例时，初始化该 MethodHandle 指向的方法所在的类。
 * （涉及解析REF_getStatic、REF_putStatic、REF_invokeStatic方法句柄对应的类）
 * <p>
 * <p>
 * 针对5，补充说明：
 * 当Java虚拟机初始化一个类时，要求它的所有父类都已经被初始化，但是这条规则并不适用于接口。
 * >在初始化一个类时，并不会先初始化它所实现的接口
 * >在初始化一个接口时，并不会先初始化它的父接口
 * 因此，一个父接口并不会因为它的子接口或者实现类的初始化而初始化。只有当程序首次使用特定接口的静态字段时，
 * 才会导致该接口的初始化。
 */
public class ActiveUse3 {
    static{
        System.out.println("ActiveUse3的初始化过程");
    }
    @Test
    public void test1() throws ClassNotFoundException {
        // 测试1:
        // 4. 当使用java.lang.reflect包中的方法反射类的方法时。比如：Class.forName("com.atguigu.java.Test")
        try {
            Class clazz = Class.forName("com.atguigu.java1.Order");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //测试2:classLoader.loadClass()方法并不会导致类的初始化。只会执行解析阶段，链接和初始化阶段都没有执行
        // 这种方式属于类的被动使用。
        // 即属于测试类的被动使用的第4点。
        // 4. 调用ClassLoader类的loadClass()方法加载一个类，并不是对类的主动使用，不会导致类的初始化。
        // 类的被动使用，即不会进行类的初始化操作，即不会调用<clinit>()
        // 说明：没有初始化的类，不意味着没有加载！
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
        ClassLoader classLoader = ActiveUse3.class.getClassLoader();
        classLoader.loadClass("com.atguigu.java1.Order");


    }

    @Test
    public void test2() {
        System.out.println(Son.num);
    }

    @Test
    public void test3(){
        System.out.println(CompareC.NUM1);
    }

    @Test
    public void test4() {
        System.out.println(Son.num);
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}


class Father {
    static {
        System.out.println("Father类的初始化过程");
    }
}

class Son extends Father implements CompareB{
    static {
        System.out.println("Son类的初始化过程");
    }

    public static int num = 1;
}

interface CompareB {
    public static final Thread t = new Thread() {
        {
            System.out.println("CompareB的初始化");
        }
    };
    public default void method1(){
        System.out.println("你好！");
    }

}

interface CompareC extends CompareB {
    public static final Thread t = new Thread() {
        {
            System.out.println("CompareC的初始化");
        }
    };

    public static final int NUM1 = new Random().nextInt();
}
