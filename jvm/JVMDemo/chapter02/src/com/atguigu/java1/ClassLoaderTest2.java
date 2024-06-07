package com.atguigu.java1;

/**
 * @author shkstart
 * @create 2020 上午 10:59
 */
public class ClassLoaderTest2 {
    public static void main(String[] args) {
        try {
            //1.
            ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
            System.out.println(classLoader);//null
            //2.
            ClassLoader classLoader1 = Thread.currentThread().getContextClassLoader();
            System.out.println(classLoader1);//sun.misc.Launcher$AppClassLoader@18b4aac2





            //3.
            ClassLoader classLoader2 = ClassLoader.getSystemClassLoader();
            System.out.println(classLoader2);//sun.misc.Launcher$AppClassLoader@18b4aac2

            //4.
            ClassLoader classLoader3 = ClassLoader.getSystemClassLoader().getParent();
            System.out.println(classLoader3);//sun.misc.Launcher$ExtClassLoader@1b6d3586

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
