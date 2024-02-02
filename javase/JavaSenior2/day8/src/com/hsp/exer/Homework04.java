package com.hsp.exer;

import java.io.*;
import java.util.Properties;

/**
 * @author yangda
 * @description:
 * @create 2022-11-29-16:13
 */
public class Homework04 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String proPath = "src/dog.properties";
        Properties properties = new Properties();
//        properties.setProperty("name","tom");
//        properties.setProperty("age","5");
//        properties.setProperty("color","red");
//        properties.store(new FileWriter(proPath),null);
//        properties.list(System.out);

//        properties.load(new FileReader(proPath));


        Dog dog = new Dog();
        properties.load(new FileInputStream(proPath));//得到文件信息

        dog.setAge(Integer.parseInt(properties.getProperty("age")));
        dog.setColor(properties.getProperty("color"));
        dog.setName(properties.getProperty("name"));

        properties.list(System.out);

        System.out.println("dog 对象：" + dog);

        String dataPath = "e:/dog.dat";
        //转换流，针对的是 内层字节流，外层字符流
        //OutputStreamWriter gbk = new OutputStreamWriter(new FileOutputStream(dataPath), "gbk");
        //转换流没有提供 writeObject()

        //序列化，针对于内层是字节流，外层也是字节流
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataPath));
        oos.writeObject(dog);
        oos.close();
        System.out.println("数据保存成功(序列化形式)");

        //反序列化
        System.out.println();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dataPath));
//        System.out.println(ois.readInt());
//        System.out.println(ois.readUTF());
//        System.out.println(ois.readUTF());
        Object dog1 = ois.readObject();//返回一个Object
        Dog d = (Dog) dog1;
        System.out.println("反序列化后dog：" + d);

        ois.close();


//        properties.setProperty();

//        properties.getProperty()




    }
}
class Dog implements Serializable {
    String name;
    int age;
    String color;

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Dog() {
        this.name = name;
        this.age = age;
        this.color = color;
    }
}