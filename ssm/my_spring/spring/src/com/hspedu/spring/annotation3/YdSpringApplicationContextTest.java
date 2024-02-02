package com.hspedu.spring.annotation3;

import com.hspedu.spring.annotation3.YdSpringApplicationContext;
import com.hspedu.spring.annotation3.YdSpringConfig;

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
