package com.atguigu.oom;

import java.util.Random;

public class OOMDemo2 {
    //VM参数：	-Xms8m -Xmx8m -XX:+PrintGCDetails
    public static void main(String[] args) {
        String str = "www.atguigu.com";
        while (true) {
            str += str + new Random().nextInt(88888888) + new Random().nextInt(999999999);
        }
    }
}
