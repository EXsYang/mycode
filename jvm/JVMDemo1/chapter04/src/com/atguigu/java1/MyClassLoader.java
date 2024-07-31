package com.atguigu.java1;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author shkstart
 * @create 11:03
 * <p>
 * 自定义类的加载器,默认父类是系统类加载器：Launcher$AppClassLoader
 * 更加详细的自定义类的加载器的说明，参考com/atguigu/java2/MyClassLoader.java
 */
public class MyClassLoader extends ClassLoader {
    private String rootDir;

    public MyClassLoader(String rootDir) {
        this.rootDir = rootDir;
    }

    protected Class<?> findClass(String className) throws ClassNotFoundException {
        Class clazz = this.findLoadedClass(className);
        FileChannel fileChannel = null;
        WritableByteChannel outChannel = null;
        if (null == clazz) {
            try {
                String classFile = getClassFile(className);
                FileInputStream fis = new FileInputStream(classFile);
                fileChannel = fis.getChannel();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                outChannel = Channels.newChannel(baos);
                ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
                while (true) {
                    int i = fileChannel.read(buffer);
                    if (i == 0 || i == -1) {
                        break;
                    }
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }

                byte[] bytes = baos.toByteArray();
                clazz = defineClass(className, bytes, 0, bytes.length);
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

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (fileChannel != null)
                        fileChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (outChannel != null)
                        outChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return clazz;
    }

    /**
     * 类文件的完全路径
     */
    private String getClassFile(String className) {
        return rootDir + "\\" + className.replace('.', '\\') + ".class";
    }
}

