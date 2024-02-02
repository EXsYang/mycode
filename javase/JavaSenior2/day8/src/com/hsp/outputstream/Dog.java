package com.hsp.outputstream;

import java.io.Serializable;

/**
 * @author yangda
 * @description:
 * @create 2022-11-28-10:13
 */
//class Dog{
public class Dog implements Serializable {

    String name;
    int age;
// 4.序列化对象时，默认将里面所有的属性进行序列化，但除了static 或 transient修饰的成员
    static String nation;//国家，民族
    transient String color;//颜色
//    5.序列化对象时，要求里面属性的类型也需要实现序列化接口
//    private Master master1;//如果Master没有实现序列化接口，但是只声明不创建对象，往文件中写时不会报错
    private Master master = new Master();//属性位置，Master 没实现序列化接口，又创建对象，报错.NotSerializableException

//    private String hobby;//有序列化版本时 在Dog类中加了一个属性
    //在序列化或反序列化时 加了一个属性，它不会认为是一个全新的类，而是zai
    //原先的类 的升级版

    //serialVersionUID 序列化的版本号 ，提高兼容性
    private static final long serialVersionUID = 1L;

    public Dog(String name, int age, String nation, String color) {
        this.name = name;
        this.age = age;
        this.nation = nation;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                ", master=" + master +
                 ", static的属性nation=" + nation + "}" ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}

//class Master {
//}
class Master implements Serializable{
}