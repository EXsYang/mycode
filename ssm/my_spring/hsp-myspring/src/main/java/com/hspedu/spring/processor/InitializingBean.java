package com.hspedu.spring.processor;

/**
 * @author yangda
 * @description:
 * 老师解读
 * 1. 我们根据原生Spring 定义了一个InitializingBean 初始化方法是根据这个接口实现的
 * 2. 该InitializingBean接口有一个方法void afterPropertiesSet() throws Exception;
 * 3. afterPropertiesSet() 在Bean的 setter方法后执行,即就是我们原来的初始化方法
 * 4. 当一个Bean实现了这个接口后,就实现afterPropertiesSet() ，这个方法就是初始化方法
 *
 *  * HspSpringApplicationContext.java
 *  * , 在创建好 Bean 实例后，判断是否需要进行初始化 【老师心得: 容器中常
 *  * 用的一个方法是，根据该类是否实现了某个接口，来判断是否要执行某个业务逻辑, 这里
 *  * 其实就是 java 基础的接口编程实际运用】
 * @create 2023-09-14-20:10
 */
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
