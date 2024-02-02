package com.hspedu.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author 韩顺平
 * @version 1.0
 * 这是一个后置处理器, 需要实现 BeanPostProcessor接口 后置处理器会对容器中配置
 * 的所有对象生效，即使没有配置初始化方法 beans02.xml bean配置的   init-method="init"
 * 也会生效，只不过不会输出 初始化方法的信息而已
 */
public class MyBeanPostProcessor2 implements BeanPostProcessor {

    /**
     * 什么时候被调用: 在Bean的init方法前被调用
     * 注意：容器中的bean 即使没有配置init()初始化方法 这里的后置处理器中的这两个
     * 方法也会被调用！！
     * @param bean 传入的在IOC容器中创建/配置Bean
     * @param beanName 传入的在IOC容器中创建/配置Bean的id
     * @return Object 程序员对传入的bean 进行修改/处理【如果有需要的话】 ,返回
     * @throws BeansException
     */

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第二个后置处理器postProcessBeforeInitialization().. bean="
                + bean + " beanName=" + beanName);

        //初步体验案例: 如果类型是House的统一改成 上海豪宅
        //对多个对象进行处理/编程==>切面编程
        if(bean instanceof House) {
            ((House)bean).setName("上海豪宅~");
        }
        return bean;
    }

    /**
     * 什么时候被调用: 在Bean的init方法后被调用
     * @param bean  传入的在IOC容器中创建/配置Bean
     * @param beanName 传入的在IOC容器中创建/配置Bean的id
     * @return 程序员对传入的bean 进行修改/处理【如果有需要的话】 ,返回
     * @throws BeansException
     */

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("第二个后置处理器postProcessAfterInitialization().. bean="
                + bean + " beanName=" + beanName);
        return bean;
    }
}
