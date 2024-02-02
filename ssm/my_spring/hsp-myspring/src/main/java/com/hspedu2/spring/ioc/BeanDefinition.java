package com.hspedu2.spring.ioc;

/**
 * @author yangda
 * @description: 用于封装bean的定义信息
 * @create 2023-09-16-16:35
 */
public class BeanDefinition {
    // bean的clazz对象
    private Class beanClazz;
    // bean的作用范围 单例singleton 还是 多实例prototype
    private String scope;

    public Class getBeanClazz() {
        return beanClazz;
    }

    public void setBeanClazz(Class beanClazz) {
        this.beanClazz = beanClazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "beanClazz=" + beanClazz +
                ", scope='" + scope + '\'' +
                '}';
    }
}
