package com.atguigu.java1;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author shkstart
 * @create 2020-09-08 11:30
 * 指令8：异常处理
 */
public class ExceptionTest {
    public void throwZero(int i){
        if(i == 0){
            throw new RuntimeException("参数值为0");
        }
    }

    // public void throwOne(int i){  //这种方法声明的形式在jclasslib中没有看到属性 Exceptions 结构
    public void throwOne(int i) throws RuntimeException,IOException{
        //这种方法声明的形式在jclasslib中可以看到 Exceptions 属性结构，和Code结构是并列的
        // Code结构是用来刻画方法体中的内容的

        if(i == 1){
            throw new RuntimeException("参数值为1");
        }
    }




    // @Test
    public void throwArithmetic() {
        int i = 10;
        int j = i / 0; //系统提供的异常，自动抛出`java.lang.ArithmeticException: / by zero`
                        //对应的字节码看不到`athrow`指令,而是`idiv`
        System.out.println(j);
    }

    public void tryCatch(){
        try{
            File file = new File("d:/hello.txt");
            FileInputStream fis = new FileInputStream(file);

            String info = "hello!";
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(RuntimeException e){
            e.printStackTrace();
        }
    }

    //思考：如下方法返回结果为多少？
    public static String func() {
        String str = "hello";
        try{
            return str;
        }
        finally{
            str = "atguigu";
        }
    }

    public static void main(String[] args) {

        System.out.println(func());//hello
    }
}
