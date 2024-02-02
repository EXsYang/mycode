package com.atguigu.System;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author yangda
 * @description:
 * @create 2022-11-11-14:25
 */

public class System_ {
    public static void main(String[] args) {
        //exit()退出主程序
        System.out.println("ok1");
//        System.exit(0);
        //1. exit(0) 表示程序退出
        //2. 0 表示一个状态，正常的状态
//        System.exit(0);
        System.out.println("ok2");//不会输出的

//  * @param   src      the source array.
//        源数组
//  * @param   srcPos   starting position in the source array.
//        从源数组第srcPos位置开始复制
//  * @param   dest     the destination array.
//            dest 目标数组
//  * @param   destPos  starting position in the destination data.
//        目标数组的目标位置
//  * @param   length   复制几个，个数the number of array elements to be copied.
//        public static native void arraycopy(Object src,  int  srcPos,
//        Object dest, int destPos,
//        int length);
        int[] src = new int[]{2,3,4};
        int[] dest = new int[3];
        System.out.println(Arrays.toString(src));
        System.arraycopy(src,0,dest,0,3);
        System.out.println(Arrays.toString(dest));

        System.out.println(System.currentTimeMillis());

//        System.gc();


    }
}
