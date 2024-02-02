package com.hsp.reflection;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yangda
 * @description:
 * @create 2022-12-09-14:23
 */
public class ReflectionTest {
    @Test
    public void test1() throws Exception {
        //读取配置文件方法一：
        //默认识别为：当前module下
        FileInputStream fileInputStream = new FileInputStream("jdbc2.properties");
        int readLen = 0;
        byte[] buf = new byte[10];
        while ((readLen = fileInputStream.read(buf)) != -1) {
            System.out.print(new String(buf, 0, readLen));
        }
//        System.out.println(fileInputStream.read());
//        System.out.println(fileInputStream.read());

        //读取配置文件方法二：
        //默认识别为：当前module的src下
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream("jdbc1.properties");
        while ((readLen = resourceAsStream.read(buf)) != -1) {
            System.out.print(new String(buf, 0, readLen));
        }
    }

    @Test
    public void test2() throws IllegalAccessException, InstantiationException {
        //创建运行时类的对象
        Class<Person> p = Person.class;
        Person person = p.newInstance();
        System.out.println(p);//class com.hsp.reflection.Person


    }
    @Test
    public void test3() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //创建运行时类的对象
        Class<Person> p = Person.class;
        Person person = p.newInstance();
        System.out.println(p);//class com.hsp.reflection.Person

        //调用运行时类指定的方法
        Method cry = p.getDeclaredMethod("cry", int.class);
        cry.setAccessible(true);
        cry.invoke(p,5);//IllegalAccessException
        cry.invoke(null,15);//IllegalAccessException

        //调用无参的方法
        Method cry2 = p.getDeclaredMethod("cry2", null);
        // 反射爆破 因为是私有的 所以需要进行反射暴破
        cry2.setAccessible(true);
        // 静态static 无参的方法的调用
        //Confusing argument 'null', unclear if a varargs or non-varargs call is desired
        //混淆的参数“null”，不清楚是否需要varargs或非varargs调用
        cry2.invoke(null,null);

    }


















}
