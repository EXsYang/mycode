package com.hspedu.spring.bean;

/**
 * @author yangda
 * @description: JavaBean/Entity
 * @create 2023-08-29-21:08
 */
public class Monster {

    private Integer monsterId;
    private String name;
    private String skill;

    public Monster(Integer monsterId, String name, String skill) {
        //System.out.println("Monster 全参构造器被调用...");
        this.monsterId = monsterId;
        this.name = name;
        this.skill = skill;
    }

    //无参构造器一定要写，Spring反射创建对象时，需要使用
    public Monster() {
    }

    public Integer getMonsterId() {
        return monsterId;
    }

    public void setMonsterId(Integer monsterId) {
        this.monsterId = monsterId;
    }

    public String getName() {
        return name;
    }

    // 一定要给set方法
    // 1.当我们给某个bean对象设置属性的时候
    // 2.底层是使用对应的setter方法完成的, 比如setName()
    // 3.如果没有这个方法，就会报错
    public void setName(String name) {
        this.name = name;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "monsterId=" + monsterId +
                ", name='" + name + '\'' +
                ", skill='" + skill + '\'' +
                '}';
    }

    //private void init() {
    //    System.out.println("Monster init...");
    //}
    //private void destroy() {
    //
    //}
}
