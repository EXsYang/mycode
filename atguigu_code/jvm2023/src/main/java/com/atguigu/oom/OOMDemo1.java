package com.atguigu.oom;

import java.util.ArrayList;
import java.util.List;

public class OOMDemo1 {

    //-Xms8m -Xmx8m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=D:\myDump
    public static void main(String[] args) {
        /*List list = new ArrayList();
        while (true){
            list.add(new Dog());
        }*/

        Integer i1 = 127;
        Integer i2 = 127;
        System.out.println(i1 == i2);
    }
}

class Dog{
    byte[] aByte = new byte[10*1024*1024];
}


