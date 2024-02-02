package com.hspedu.springboot;

import com.hspedu.springboot.bean.*;
import com.hspedu.springboot.config.BeanConfig;
import com.hspedu.springboot.config.BeanConfig2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * @author yangda
 * @create 2023-11-21-15:40
 * @description: @SpringBootApplication注解表示这是一个 SpringBoot 应用/项目
 * 加了该注解后 一些基本的/约定的配置就出来了
 * <p>
 * springboot 默认扫描的是运行springboot主程序所在的类
 * MainApp.java 所在的包和它的子包
 * 可以通过 @SpringBootApplication 注解的 scanBasePackages属性更改默认要扫描的包的位置
 * scanBasePackages = "com.hspedu" 指定了 springboot要扫描的包和子包
 * 如果有多个包 可以 scanBasePackages = {"com.hspedu","xxx.yyy.zzz"}
 */
// @SpringBootApplication(scanBasePackages = {"com.hspedu"}) //指定一个String数组也可以
// 经过测试,要扫描的包以这里指定的为准，
// 这指定了一个包，会把默认的要扫描包的规则覆盖掉
// 即不会再按照运行springboot主程序的.java文件所在的包及其子包进行扫描了
// @SpringBootApplication(scanBasePackages = "com.hspedu.testscan")
@SpringBootApplication(scanBasePackages = "com.hspedu") //直接指定一个字符串也可以
public class MainApp {

    public static void main(String[] args) {

        //启动springboot应用程序/项目
        ConfigurableApplicationContext ioc =
                SpringApplication.run(MainApp.class, args);


        //如何查看容器中注入的组件
        // String[] beanDefinitionNames = ioc.getBeanDefinitionNames();
        // for (String beanDefinitionName : beanDefinitionNames) {
        //     System.out.println("beanDefinitionName= " + beanDefinitionName);
        // }

        //演示Spring中传统的注解依然可以使用 @Controller @Service @Repository 等.
        // A aBean = ioc.getBean("a", A.class);
        // System.out.println("aBean= " + aBean);

        //--演示在springboot 项目，依然可以使用spring的配置bean/注入bean/获取bean方式 start====

        // ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
        // Monster monster03 = ac.getBean("monster03", Monster.class);
        // System.out.println("monster03= " + monster03);

        //--演示在springboot 项目，依然可以使用spring的配置bean/注入bean/获取bean方式 end====
        //==== 演示 @Configuration start ====

        // Monster monster01 = ioc.getBean("monster01", Monster.class);
        // Monster monster02 = ioc.getBean("monster01", Monster.class);
        // Monster monster01 = ioc.getBean("monster_nmw", Monster.class);
        // Monster monster02 = ioc.getBean("monster_nmw", Monster.class);
        // System.out.println("monster01= " + monster01 + " " + monster01.hashCode());
        // //可见 @Bean默认注入是单例的
        // System.out.println("monster02= " + monster02 + " " + monster02.hashCode());

        //==== 演示 @Configuration end ====


        //==== 演示 标识了 @Configuration注解的 配置类bean也会被注入到ioc容器 start ====

        // 配置类本身也是组件 因此也可以获取
        // BeanConfig beanConfig = ioc.getBean("beanConfig", BeanConfig.class);
        // BeanConfig beanConfig = ioc.getBean( BeanConfig.class);
        // System.out.println("beanConfig= " + beanConfig);
        // // beanConfig= com.hspedu.springboot.config.BeanConfig$$EnhancerBySpringCGLIB$$9e922700@22101c80
        // Monster monster01 = beanConfig.monster01();
        // Monster monster02 = beanConfig.monster01();
        // System.out.println("monster01= " + monster01);
        // System.out.println("monster02= " + monster02);

        //==== 演示 标识了 @Configuration注解的 配置类bean也会被注入到ioc容器 end ====

        //==== 演示 @Configuration(proxyBeanMethods = xxx) start ====
        // //1. 先得到BeanConfig组件
        // BeanConfig beanConfig = ioc.getBean( BeanConfig.class);
        // Monster monster_01 = beanConfig.monster01();
        // Monster monster_02 = beanConfig.monster01();
        // System.out.println("monster_01= " + monster_01 + " " + monster_01.hashCode());
        // System.out.println("monster_02= " + monster_02 + " " + monster_02.hashCode());
        //
        // // 特别说明: proxyBeanMethods 是在 调用@Bean 方法 才生效，因此，需要先获取
        // // BeanConfig 组件，再调用方法 而不是直接通过 SpringBoot主程序得到的容器来获取 bean,
        // // 注意观察直接通过ioc.getBean() 获取 Bean, proxyBeanMethods 值并没有生效
        //
        // Monster monster01 = ioc.getBean("monster01",Monster.class);
        // Monster monster02 = ioc.getBean("monster01",Monster.class);
        // System.out.println("monster01= " + monster01 + " " + monster01.hashCode());
        // System.out.println("monster02= " + monster02 + " " + monster02.hashCode());
        //==== 演示 @Configuration(proxyBeanMethods = xxx) end ====

        //==== 演示 可以有多个配置类 即在springboot中可以配置多个BeanConfig.java文件 start ====
        // Monster monster01 = ioc.getBean("monster01", Monster.class);
        // Monster monster02 = ioc.getBean("monster02", Monster.class);
        // System.out.println("monster01= " + monster01 + " " + monster01.hashCode());
        // System.out.println("monster02= " + monster02 + " " + monster02.hashCode());

        //==== 演示 BeanConfig2 即在springboot中可以配置多个BeanConfig.java文件 end ====

        //==== 演示 @Import start ====

        // 因为默认是单例注入的 可以使用clazz对象来获取bean
        // Dog dogBean = ioc.getBean(Dog.class);
        // Cat catBean = ioc.getBean(Cat.class); //这个cat对象在debug时 ioc中找不到，在旁边显示的View中才找得到 属于debug视图显示有问题
        //
        // System.out.println("dogBean= " + dogBean);
        // System.out.println("catBean= " + catBean);

        //==== 演示 @Import end ====

        //==== 演示 @ConditionalOnBean start ====
        // Dog dog01 = ioc.getBean("dog01", Dog.class);
        // System.out.println("dog01= " + dog01);

        //==== 演示 @ConditionalOnBean end ====

        //==== 演示 @ImportResource start ====
        // Monster monster03 = ioc.getBean("monster03", Monster.class);
        // System.out.println("monster03= " + monster03);
        // System.out.println("monster03 bean 是否存在- " + ioc.containsBean("monster03"));
        //
        // Monster monster04 = ioc.getBean("monster04", Monster.class);
        // System.out.println("monster04= " + monster04);
        //==== 演示 @ImportResource end ====

        //==== 演示  @ConfigurationProperties(prefix = "furn01") start ====

        // Furn furn = ioc.getBean("furn", Furn.class);
        // System.out.println("furn= " + furn);

        // System.out.println("ok");
        //==== 演示  @ConfigurationProperties(prefix = "furn01") end ====

        //==== 演示  @EnableConfigurationProperties({Furn.class}) 从ioc中获取 start ====
        // Furn furn = ioc.getBean("furn01-com.hspedu.springboot.bean.Furn", Furn.class);
        // System.out.println("furn= " + furn);
        // furn= com.hspedu.springboot.bean.Furn@3eba57a7

        // 如果application.properties 中配置了中文 furn01.name=TV~~!电视机
        // 注入到ioc中的bean 对应的属性就会出现中文乱码 而不是只是返回给浏览器出现中文乱码 这里注意！
        // furn= Furn{id=199, name='TV~~!ï¿½ï¿½ï¿½Ó»ï¿½', price=1000.9}
        System.out.println("okk");

        //==== 演示  @EnableConfigurationProperties({Furn.class}) end ====
    }
}

