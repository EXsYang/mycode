package com.atguigu.exer;

import com.sun.org.apache.xpath.internal.functions.FuncFalse;

/**
 * @author yangda
 * @create 2022-06-04-23:06
 */
public class ReturnTest {

    public boolean test1(){
        for (int i = 0; i < 3; i++) {
            return false;
        }
        return true;
    }


}
class Test{
    public static void main(String[] args) {
        ReturnTest r = new ReturnTest();
        boolean b = r.test1();
        System.out.println(b);
    }

}