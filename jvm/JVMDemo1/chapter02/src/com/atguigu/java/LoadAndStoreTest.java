package com.atguigu.java;

import java.util.Date;

/**
 * @author shkstart
 * @create 2020-09-07 21:26
 *
 * 指令1：加载与存储指令
 */
public class LoadAndStoreTest {

    //1.局部变量压栈指令 xload_<n>、xload n
    public void load(int num, Object obj,long count,boolean flag,short[] arr) {
        System.out.println(num);
        System.out.println(obj);
        System.out.println(count);
        System.out.println(flag);
        System.out.println(arr);
    }

    //2.常量入栈指令 const、push、ldc
    public void pushConstLdc() {
        int i = -1; // iconst_m1
        int a = 5; // iconst_5
        int b = 6; // bipush 6
        int c = 127; // bipush 127
        int d = 128; // sipush 128
        int e = 32767; // sipush 32767
        int f = 32768; // ldc #7 <32768>

        boolean g = true; // iconst_1
        boolean h = false; // iconst_0
    }

    public void constLdc() {
        long a1 = 1;
        long a2 = 2;
        float b1 = 2;
        float b2 = 3;
        double c1 = 1;
        double c2 = 2;
        Date d = null;

    }

    //3.出栈装入局部变量表指令
    public void store(int k, double d) {
        int m = k + 2;
        long l = 12;
        String str = "atguigu";
        float f = 10.0F;
        d = 10;
    }

    /**
     * 该方法局部变量表的最大长度是5，
     * 但是jclasslib显示出的`0，1，2`指的是索引，而不是指的槽位slot。
     * 代码块中的局部变量没有显示出来
     *
     *
     *
     * 对于方法 foo(long l, float f)，局部变量表的分配如下：
     *
     * 槽位 0：由于这是一个非静态方法，槽位 0 被 this（当前对象的引用）占据。
     * 槽位 1 和 2：long 类型参数 l 占用两个槽位。
     * 槽位 3：float 类型参数 f 占用一个槽位。
     * 局部变量的分配和槽位复用
     * 接下来是局部变量 i 和 s：
     *
     * 局部变量 i（int类型）可能被分配到槽位 4。
     * 局部变量 s（String类型）随后也可能被分配到槽位 4，因为 i 的作用域已经结束，且其生命周期不与 s 重叠。
     */
    public void foo(long l, float f) {
        {
            int i = 0;
        }
        {
            String s = "Hello, World";
        }

    }

}
