package com.atguigu.java1;

/**
 * @author shkstart
 * @create 2020-09-06 21:07
 */
public class JavapTest {
    private int num;
    boolean flag;
    protected char gender;
    public String info;

    public static final int COUNTS = 1;
    static{
        String url = "www.atguigu.com";
    }
    {
        info = "java";
    }
    public JavapTest(){

    }
    private JavapTest(boolean flag){
        this.flag = flag;
    }
    private void methodPrivate(){

    }
    int getNum(int i){
        return num + i;
    }

    // 源码层面getNum(int i)相同，返回值不同，不可以当作方法重载的条件，即不可以共存会报错
    // boolean getNum(int i){
    //     return false;
    // }


    protected char showGender(){
        return gender;
    }
    public void showInfo(){
        int i = 10;
        System.out.println(info + i);
    }
}
