package com.atguigu.java1;

/**
 * @author shkstart
 * @create 2020 上午 9:22
 */
public class ClassLoaderTest {
    public static void main(String[] args) {

        System.out.println("ClassLoaderTest.class-> " + ClassLoaderTest.class);
        System.out.println("ClassLoaderTest.toString()-> " + ClassLoaderTest.class.toString());


        /**
         *  public String toString() {
         *      return getClass().getName() + "@" + Integer.toHexString(hashCode());
         *  }
         */
        //获取系统类加载器
        /**
         * `ClassLoader.getSystemClassLoader()` 通常也返回 系统类加载器/应用程序类加载器/AppClassLoader 的实例
         */
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println("系统类加载器-> " + systemClassLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        //获取其上层：扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println("扩展类加载器 extClassLoader-> " + extClassLoader);//sun.misc.Launcher$ExtClassLoader@1540e19d

        //获取其上层：获取不到引导类加载器
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);//null

        //对于用户自定义类来说：默认使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println("系统类加载器-> " + classLoader);//sun.misc.Launcher$AppClassLoader@18b4aac2

        //String类使用引导类加载器进行加载的。---> Java的核心类库都是使用引导类加载器进行加载的。
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);//null


    }
}
