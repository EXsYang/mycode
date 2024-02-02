package com.hspedu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author yangda
 * @create 2023-12-02-23:12
 * @description: WEB 开发-静态资源访问
 *
 * 1. 只要静态资源放在类路径下： /static 、 /public 、 /resources 、 /META-INF/resources
 * 可以被直接访问- 对应文件 WebProperties.java
 * private static final String[] CLASSPATH_RESOURCE_LOCATIONS =
 * { "classpath:/META-INF/resources/", "classpath:/resources/", "classpath:/static/", "classpath:/public/" };
 * 2. 常见静态资源：JS、CSS 、图片（.jpg .png .gif .bmp .svg）、字体文件(Fonts)等
 * 3. 访问方式 ：默认: 项目根路径/ + 静态资源名 比如 http://localhost:8080/hi.jpg . - 设置
 * WebMvcProperties.java
 * private String staticPathPattern = "/**";
 */
@SpringBootApplication
public class MainApp {
    public static void main(String[] args) {

        /**
         * private static final String[] CLASSPATH_RESOURCE_LOCATIONS = { "classpath:/META-INF/resources/",
         * 				"classpath:/resources/", "classpath:/static/", "classpath:/public/" };
         * private String[] staticLocations = CLASSPATH_RESOURCE_LOCATIONS;
         */
        SpringApplication.run(MainApp.class,args);
        System.out.println("springboot start...");

    }
}
