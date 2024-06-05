package com.atguigu.jvm;

public class FinalizeTest {

    public FinalizeTest(){
        System.out.println("&&&&&&&&& T2 constructor &&&&&&&&&");
    }

    //面试题： final  finally  finalize
    @Override
    protected void finalize() throws Throwable {
        System.out.println("&&&&&&&&& T2 finalize &&&&&&&&&");
        super.finalize();
    }

    //System.gc();调用后，是否会立即执行垃圾回收？ 不会！
    public static void main(String[] args) {
        FinalizeTest t2 = new FinalizeTest();
        t2 = null;
        System.gc();
        System.out.println("Hello,World...");
    }
}
