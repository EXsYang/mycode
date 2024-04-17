package com.hspedu;

import org.junit.Test; //间接依赖可以使用 junit4.12

/**
 * @author yangda
 * @create 2024-04-17-18:52
 * @description: 测试在maven_A中 间接依赖是否可以使用 junit4.12
 */
public class HspTest {

    @Test //间接依赖可以使用 junit4.12 //路径优先-这里使用的是junit4.12
    public void t1(){
        System.out.println("t1测试方法...");
    }
}
