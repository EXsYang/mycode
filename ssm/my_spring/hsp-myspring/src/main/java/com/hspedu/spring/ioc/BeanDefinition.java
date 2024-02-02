package com.hspedu.spring.ioc;

/**
 * @author yangda
 * @description: 用于封装/记录Bean的信息[1. scope 2 Bean对应的Class对象, 反射可以生对应的对象]
 * @create 2023-09-13-16:32
 */
public class BeanDefinition {

    private String Scope;
    private Class clazz;
    //可以根据需求，进行扩展


    public String getScope() {
        return Scope;
    }

    public void setScope(String scope) {
        Scope = scope;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "Scope='" + Scope + '\'' +
                ", clazz=" + clazz +
                '}';
    }
}
