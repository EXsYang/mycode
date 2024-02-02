package com.hspedu.spring.annotation2;

/**
 * @author yangda
 * @description:
 * @create 2023-09-04-19:58
 */
public class YdSpringApplicationContextTest {
    public static void main(String[] args) {

        YdSpringApplicationContext ioc = new YdSpringApplicationContext(YdSpringConfig.class);
        System.out.println("ok");
    }
}
