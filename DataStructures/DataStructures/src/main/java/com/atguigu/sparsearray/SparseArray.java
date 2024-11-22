package com.atguigu.sparsearray;

import java.io.PrintStream;

/**
 * @author yangda
 * @create 2024-11-21-20:47
 * @description:
 *
 * 稀疏数组
 *
 */
public class SparseArray {

    public static void main(String[] args) throws Exception {
        // 设置控制台输出编码为GBK
        // System.setOut(new PrintStream(System.out, true, "GBK")); //在Cursor中使用这个，将Cursor的Terminal（PowerShell）设置为GBK

        System.setOut(new PrintStream(System.out, true, "utf-8"));  //在idea中使用这个

        int [][] chessArr1 = new int[11][11];

        // 1 表示黑子 2 表示白子
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        // 输出原始的二维数组
        System.out.println("原始的二维数组这里是中文啊~~");
        for (int[] row : chessArr1) {
            for (int data : row) {
                // System.out.print(data + " ");

                // %d\t 是一个格式化字符串，用于输出整数值。
                //
                // %d 是整数的格式化符号。
                // \t 是一个特殊字符，表示一个制表符（tab）。
                System.out.printf("%d\t",data); // \t 表示制表符();  当你使用 "%d\t" 在 printf 函数中时，它会输出整数值后面跟一个制表符。
            }
            System.out.println();
        }




        // for (int i = 0; i < 11; i++) {
        //     for (int j = 0; j < 11; j++) {
        //         if (chess[i][j] != 0) {
        //             System.out.print("("+i+","+j+")");
        //         }
        //     }
        // }









    }

}
