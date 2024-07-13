package com.atguigu.java;

/**
 * @author shkstart
 * @create 16:03
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        System.out.println(ClassLoaderTest.class.getClassLoader());
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent());
        System.out.println(ClassLoaderTest.class.getClassLoader().getParent().getParent());

        // jdk8中系统类加载器和扩展类加载器都是继承于URLClassLoader
        // static class AppClassLoader extends URLClassLoader {
        // static class ExtClassLoader extends URLClassLoader {

        //
        // jdk9中的ClassLoaders类中可以看到：启动类加载器、平台类加载器、系统类加载器
        // jdk9中启动类加载器、平台类加载器、系统类加载器都继承于BuiltinClassLoader！
        //  private static class BootClassLoader extends BuiltinClassLoader {
        // BuiltinClassLoader继承于SecureClassLoader
        // SecureClassLoader继承于ClassLoader
        // jdk9中没有 URLClassLoader,因此如果是继承于URLClassLoader实现的自定义类加载器
        // 在jdk9中会因为不兼容而报错，而如果是继承于ClassLoader实现的自定义类加载器则没有问题



        //获取系统类加载器
        System.out.println(ClassLoader.getSystemClassLoader());
        //获取平台类加载器
        System.out.println(ClassLoader.getPlatformClassLoader());
        //获取类的加载器的名称
        System.out.println(ClassLoaderTest.class.getClassLoader().getName());
    }
}
