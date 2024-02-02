package com.hspedu.spring.bean;

import org.springframework.context.annotation.Bean;

/**
 * @author 韩顺平
 * @version 1.0
 */
public class House {
    private String name;

    public House() {
        System.out.println("House() 构造器...");
    }

    public String getName() {
        return name;
    }

    // 如果不给setName方法 同时beans.xml 中又配置了name属性 会报错 BeanCreationException:Bean property 'name' is not writable or has an invalid setter method.
    //public void setName(String name) {
    //    System.out.println("House setName()=" + name);
    //    this.name = name;
    //}
    public void setName(String name) {
        System.out.println("House setName()=" + name);
        this.name = name;
    }

    //老师解读
    //1. 这个方法是程序员来编写的.
    //2. 根据自己的业务逻辑来写.
    public void init() {
        System.out.println("House init()..");
    }

    // 在配置bean时 不管是用来测试生命周期还是测试后置处理器中代码执行顺序时
    // 私有的init() 方法都可以被调用到
    //  <bean class="com.hspedu.spring.bean.House" id="house"
    //          init-method="init"
    //          destroy-method="destroy">
    //        <property name="name" value="北京豪宅"/>
    //    </bean>
    //
    //private void init() {
    //    System.out.println("House private init()..");
    //}

    //老师解读
    //1. 这个方法是程序员来编写的.
    //2. 根据自己的业务逻辑来写.
    //3. 名字也不是固定的
    public void destroy() {
        System.out.println("House destroy()..");
    }
    //private void destroy() {
    //    System.out.println("House destroy()..");
    //}

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                '}';
    }
}
