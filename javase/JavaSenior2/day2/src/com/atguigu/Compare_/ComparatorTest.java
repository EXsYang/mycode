package com.atguigu.Compare_;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yangda
 * @description:定制排序
 * @create 2022-11-11-17:00
 */
public class ComparatorTest {
    public static void main(String[] args) {


        Goods goods1 = new Goods("dec",233);
        Goods goods2 = new Goods("ccv",23);
        Goods goods3 = new Goods("ccv",33);

        Goods[] goods = new Goods[4];
        goods[0] = goods1;
        goods[1] = goods2;
        goods[3] = goods3;
        goods[2] = new Goods("aaae",3);

        //排序借助了Arrays.sort()
        Arrays.sort(goods, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                if (o1 instanceof Goods && o2 instanceof Goods){
                    Goods g1 = (Goods) o1;
                    Goods g2 = (Goods) o2;
                if (g1.getName().equals(g2.getName())){
//                    if ( o11.getPrice() > o21.getPrice()){
//                        return 1;
//                    }
                return -Integer.compare(g1.getPrice(),g2.getPrice());
                }else {
                    return g1.getName().compareTo(g2.getName());
                }

                }


                return 0;
            }
        });//.ClassCastException: com.atguigu.Comparable_.Goods cannot be cast to java.lang.Comparable
        System.out.println(Arrays.toString(goods));//[Goods{name='毛巾', price=3}, Goods{name='裤衩', price=23}, Goods{name='校服', price=233}]

    }
}
