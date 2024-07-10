package com.atguigu.java;

/**
 * @author shkstart
 * @create 2020-09-08 10:13
 *
 * 指令6：操作数栈管理指令
 */
public class StackOperateTest {

    public void print(){
        Object obj = new Object();
       String info = obj.toString(); //这里会存储在局部变量表中 `astore_2`



        // 这里不会存储在局部变量表中，
        // String类型的返回值没有用到【这里是没有使用变量接收】
        // 虽然返回来了，但是返回来的数据没有被使用，
        // ，而是直接弹出栈 `pop`

        // obj.toString();

    }
    //类似的，返回了数据，但是没有用。就会直接弹出栈，
    // 又因为是long类型的，需要两个槽位slot，所以是: pop2
    public void foo(){
        bar();
    }
    public long bar(){
        return 0;
    }

    //会出现操作数栈中的插入操作
    public long nextIndex() {
        return index++;
    }

    private long index = 0;
}
