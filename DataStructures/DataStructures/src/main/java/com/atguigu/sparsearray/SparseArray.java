package com.atguigu.sparsearray;

import java.io.PrintStream;

/**
 * @author yangda
 * @create 2024-11-21-20:47
 * @description: 稀疏数组
 */
public class SparseArray {

    public static void main(String[] args) throws Exception {
        // 设置控制台输出编码为GBK
        System.setOut(new PrintStream(System.out, true, "GBK")); //在Cursor中使用这个，将Cursor的Terminal（PowerShell）设置为GBK

        // System.setOut(new PrintStream(System.out, true, "utf-8"));  //在idea或者Windsurf中使用这个

        int[][] chessArr1 = new int[11][11];

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
                System.out.printf("%d\t", data); // \t 表示制表符();  当你使用 "%d\t" 在 printf 函数中时，它会输出整数值后面跟一个制表符。
            }
            System.out.println();
        }


        // 将二维数组转换成稀疏数组

        // 定义sum,记录有效数据的个数
        int sum = 0;

        for (int[] row : chessArr1) {
            for (int data : row) {
                // 判断数据是否有效，即非零
                if (data != 0) {
                    //该数据为有效数据
                    sum++;
                }
            }
        }
        System.out.println("有效数据的个数sum= " + sum);

        // 得到稀疏数组的有效数据个数后，就可以创建稀疏数组了
        int[][] sparseArray = new int[sum + 1][3];   // new int[sum+1][3];

        // 往稀疏数组中填充要保存的数据
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;

        // 将原始数组中的有效数据的行，列，值 分别填入。
        // 可以做成变量

        // 定义一个计数器count,记录是第几个有效数据.//count 用于记录是第几个非 0 数据
        int count = 0;

        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                // 判断数据是否有效，即非零
                if (chessArr1[i][j] != 0) {
                    //计数器加1
                    count++;
                    // 将此时的角标数据填入稀疏数组对应的位置
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr1[i][j];
                }
            }
        }


        // 输出稀疏数组
        System.out.println("打印稀疏数组数组如下：");
        for (int[] row : sparseArray) {
            for (int data : row) {
                // System.out.print(data + " ");

                // %d\t 是一个格式化字符串，用于输出整数值。
                //
                // %d 是整数的格式化符号。
                // \t 是一个特殊字符，表示一个制表符（tab）。
                System.out.printf("%d\t", data); // \t 表示制表符();  当你使用 "%d\t" 在 printf 函数中时，它会输出整数值后面跟一个制表符。
            }
            System.out.println();
        }


        // 将稀疏数组转为原始的二维数组
        // 先读取稀疏数组的第一行的前两个元素，得到要恢复的原始二维数组有几行几列
        // Row Column   行 列

        //定义一个原始数组
        int[][] originalArray = null;

        // 遍历稀疏数组，并将将数据转为原始二维数组

        int row = 0;
        int col = 0;

        for (int i = 0; i < sparseArray.length; i++) {

            if (i == 0) {
                row = sparseArray[i][0];
                col = sparseArray[i][1];

                // 根据稀疏数组中保存的几行几列，创建出原始数组 originalArray
                originalArray = new int[row][col];

            }else{
                // 说明下面该存数据了
                int flag = 0;
                int row2 = 0;
                int col2 = 0;

                for (int j = 0; j < sparseArray[i].length; j++) {
                    flag++;

                    // 将稀疏数组保存的3种数据(固定的，这里就是保存三种数据)依次按照 行 列 值的顺序赋值给原始数组
                    // 取到的第一个数据，就是棋子在原始数组中的行，即元素角标
                    if (flag == 1) {
                        //说明循环第1次，取得是第1个值，即 行
                        row2 = sparseArray[i][j];
                    } else if (flag == 2) {
                        //说明循环第2次，取得是第2个值，即 列
                        col2 = sparseArray[i][j];
                    } else if (flag == 3) {
                        //说明循环第3次，取得是第3个值，即 保存的值(表示黑子还是白子)
                        originalArray[row2][col2] = sparseArray[i][j];
                    } else {
                        System.out.println("循环出错");
                    }
                }
            }

        }


        System.out.println("由稀疏数组还原后的原始数组如下：");

        for (int i = 0; i < originalArray.length; i++) {
            for (int j = 0; j < originalArray[i].length; j++) {
                System.out.print(originalArray[i][j] + "\t");
            }
            System.out.println();
        }


        // 文件io操作
        





    }

}
