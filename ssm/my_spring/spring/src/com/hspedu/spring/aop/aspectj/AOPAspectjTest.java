package com.hspedu.spring.aop.aspectj;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangda
 * @description:
 * @create 2023-09-08-17:40
 */
public class AOPAspectjTest {

    @Test
    public void smartDogTestByProxy(){

       /* 细节说明
        1. 关于切面类方法命名可以自己规范一下, 比如 showBeginLog() . showSuccessEndLog()
        showExceptionLog(), showFinallyEndLog()
        2. 切入表达式的更多配置，比如使用模糊配置
        @Before(value="execution(* com.hspedu.aop.proxy.SmartDog.*(..))")
        3. 表示所有访问权限，所有包的下所有有类的所方法，都会被执行该前置通知方法
        @Before(value="execution(* *.*(..))")
        4. 当 spring 容器开启了 <!-- 开启基于注解的 AOP 功能 --> <aop:aspectj-autoproxy/> , 我们获
        取注入的对象, 需要以接口的类型来获取, 因为你注入的对象.getClass() 已经是代理类型
        了!
        5. 当 spring 容器开启了 <!-- 开启基于注解的 AOP 功能 --> <aop:aspectj-autoproxy/> , 我们获
        取注入的对象, 也可以通过 id 来获取, 但是也要转成接口类型.*/

        // 当spring 容器开启了 基于注解的AOP功能 getBean() 方法类似于自己写的getProxy()
        // 返回的是代理对象 不管是用 类型还是id 去获取 返回的都是代理对象！！！
        // 不是之前的getBean() 了 类似于进行了一次包裹

        //得到spring容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans08.xml");

        //这里我们需要通过接口类型来获取到注入的SmartDog对象-就是代理对象
        //在底层返回代理对象 是按照接口类型来返回的
        SmartAnimalable smartAnimalable =
                ioc.getBean(SmartAnimalable.class);


        // 在返回的时候 是按照代理对象来返回 不可以给你返回一个SmartDog
        //SmartDog smartAnimalable = ioc.getBean(SmartDog.class);
        //下面这种也不行 在等号右边的ioc.getBean(SmartDog.class); 底层
        // 不可以返回一个SmartDog.class 类型的对象
        //SmartAnimalable smartAnimalable = ioc.getBean(SmartDog.class);
        //报错：org.springframework.beans.factory.NoSuchBeanDefinitionException:
        //No qualifying bean of type 'com.hspedu.spring.aop.aspectj.SmartDog' available


        //SmartAnimalable smartAnimalable =
        //        (SmartAnimalable)ioc.getBean("smartDog");


        //smartAnimalable.getSub(10,2);
         //下面的报错信息 是因为没有找到 Bean定义 'com.hspedu.spring.aop.aspectj.SmartAnimalable'
         //即在容器中 没有找的这个类的定义信息 说明可能是导错包了 因为有很多
         //名字叫SmartAnimalable的类
         //容器中没有找到'com.hspedu.spring.aop.aspectj. 包下的实现了这个接口的类


        //org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.hspedu.spring.aop.aspectj.SmartAnimalable' available

        smartAnimalable.getSum(10,2);
        System.out.println("==============");
        smartAnimalable.getSub(10,2);
        //
        ////return getClass().getName() + "@" + Integer.toHexString(hashCode());
        //System.out.println("smartAnimalable= " + smartAnimalable);
        //System.out.println("smartAnimalable.getClass().getName()= " + smartAnimalable.getClass().getName());
        ////为什么直接sout对象和sout对象.getClass().getName()不一样?
        //// 可以参考 ：https://www.zhihu.com/question/542747583
        ////smartAnimalable= com.hspedu.spring.aop.aspectj.SmartDog@43df23d3
        ////smartAnimalable.getClass().getName()= com.sun.proxy.$Proxy17
        //System.out.println("smartAnimalable运行类型= " + smartAnimalable.getClass());
        ////smartAnimalable运行类型= class com.sun.proxy.$Proxy17
        //
        ////org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.hspedu.spring.aop.aspectj.SmartAnimalable' available

    }

    @Test
    public void test3() {

      /*  1. 切入表达式也可以指向类的方法, 这时切入表达式会对该类/对象生效
        2. 切入表达式也可以指向接口的方法, 这时切入表达式会对实现了接口的类/对象生效
        3. 切入表达式也可以对没有实现接口的类，进行切入【举例说明】
        class Car {
            public void run() {
                System.out.println("car run");
            }
        }
        4. 老师补充: 动态代理 jdk 的 Proxy 与 Spring 的 CGlib
        https://www.cnblogs.com/threeAgePie/p/15832586.html*/

        //1. JDK动态代理是面向接口的，只能增强实现类中接口中存在的方法。CGlib是面向父类的，可以增强父类的所有方法
        //2. JDK得到的对象是JDK代理对象实例，而CGlib得到的对象是被代理对象的子类

        //得到spring容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans08.xml");

        Car car = ioc.getBean(Car.class);

        //说明: car对象仍然是代理对象
        System.out.println("car的运行类型=" + car.getClass());

        car.run();



    }

    @Test
    public void testDoAround() {
        //得到spring容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans08.xml");


        SmartAnimalable smartAnimalable =
                ioc.getBean(SmartAnimalable.class);

        smartAnimalable.getSum(10, 2);
    }

    @Test
    public void testSmartCat(){
        //得到spring容器
        ApplicationContext ioc =
                new ClassPathXmlApplicationContext("beans08.xml");


        // 被切入的方法的类 返回的是代理对象 $Proxy22
        SmartAnimalable smartAnimalable1 =
                (SmartAnimalable)ioc.getBean("smartDog");
        System.out.println("smartAnimalable1.getClass= " + smartAnimalable1.getClass());
        // smartAnimalable1.getClass= class com.sun.proxy.$Proxy22

        // 没有被切入的方法的类 返回的是原本的对象 SmartCat
        SmartAnimalable smartAnimalable2 =
                (SmartAnimalable)ioc.getBean("smartCat");
        System.out.println("smartAnimalable2.getClass= " + smartAnimalable2.getClass());
        // smartAnimalable.getClass= class com.hspedu.spring.aop.aspectj.SmartCat

        System.out.println("============================");
        // 测试 在SmartAnimalAspect3 中配置了一个切入方法后 返回的是否为代理对象 结果:是
        // 结论: 只要某个类 没有被切入 返回的就是 对应的那个类的对象
        // ，如果在切面类配置了 切入方法 ，返回的就是代理对象
        SmartAnimalable smartAnimalable3 =
                (SmartAnimalable)ioc.getBean("smartCat");
        System.out.println("smartAnimalable3.getClass= " + smartAnimalable3.getClass());
        // smartAnimalable3.getClass= class com.sun.proxy.$Proxy22




        // smartAnimalable.getSum(10, 2);
    }


}
