package com.atguigu.jstat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shkstart
 * @create 17:49
 * -Xms60m -Xmx60m -XX:SurvivorRatio=8
 *
 * -Xms600m -Xmx600m -XX:-UseAdaptiveSizePolicy
 */
public class GCTest {
    public static void main(String[] args) {
        ArrayList<byte[]> list = new ArrayList<>();

        System.out.println("GCTest started...");

        for (int i = 0; i < 1000; i++) {
            byte[] arr = new byte[1024 * 100];//100KB
            list.add(arr);
            try {
                Thread.sleep(1620);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}