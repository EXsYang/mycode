package com.atguigu1.sparsearray;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * @author yangda
 * @create 2024-11-21-20:47
 * @description: 稀疏数组
 */
public class SparseArray {

    public static void main(String[] args) throws Exception {
        // 设置控制台输出编码为GBK
        // System.setOut(new PrintStream(System.out, true, "GBK")); //在Cursor中使用这个，将Cursor的Terminal（PowerShell）设置为GBK

        System.setOut(new PrintStream(System.out, true, "utf-8"));  //在idea或者Windsurf中使用这个

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
        System.out.println("开始保存稀疏数组到文件");
        //创建FileWriter对象
        // fileWriter = new FileWriter(filePath);//默认覆盖操作
        // fileWriter = new FileWriter(filePath,true);//追加操作

        // try (BufferedWriter writer = new BufferedWriter(new FileWriter("sparsearray.txt"))) {
        //     // 遍历稀疏数组并写入文件
        //     for (int[] sparseRow : sparseArray) {
        //         // 将每行数据转换为字符串，用逗号分隔
        //         String line = sparseRow[0] + "," + sparseRow[1] + "," + sparseRow[2];
        //         writer.write(line);
        //         writer.newLine(); // 添加换行符
        //     }
        //     System.out.println("稀疏数组已成功保存到文件");
        // } catch (IOException e) {
        //     System.out.println("保存文件时出错：" + e.getMessage());
        // }



        // DataInputStream/DataOutputStream 的特点:
        // 专门用于处理原始数据类型（如int, long, double等）的二进制读写
        // 直接以二进制格式存储，不需要转换成字符串
        // 读写效率高，文件体积小

        // 为什么不选择其他方式:
        // FileWriter:
        // 只能写入字符，需要将数字转换成字符串
        // 占用空间大（比如数字1需要用"1"存储）
        // 读取时需要解析字符串，效率低
        // FileInputStream:
        // 只能按字节读写，使用不方便
        // 需要手动处理字节转换
        // 没有针对基本数据类型的便捷方法

        // 举个例子：
        // DataOutputStream 写入方式（推荐）
        // DataOutputStream dos = new DataOutputStream(new FileOutputStream("file.dat"));
        // dos.writeInt(12345);  // 直接写入整数，占4字节

        // FileWriter 写入方式（不推荐）
        // FileWriter fw = new FileWriter("file.txt");
        // fw.write("12345");    // 写入字符串，占5字节，还需要额外处理分隔符

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("map.data"))) {
            // 写入数组维度
            dos.writeInt(sparseArray.length);
            dos.writeInt(sparseArray[0].length);
            
            // 写入所有数据
            for (int[] row3 : sparseArray) {
                for (int value : row3) {
                    dos.writeInt(value);
                }
            }
            System.out.println("数据保存成功！");
        } catch (IOException e) {
            System.out.println("保存失败：" + e.getMessage());
        }
        

        System.out.println("\n开始从磁盘读取数据并还原数组");
        // 从磁盘读取稀疏数组
        int[][] loadedSparseArray = null;
        try (DataInputStream dis = new DataInputStream(new FileInputStream("map.data"))) {
            // 读取数组维度
            // writeInt() 写入的数据必须用 readInt() 读取
            int rows = dis.readInt(); // 读取第一个int值，表示稀疏数组的行数
            int cols = dis.readInt(); // 读取第二个int值，表示稀疏数组的列数

            /**
             * 顺序读取：
                DataInputStream 维护一个内部指针
                每次读取后，指针自动移动到下一个位置
                读取顺序必须与写入顺序完全匹配
                
             * try (DataInputStream dis = new DataInputStream(new FileInputStream("map.data"))) {
            // 假设文件中的数据是: [3][3][11][11][2][1][2][1][2][3][2]
            
                    int rows = dis.readInt();    // 读取第1个int，得到3，指针自动移到下一个位置
                    int cols = dis.readInt();    // 读取第2个int，得到3，指针继续移动
            
                    // 文件指针会自动向后移动，就像一个不断前进的光标
                    loadedSparseArray = new int[rows][cols];
                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {
                            loadedSparseArray[i][j] = dis.readInt(); // 每次读取都会自动移动到下一个int
                        }
                    }
                }

             */
            
            // 创建数组并读取数据
            loadedSparseArray = new int[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    loadedSparseArray[i][j] = dis.readInt();
                }
            }
            System.out.println("数据读取成功！");
        } catch (IOException e) {
            System.out.println("读取失败：" + e.getMessage());
        }

        // 打印读取的稀疏数组
        System.out.println("从磁盘读取的稀疏数组：");
        for (int[] row4 : loadedSparseArray) {
            for (int data : row4) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 将读取的稀疏数组还原成原始的二维数组
        int[][] restoredArray = new int[loadedSparseArray[0][0]][loadedSparseArray[0][1]];
        
        // 从稀疏数组第二行开始还原数据
        for (int i = 1; i < loadedSparseArray.length; i++) {
            restoredArray[loadedSparseArray[i][0]][loadedSparseArray[i][1]] = loadedSparseArray[i][2];
        }

        // 打印还原后的二维数组
        System.out.println("\n从磁盘读取并还原后的原始二维数组：");
        for (int[] row5 : restoredArray) {
            for (int data : row5) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }












    }

}
