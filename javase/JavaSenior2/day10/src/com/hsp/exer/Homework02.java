package com.hsp.exer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yangda
 * @description:
 * @create 2022-12-13-13:34
 */
public class Homework02 {
    public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
//        File file = new File("e:/aa.txt");
//        file.createNewFile();
        Class<?> fileCls = Class.forName("java.io.File");
        System.out.println(fileCls);//class java.io.File
        System.out.println(fileCls.getClass());//class java.lang.Class

        Constructor<?>[] constructors = fileCls.getDeclaredConstructors();

        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }

//        Object o = fileCls.newInstance();//可以这样写吗？不行，没有空参构造器！
        Constructor<?> declaredConstructor = fileCls.getDeclaredConstructor(String.class);
        String fileAllPath = "e:/mynew.txt";
        Object file = declaredConstructor.newInstance(fileAllPath);

        Method createNewFile = fileCls.getMethod("createNewFile");
        createNewFile.invoke(file);


        System.out.println(file);//file是对象   e:\mynew.txt
        System.out.println(file.getClass());//class java.io.File

        System.out.println(fileCls);//fileCls是运行时类  class java.io.File
        System.out.println(fileCls.getClass());//class java.lang.Class




//        System.out.println(o.getClass());


    }
}
