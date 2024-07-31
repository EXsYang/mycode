package com.atguigu.java2;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author shkstart
 * @create 15:20
 * 自定义ClassLoader
 */
public class MyClassLoader extends ClassLoader {
    private String byteCodePath;

    public MyClassLoader(String byteCodePath) {
        this.byteCodePath = byteCodePath;
    }

    public MyClassLoader(ClassLoader parent, String byteCodePath) {
        super(parent);
        this.byteCodePath = byteCodePath;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        try {
            //获取字节码文件的完整路径
            String fileName = byteCodePath + className + ".class";
            //获取一个输入流
            bis = new BufferedInputStream(new FileInputStream(fileName));
            //获取一个输出流
            baos = new ByteArrayOutputStream();
            //具体读入数据并写出的过程
            int len;
            byte[] data = new byte[1024];
            while ((len = bis.read(data)) != -1) {
                baos.write(data, 0, len);
            }
            //获取内存中的完整的字节数组的数据
            byte[] byteCodes = baos.toByteArray();
            //调用defineClass()，将字节数组的数据转换为Class的实例。
            Class clazz = defineClass(null, byteCodes, 0, byteCodes.length);
            // 到这里自定义类的加载器的逻辑就结束了，即不写if (resolve) { resolveClass(c); }这段代码也可以正常完成类的加载工作。
            // 并没有像ClassLoader类源码中的loadClass()方法一样，书写判断是否在类的加载阶段判断是否执行解析操作。
            /**
             * 解析阶段的执行
             * 在Java类加载机制中，解析阶段是类加载过程中的一个重要环节，
             * 它会将类的符号引用转换为直接引用。
             * 尽管在自定义类加载器中没有显式调用resolveClass，
             * 但Java虚拟机会在第一次使用该类时自动触发解析过程。
             *
             * 解析阶段的自动触发
             * 当类的符号引用（如静态字段、静态方法等）被首次使用时，Java虚拟机会确保该类已经被解析。
             * 在这些情况下，Java虚拟机会检查该类是否已经解析。如果没有，则会自动触发解析阶段。
             * 这意味着，即使在自定义类加载器中没有显式调用resolveClass，在实际使用类时，Java虚拟机会确保解析阶段被执行。
             *
             * 总结
             * 自定义类加载器：在自定义类加载器中，如果没有显式调用resolveClass，解析阶段不会在加载时立即执行。
             * 解析的自动触发：Java虚拟机在第一次使用类时（如访问静态字段、调用静态方法或创建类实例），会自动触发解析阶段。
             * 安全性和正确性：无论是否在加载时显式调用resolveClass，Java虚拟机都会确保类在使用前已经完成解析阶段，从而保证程序的安全性和正确性。
             * 通过这些机制，Java虚拟机能够确保即使在自定义类加载器中没有显式调用resolveClass，类的解析阶段也会在适当的时候自动进行。
             */
            return clazz;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null)
                    baos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bis != null)
                    bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;


    }
}
