package com.atguigu.collection_;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author yangda
 * @description:Collection常用方法测试
 * @create 2022-11-14-8:35
 */
public class ArrayList_ {
    public static void main(String[] args) {
        ArrayList ss = new ArrayList(0);
        ss.add("staing");//数组长度是1
        ss.add("staing");//数组长度是2
        ss.add("staing");
        System.out.println(ss);//[staing, staing, staing]
        System.out.println(ss.get(1));//staing
        System.out.println(ss.size());//3
        System.out.println("******************");

        ArrayList arrayList = new ArrayList();

        System.out.println("****************" + arrayList.isEmpty()); //****************true

        System.out.println("获取元素个数= "+arrayList.size());// 获取元素个数= 0

        arrayList.add("杨达"); // 添加一个元素后 底层会自动扩容到10 但是获取size时 是按照存放元素的个数获取的 此处size=1
        arrayList.add(2);
        arrayList.add('z');
        arrayList.add('z');
        arrayList.add('z');
        arrayList.add('z');
        arrayList.add('z');
        arrayList.add(4);//自动装箱
        arrayList.add(4);
        arrayList.add(4);
        arrayList.add(4);
        arrayList.add(6.0);
        arrayList.add(6.0);
        System.out.println(arrayList);//[杨达, 2, z, z, 4, 4, 4, 4, 6.0, 6.0]


        arrayList.remove(4);//索引位置
        arrayList.remove(4);//索引位置
        System.out.println(arrayList);//[杨达, 2, z, z, 4, 4, 4, 6.0, 6.0]

        arrayList.remove(6);//这里的6是指的索引 [杨达, 2, z, z, 4, 4, 6.0, 6.0]
        System.out.println(arrayList);

        //判断当前集合是否包含obj,调用obj对象所在类的equals()
        boolean contains = arrayList.contains(6);//分别调用集合元素的equals()
        System.out.println(contains);

        System.out.println(arrayList.size());//获取元素个数

        System.out.println("arrayList.isEmpty()= "+arrayList.isEmpty());

        arrayList.clear();//集合对象arrayList还是有的，元素没有了，
        //不是arrayList = null;  调用isEmpty()不会空指针
        System.out.println(arrayList); // []
        System.out.println("****************" + arrayList.isEmpty()); //****************true

        System.out.println("获取元素个数= "+arrayList.size());// 获取元素个数= 0

        ArrayList arrayList1 = new ArrayList();
//        arrayList1.add("yangda","ee");//element:元素
        arrayList1.add(0,"ee");//element:元素
        arrayList1.add(1,"问问");//element:元素
        arrayList1.add(2,"DF");//element:元素

//        arrayList1.add(4,"DF");//IndexOutOfBoundsException: Index: 4, Size: 3     需要连着添加，超出报异常！！！！

        System.out.println(arrayList1);

        arrayList.addAll(arrayList1);

        System.out.println(arrayList);//[杨达, 2, z, z, 4, 4, 6.0, 6.0, ee, 问问, DF]

//        arrayList.addAll(11,arrayList1);//[杨达, 2, z, z, 4, 4, 6.0, 6.0, ee, 问问, DF, ee, 问问, DF]
//        System.out.println(arrayList);

//        arrayList.addAll(12,arrayList1);//.IndexOutOfBoundsException: Index: 12, Size: 11   需要连着添加，超出报异常！！！
        System.out.println(arrayList);

        System.out.println(arrayList.containsAll(arrayList1));//true

        System.out.println(arrayList.removeAll(arrayList1));

        System.out.println(arrayList);//[杨达, 2, z, z, 4, 4, 6.0, 6.0]




    }

    /*
    测试迭代器
    */
    @Test
    public void testIterator(){

        ArrayList arr = new ArrayList();
        arr.add(new Book("白夜行",44,"东野圭吾"));
        arr.add(new Book("心",55,"夏目漱石"));
        arr.add(new Book("雪国",66,"川端康成"));
//        System.out.println(arr);

        Iterator iterator = arr.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            System.out.println(next);
        }


    }
    @Test
    public void testforeach(){
        ArrayList arr = new ArrayList();
        arr.add(new Book("白夜行",44,"东野圭吾"));
        arr.add(new Book("心",55,"夏目漱石"));
        arr.add(new Book("雪国",66,"川端康成"));

        /*
         * 使用增强for在Collection
         * 底层仍然是迭代器
         * 可以理解为简化版的迭代器
         * 快捷键 I iter
         */
        for(Object book : arr){
            System.out.println("book:\t" + book);
        }

    }
}
class Book{
    String name;
    int price;
    String writer;

    public Book(String name, int price, String writer) {
        this.name = name;
        this.price = price;
        this.writer = writer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", writer='" + writer + '\'' +
                '}';
    }
}

