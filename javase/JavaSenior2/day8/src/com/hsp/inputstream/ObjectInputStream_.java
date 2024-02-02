package com.hsp.inputstream;

import com.hsp.outputstream.Dog;

import java.io.*;

/**
 * @author yangda
 * @description: 反序列化
 * 注意事项和细节说明
 *
 * 1.读写顺序要一致
 * 2.要求序列化或反序列化对象，需要实现 Serializable
 * 3.序列化的类中建议添加 SerialVersionUID ,为了提高版本的兼容性
 * Serial序列号  Version 版本
 * 如果有SerialVersionUID
 * class Dog implements Serializable{
 *  private String hobby;//有序列化版本时 在Dog类中加了一个属性
 *     //在序列化或反序列化时 加了一个属性，它不会认为是一个全新的类，而是在
 *     //原先的类 的升级版
 *
 *     //serialVersionUID 序列化的版本号 ，提高兼容性
 *     private static final long serialVersionUID = 1L;
 * 4.序列化对象时，默认将里面所有的属性进行序列化，但除了static 或 transient修饰的成员
 * 5.序列化对象时，要求里面属性的类型也需要实现序列化接口
 * 6.序列化具备可继承性，也就是如果某类已经实现了序列化，则它的所有子类也已经默认实现了序列化
 *
 *
 * //正确写入成功，再读取 不会报异常， 写入失败(属性位置有创建对象没有实现序列化接口的NotSerializableException)后再次读取会报异常(.WriteAbortedException)
 *
 *
 * @create 2022-11-28-9:15
 */
public class ObjectInputStream_ {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        String filePath = "e:/data.dat";
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));

        //读取数据
        //1. 读取(反序列化)的顺序需要和保存数据(序列化)的顺序一致
        //2. 否则会出现异常
//        System.out.println(ois.readUTF());//这里多写一个也报异常
        System.out.println(ois.readInt());
        System.out.println(ois.readBoolean());
        System.out.println(ois.readChar());
        System.out.println(ois.readDouble());
        System.out.println(ois.readUTF());//.UTFDataFormatException

        //顺序不一致.OptionalDataException，
        Object dog = ois.readObject();//这里抛出异常 //.InvalidClassException,写入的类和读取的类不一致，需要重新写入一下
        System.out.println("运行类型：" + dog.getClass());
        System.out.println("dog信息：" + dog);//底层 Object -> Dog

        //注意如下问题:
        //dog 是 com.hsp.outputstream.Dog
//        Dog 是com.hsp.inputstream.Dog
//        Dog dog1 = (Dog)dog;//.ClassCastException
//        System.out.println(dog1.getName());

        //这里特别重要的细节
        //1. 如果我们希望调用Dog的方法，需要向下转型
        //2. 需要我们将Dog类的定义，放在可以引用的位置
        Dog dog1 = (Dog) dog;
        System.out.println(dog1.getName());


        ois.close();//关闭外层流即可，底层会自动关闭 FileInputStream 流


    }
}
