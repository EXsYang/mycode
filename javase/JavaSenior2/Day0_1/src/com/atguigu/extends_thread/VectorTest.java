package com.atguigu.extends_thread;

import java.util.Vector;

/**
 * @author yangda
 * @create 2022-08-13-10:15
 */
public class VectorTest {
    public static void main(String[] args) {
        Vector vector = new Vector();
        for (int i = 1; i <=10 ; i++) {
            vector.add(i);
        }
        vector.add(100);
        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);
        vector.add(null);
        System.out.println("null");
    }
}
