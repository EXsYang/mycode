package com.hspedu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author yangda
 * @create 2023-12-07-15:37
 * @description:
 */
//@ServletComponentScan: 要求扫描 "com.hspedu.springboot" 包/子包下的原生方式注入的Servlet
// @ServletComponentScan(basePackages = "com.hspedu.springboot")
@SpringBootApplication
public class Application {
    public static void main(String[] args) {

        ConfigurableApplicationContext ioc =
                SpringApplication.run(Application.class, args);

        // 下面这行代码会触发 注入到ioc中的监听器 Listener_ 的contextDestroyed()销毁方法
        // ioc.stop();//停止容器

        System.out.println("springboot start...");


    }
}
