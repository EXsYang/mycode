package com.hsp.test1;

import org.junit.Test;

/**
 * @author yangda
 * @description:
 * @create 2023-03-27-8:29
 */
public class DayTest1 {
    public static void main(String[] args) {
        label1:
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println(i);
                System.out.println(j);
//                continue;
//                continue label1;
                break;
            }

        }
    }

    @Test
    public void continueTest(){

        for (int i = 0; i < 4; i++) {
            if (true){
                if (true){
                    if (i == 2){
                        continue;
                    }
                    System.out.println(" i= " + i);
                }
            }

        }
    }

}
