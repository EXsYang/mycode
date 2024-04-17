package com.hspedu;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author yangda
 * @create 2024-04-15-20:28
 * @description:
 */
public class TestHello {

    @Test
    public void testSum(){
        Hello hello = new Hello();
        String res = hello.sum(10, 50);

        //使用断言进行测试 Assert是junit提供的
        /**
         * 解读: assert:断言;生效; /əˈsɜːt/
         * 1. Assert 是一个断言类，(就是判断实际返回的值和期望值是否相同)
         * 2. 如果res 和 "sum=10" 是相同的，则通过断言
         * 3. 如果res 和 "sum=10" 是不相同的，则给出相应提示
         *
         * expected 预期的 , actual 实际的
         */
        Assert.assertEquals("sum=60",res);

    }
}
