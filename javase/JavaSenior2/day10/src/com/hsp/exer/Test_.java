package com.hsp.exer;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author yangda
 * @description:
 * @create 2022-12-13-11:47
 */
public class Test_ {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException, ClassNotFoundException {
        Class<PrivateTest> p = PrivateTest.class;
        PrivateTest privateTest = p.newInstance();//不要忘了创建实例！！！

        /*
           getName:获取全类名
           getSimpleName:获取简单类名
           getFields:获取所有 public 修饰的属性，包含本类以及父类的
           getDeclaredFields:获取本类中所有属性
           getMethods:获取所有 public 修饰的方法，包含本类以及父类的
           getDeclaredMethods:获取本类中所有方法 不管是private 默认 受保护的 public 都获取到
         */

        Field[] fields = p.getFields();
        for (Field field : fields) {
            System.out.println("p.getFields()得到的属性名= " + field.getName());
        }
        System.out.println("=================");
        Field[] fields2 = p.getDeclaredFields();
        for (Field field : fields2) {
            System.out.println("p.getDeclaredFields()得到的属性名= " + field.getName());
        }

        Field name = p.getDeclaredField("name");
        name.setAccessible(true);
        System.out.println(name.get(privateTest));
        Method getName = p.getMethod("getName");
        System.out.println(getName.invoke(privateTest));

        //使用forName()创建对象
        Class<?> aClass = p.forName("com.hsp.exer.PrivateTest");
        System.out.println(aClass.getClass());//class java.lang.Class


    }
}
