package com.atguigu.list_;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2022-11-14-17:20
 */
public class ArrayListSource {
    public static void main(String[] args) {

        //使用无参构造器创建ArrayList对象
        ArrayList list = new ArrayList();
        // List list = new ArrayList();
//        ArrayList arrayList1 = new ArrayList(8);
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }

        for (int i = 11; i <= 15 ; i++) {
            list.add(i);
        }

        list.add(100);
        list.add(200);
        list.add(null);

        //list.get(0);




    }
}
