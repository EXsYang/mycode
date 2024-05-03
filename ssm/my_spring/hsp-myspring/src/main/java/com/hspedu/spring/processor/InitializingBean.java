package com.hspedu.spring.processor;

/**
 * @author yangda
 * @description:
 * 老师解读
 * 1. 我们根据原生Spring 定义了一个 InitializingBean 初始化方法是根据这个接口实现的
 * 2. 该InitializingBean接口有一个方法void afterPropertiesSet() throws Exception;
 * 3. afterPropertiesSet() 在Bean的 setter方法后执行,即就是我们原来的初始化方法

    bean 的生命周期
    ● 说明: bean 对象创建是由 JVM 完成的，然后执行如下方法
    1. 执行构造器
    2. 执行 set 相关方法-(即这里会对属性进行装配/设置,如装配DAO等)
    3. 调用 bean 的初始化的方法（需要配置）
    4. 使用 bean
    5. 当容器关闭时候，调用 bean 的销毁方法（需要配置）

    原生Spring的bean 的初始化方法的实现 底层也是用的是实现了InitializingBean接口
    如果配置了后置处理器则在调用初始化方法是的调用机制如下:
 *    调用 后置处理器 postProcessBeforeInitialization()
 *    调用 bean 的初始化方法（需要配置）
 *    调用 后置处理器 postProcessAfterInitialization()
 *
 *
 *
 * 4. 当一个Bean实现了这个接口后,就实现afterPropertiesSet() ，这个方法就是初始化方法
 *
 *  * HspSpringApplicationContext.java
 *  * , 在创建好 Bean 实例后，判断是否需要进行初始化 【老师心得: 容器中常
 *  * 用的一个方法是，根据该类是否实现了某个接口，来判断是否要执行某个业务逻辑, 这里
 *  * 其实就是 java 基础的 接口编程 实际运用 标记接口,一个方法都没有,价值就是根据是否实现了某个标记接口，
 *  来判断是否要加入某些逻辑,比如序列化时的接口 Serializable,该接口中就一个方法都没有
 *  ,Serializable标记接口给底层使用的
 *  public interface Serializable {
 * }
 *  】
 * @create 2023-09-14-20:10
 */
public interface InitializingBean {

    // 该方法是在注入到ioc容器中的bean类的所有
    // 属性都初始化后(即bean生命周期中setter方法执行完后)
    // 自动执行的，前提是该类实现了InitializingBean接口
    //这个afterPropertiesSet()就是初始化方法
    void afterPropertiesSet() throws Exception;
}
