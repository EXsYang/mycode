package com.atguigu.java2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yangda
 * @create 2022-06-05-19:14
 */
public class ComparatorTest1 {
    public static void main(String[] args) {
        String[] s = new String[]{"ss","dd","ff","aa"};
        Goods1[] foo = new Goods1[6];
        foo[2] = new Goods1("huawei",35);
        foo[4] = new Goods1("oppo",37);
        foo[0] = new Goods1("li",33);
        foo[3] = new Goods1("dai",36);
        foo[1] = new Goods1("sanxing",34);
        foo[5] = new Goods1("tanxing",34);

        //Arrays.sort(foo);//默认从小到大排序     ClassCastException//没有实现Comparable接口，sort()底层使用了
        //compareTo()方法

        Arrays.sort(foo, new Comparator() {//这一行和下一行书写时会报错，alt+enter重新生成一下就行了！！！
            @Override
            public int compare(Object o1, Object o2) {
                    if (o1 instanceof Goods1 && o2 instanceof Goods1) {
                        Goods1 g1 = (Goods1)o1;
                        Goods1 g2 = (Goods1)o2;
                        if (g1.getName().equals(g2.getName())) {
                            return Double.compare(g1.getPrice(),g2.getPrice());
                        }else{
                            return g1.getName().compareTo(g2.getName());
                        }

                    }
                    throw new RuntimeException("输入的类型不匹配");
                }
            });

        System.out.println(Arrays.toString(foo));
        System.out.println(foo[1]);

        Arrays.sort(s);//默认从小到大排序
        System.out.println(Arrays.toString(s));



    }
}
