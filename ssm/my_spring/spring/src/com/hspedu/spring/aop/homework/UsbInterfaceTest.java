package com.hspedu.spring.aop.homework;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yangda
 * @description:
 * @create 2023-09-08-23:02
 */
public class UsbInterfaceTest {

    @Test
    public void aopTest(){

        ClassPathXmlApplicationContext ioc = new ClassPathXmlApplicationContext("beans08.xml");
        //
        //UsbInterface usbInterface = ioc.getBean(UsbInterface.class);

        // 按照面向对象的形式获取bean对象 即在配置文件beans08.xml 中没有启用 <aop:aspectj-autoproxy/>
        // 可以获取到 因为此时ioc 容器中保存的是Phone类型的对象
        // 通过debug 保存在singletonObjects table id=phone 的val属性中的才是真正保存到对象
        // 当启用<aop:aspectj-autoproxy/> 就按照spring aop 是按照 动态代理 那套机制执行 此时
        // getBean 方法就类似于 手写的getProxy() 返回的是接口类型的代理对象
        // 此时再通过 Phone.class 去获取会直接报错 报错是因为 它在返回的时候 是按照代理对象来返回，
        // 他不可以返回一个(Phone.class) 类型的对象！！！

        //Phone bean = ioc.getBean(Phone.class);

       UsbInterface phone = (UsbInterface)ioc.getBean("phone");
       UsbInterface camera = (UsbInterface)ioc.getBean("camera");

        phone.work();
        System.out.println("===============");
        camera.work();
    //

    }
}
