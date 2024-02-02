package com.hsp.reflection;

/**
 * @author yangda
 * @description:
 * @create 2022-12-13-10:26
 */
public class Person {
    public String name = "tom";
    private int age = 24;

    public void eat(){
        System.out.println("tom在吃饭");
    }

    public void work(String work){
        System.out.println(work);

    }

    private static void cry(int a){
        System.out.println("tom哭了" + a + "次");
    }
    private static void cry2(){
        System.out.println("jack哭了");
    }




}
