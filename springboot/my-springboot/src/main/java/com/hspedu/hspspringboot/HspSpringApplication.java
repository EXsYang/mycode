package com.hspedu.hspspringboot;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

/**
 * @author yangda
 * @create 2023-12-01-16:59
 * @description:
 */
public class HspSpringApplication {

    //这里会创建tomcat对象，并关联Spring容器，并启动
    public static void run() {

        try {
            //创建Tomcat对象
            Tomcat tomcat = new Tomcat();

            //1. 让tomcat可以将请求转发到spring web容器，因此需要进行关联
            //2. "/hspboot" 就是我们的项目的 application context ，就是原来配置tomcat时指定的 application context
            //3."D:\\Java_developer_tools\\springboot\\my-springboot"  这句话 就是指定项目的路径
            //4. 没有下面这句话 所有的配置都是白瞎的
            tomcat.addWebapp("/hspboot", "D:\\Java_developer_tools\\springboot\\my-springboot");

            //设置端口
            tomcat.setPort(9090);
            //启动
            tomcat.start();

            //等待请求接入
            System.out.println("---9090端口--等待请求----");
            tomcat.getServer().await();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
