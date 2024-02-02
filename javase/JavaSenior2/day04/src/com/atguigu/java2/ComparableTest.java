package com.atguigu.java2;

import java.util.Arrays;

/**
 * @author yangda
 * @create 2022-06-05-16:44
 */
public class ComparableTest {

    public static void main(String[] args) {
    Goods[] foo = new Goods[6];
        foo[2] = new Goods("huawei",35);
        foo[4] = new Goods("oppo",37);
        foo[0] = new Goods("li",33);
        foo[3] = new Goods("dai",36);
        foo[1] = new Goods("sanxing",34);
        foo[5] = new Goods("tanxing",34);
        Arrays.sort(foo);//默认从小到大排序
        // Arrays.sort()底层用到了compareTo()方法，相当于多态，传到Object的Goods,这里又重写了
        System.out.println("直接打印："+ foo);//默认从小到大排序
//System.out.println(Arrays.sort(foo));//Cannot resolve method 'println(void)':无法解析方法“println(void)”
        //sort()方法返回值为空，打印不出来

        System.out.println("数组toString:"+Arrays.toString(foo));


    }



}
