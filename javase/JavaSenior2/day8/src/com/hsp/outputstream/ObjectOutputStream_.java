package com.hsp.outputstream;

import java.io.*;

/**
 * @author yangda
 * @description: 序列化
 * 1.序列化就是在保存数据时，保存数据的值和数据类型
 * 2.反序列化就是在恢复数据时，恢复数据的值和数据类型
 * 3.需要让某个对象支持序列化机制，则必须让其类是可序列化的，为了让某个类是可序列化的，该类必须实现如下两个接口之一：
 * Serializable //这是一个标记接口，没有方法
 * Externalizable //该接口有方法需要实现，因此我们一般实现Serializable
 *
 *
 *
 *
 * @create 2022-11-28-9:14
 */
public class ObjectOutputStream_ {
    public static void main(String[] args) throws IOException {

        //序列化后，保存的文本格式，不是纯文本，而是按照他的格式来保存
        String filePath = "e:/data.dat";

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
        //序列化数据到 "e:/data.dat"
        oos.writeInt(100);// int -> Integer(实现了Serializable)
        oos.writeBoolean(false);// boolean -> Boolean(实现了Serializable)
        oos.writeChar('a');// char -> Char (实现了Serializable)
        oos.writeDouble(2.2);// double -> Double (实现了Serializable)
        oos.writeUTF("杨达在学习");//String
        //保存一个Dog对象
        oos.writeObject(new Dog("旺财",3,"日本","红"));//.NotSerializableException

        //关闭流，这里不关闭也可以写入
        oos.close();

        System.out.println("数据保存完毕(数据保存完毕(序列化形式))");


    }
}

