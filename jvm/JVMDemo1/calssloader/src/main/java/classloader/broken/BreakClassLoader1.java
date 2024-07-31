package classloader.broken;//package com.itheima.jvm.chapter02.classloader.broken;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.ProtectionDomain;
import java.util.regex.Matcher;

/**
 * 打破双亲委派机制 - 自定义类加载器
 */

public class BreakClassLoader1 extends ClassLoader {

    private String basePath;
    private final static String FILE_EXT = ".class";

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    private byte[] loadClassData(String name)  {
        try {
            String tempName = name.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
            // 这里是使用的文件输入流读取一个文件流到内存。
            FileInputStream fis = new FileInputStream(basePath + tempName + FILE_EXT);
            try {
                // 这里是使用工具类将文件流转为byte数组
                return IOUtils.toByteArray(fis);
            } finally {
                IOUtils.closeQuietly(fis);
            }

        } catch (Exception e) {
            System.out.println("自定义类加载器加载失败，错误原因：" + e.getMessage());
            return null;
        }
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        // 如果是以java.开头则交给当前类加载器的父加载器(系统类加载器)进行加载。
        // 即如果需要加载A类/B类的父类java.lang.Object类，因为要想加载A类则需要先加载其父类Object类，
        // 因为默认Object是所有类的父类，则需要使用到系统类加载器进行加载。

        // 如果不写下面这个if(name.startsWith("java.")){的判断，
        // 则会报错：
        // 自定义类加载器加载失败，错误原因：D:\lib\java\lang\Object.class (系统找不到指定的路径。)
        // Exception in thread "main" java.lang.NullPointerException

        // 原因是如果不写该判断则会使用自定义类加载器进行加载Object类的.class文件，
        // 但在指定的目录d:/lib下找不到java\lang\Object.class ，因此报错。

        // 或者将java.lang.Object类的.class文件也放在d:\lib目录下才能找到Object类
        // 因为自定义类加载器BreakClassLoader1默认是在lib目录下进行加载的其他类的如Object
        if(name.startsWith("java.")){
            return super.loadClass(name);
        }


        byte[] data = loadClassData(name);
        return defineClass(name, data, 0, data.length);
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
        BreakClassLoader1 classLoader1 = new BreakClassLoader1();
        classLoader1.setBasePath("D:\\lib\\");

        Class<?> clazz1 = classLoader1.loadClass("com.itheima.my.A");

        System.out.println("classLoader1.getParent()==>" + classLoader1.getParent());
        System.out.println("ClassLoader.getSystemClassLoader()==>" + ClassLoader.getSystemClassLoader());

        // 要想修改自定义类加载器的父加载器
        // 需要修改其构造方法
        //  protected ClassLoader() {
        //    在空参构造器默认传入的是系统类加载器
        //    this(checkCreateClassLoader(), getSystemClassLoader());
        //  }

        // 要想修改其默认的父加载器可以在下面这个带参的protected的构造器中，
        // 自己传入一个想要指定的父加载器即可。
        // protected ClassLoader(ClassLoader parent) {
        //  this(checkCreateClassLoader(), parent);
        // }

        // 这个是私有的，是属于在父类ClassLoader中单独使用的
        // 在Java中，子类不能直接调用父类的私有方法。私有方法在父类中是私有的，意味着它们只能在父类的内部被访问，不能被子类或其他类访问。
        // 私有方法：子类不能直接调用父类的私有方法。私有方法只能在声明它的类内部访问。
        // 子类可以通过调用父类的公有或受保护的方法来间接调用父类的私有方法。
        // 如果希望子类能够访问父类的方法，可以将方法的访问修饰符更改为protected。受保护的方法可以在子类中直接访问。
        // private ClassLoader(Void unused, ClassLoader parent) {
        //         this.parent = parent;




        System.out.println();
        BreakClassLoader1 classLoader2 = new BreakClassLoader1();
        classLoader2.setBasePath("D:\\lib\\");

        Class<?> clazz2 = classLoader2.loadClass("com.itheima.my.A");

        System.out.println(clazz1 == clazz2);

        Thread.currentThread().setContextClassLoader(classLoader1);

        System.out.println(Thread.currentThread().getContextClassLoader());

        System.in.read();
     }
}
