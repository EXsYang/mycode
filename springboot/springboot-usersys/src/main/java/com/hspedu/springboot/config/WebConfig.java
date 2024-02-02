package com.hspedu.springboot.config;

import com.hspedu.springboot.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/**
 * @author yangda
 * @create 2023-12-08-22:47
 * @description: 这是一个配置类 注入拦截器LoginInterceptor
 */

//第一种注入拦截器的方式
// @Configuration
// public class WebConfig implements WebMvcConfigurer {
//
//     @Override
//     public void addInterceptors(InterceptorRegistry registry) {
//         registry.addInterceptor(new LoginInterceptor())
//                 .addPathPatterns("/**")  // 这里会拦截所有的请求，登录页面 / 都看不到，会被拦截到
//                 // .excludePathPatterns("/login"); //指定要放行的，后面可以根据需要，来指定需要放行的请求路径
//                 // .excludePathPatterns("/","/login"); // /"images/**" 如果没有放行，图片会被拦截
//                 // "images/**" 细节:为什么不需加static? => static/images/**,因为在 WebProperties.java
//                 // ,默认就已经将静态资源的访问路径指到了,static/ 目录下，因此不用加static
//                 .excludePathPatterns("/","/login","/images/**"); //指定要放行的，后面可以根据需要，来指定需要放行的请求路径
//     }
// }


//第二种注入拦截器的方式
@Configuration
public class WebConfig {

    // @Bean
    // public ViewResolver thymeleafViewResolver(SpringTemplateEngine templateEngine) {
    //     ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
    //     viewResolver.setTemplateEngine(templateEngine);
    //     return viewResolver;
    // }
    //
    // @Bean
    // public SpringTemplateEngine templateEngine() {
    //     SpringTemplateEngine templateEngine = new SpringTemplateEngine();
    //     templateEngine.setTemplateResolver(templateResolver());
    //     return templateEngine;
    // }
    //
    // private ClassLoaderTemplateResolver templateResolver() {
    //     ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    //     templateResolver.setPrefix("templates/");
    //     templateResolver.setSuffix(".html");
    //     templateResolver.setTemplateMode("HTML");
    //     templateResolver.setCharacterEncoding("UTF-8");
    //     return templateResolver;
    // }

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                System.out.println("addInterceptors...@@@");
                registry.addInterceptor(new LoginInterceptor())
                        .addPathPatterns("/**")
                        .excludePathPatterns("/","/login","/images/**","/upload.html","/upload","/sql");
                        // .excludePathPatterns("/","/login","/images/**","/upload.html","/upload","/css/**");
                        // 测试如果静态资源没有配置，看看是否会被拦截
                        // .excludePathPatterns("/","/login","/upload.html","/upload");
            }
        };
    }

}
