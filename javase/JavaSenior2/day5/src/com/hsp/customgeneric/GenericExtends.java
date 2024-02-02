package com.hsp.customgeneric;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2022-11-19-18:07
 */
public class GenericExtends {
    public static void main(String[] args) {

        Object o = new String("ssss");

        //泛型没有继承性
//        List<Object> list = new ArrayList<String>();

        List<String> list = new ArrayList<String>();

        list.add("yangda");
        list.add("dd");
        list.add("hsp");

        print(list);

        System.out.println(list);

        List<?> list1 = null;

//        print(list1);//NullPointerException


//        list1.add(null);//.NullPointerException
        list1 = list;
        //添加(写入)：对于List<?>就不能向其内部添加数据
        //除了添加null以外
//        list1.add("yang");

        list1.add(null);
        System.out.println(list1);//[yangda, dd, hsp, null]

        //获取（读取）：允许读取数据，读取的数据类型为Object
        Object o1 = list1.get(0);
        System.out.println(o1);


    }

    public static void print(List<?> list){
        Iterator<?> iterator = list.iterator();
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            System.out.println(obj);
        }


    }


    // ? 任何类型都可以接收
    public static void printCollection1(List<?> c) {
        for (Object object : c) {//通配符，取出时，就是Object
            System.out.println(object);
        }
    }

    //extends F 表示上限 可以接收F 及F的子类
    public static void printCollection2(List<? extends F> c){
            for (Object object : c) {//通配符，取出时，就是Object
                System.out.println(object);
            }
        }
    //super G 表示下限 可以接受G 及父类，不限于直接父类
    public static void printCollection3(List<? super G> c){
        for (Object object : c) {//通配符，取出时，就是Object
            System.out.println(object);
        }
    }



}

class F{

}
class G extends F{

}
class H extends G{

}