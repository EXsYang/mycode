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
//----------------------------------------------------------------------------------------------
/**
 * 在Spring Boot中，静态资源的默认访问路径是通过spring-boot-starter-web模块中的配置定义的。
 * 以下是关于静态资源处理的一些核心配置说明：
 *
 * 1. 静态资源默认位置：
 * Spring Boot默认配置了以下标准位置作为静态资源的存放路径，这些资源可以被直接通过URL访问。
 * - /META-INF/resources/
 * - /resources/
 * - /static/
 * - /public/
 * 这些配置通常在WebProperties.java中通过CLASSPATH_RESOURCE_LOCATIONS常量进行定义。
 */
// private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
//         "classpath:/META-INF/resources/",
//         "classpath:/resources/",
//         "classpath:/static/",
//         "classpath:/public/"
//         };

/**
 * 2. 常见静态资源类型：
 * 常见的静态资源类型包括但不限于JavaScript、CSS、图片（例如.jpg、.png、.gif、.bmp、.svg）以及字体文件等。
 */

/**
 * 3. 静态资源访问路径：
 * 默认情况下，静态资源的访问路径是项目的根路径后面直接跟上资源名称。
 * 例如，如果应用部署在 http://localhost:8080/，那么访问/static目录下的hi.jpg图片资源的URL将会是 http://localhost:8080/hi.jpg。
 * 这个默认行为可以通过在WebMvcProperties.java中设置staticPathPattern属性来修改。默认情况下，此属性的值为"/**"，意味着所有的URL路径都会被考虑用于静态资源的映射。
 */
// private String staticPathPattern = "/**";

/**
 * 注意：以上配置是基于Spring Boot的自动配置功能，通过引入spring-boot-starter-web依赖，
 * Spring Boot应用会自动按照上述规则来处理静态资源的访问请求。
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
