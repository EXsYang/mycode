package com.hsp.arrays;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author yangda
 * @description:
 * @create 2022-11-19-9:40
 */
public class ArraysSort {

    public static void main(String[] args) {
        Integer[] integer = new Integer[]{2,4,3,44,5,-5,-66,-3};

        System.out.println(integer);//[Ljava.lang.Integer;@677327b6
        System.out.println(Arrays.toString(integer));//[2, 4, 3, 44, 5]
//        Arrays.toString 源码：
//        public static String toString(Object[] a) {
//            if (a == null)
//                return "null";
//
//            int iMax = a.length - 1;
//            if (iMax == -1)
//                return "[]";
//
//            StringBuilder b = new StringBuilder();
//            b.append('[');
//            for (int i = 0; ; i++) {
//                b.append(String.valueOf(a[i]));
//                if (i == iMax)
//                    return b.append(']').toString();
//                b.append(", ");
//            }
//        }

        Arrays.sort(integer, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                //方式一：
//                if (o1 > o2){
//                    return 1;
//                }else if (o1 < o2){
//                    return -1;
//                }else {
//                    return 0;
//                }
                //或者直接返回 o1 和 o2 的差 也一样
                //方式二：
                return o1 - o2;

//                最终会进入binarySort()方法
//                private static <T> void binarySort(

//                while (left < right) {
//                    int mid = (left + right) >>> 1;
                        //下面这里进行了接口编程+动态绑定+匿名内部类综合使用
//                    if (c.compare(pivot, a[mid]) < 0)//影响最终排序顺序
//                        right = mid;
//                    else
//                        left = mid + 1;
//                }
            }
        });





        System.out.println("排序后：");
        System.out.println(Arrays.toString(integer));





















    }


}
