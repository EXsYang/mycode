package com.atguigu.jps;

import java.util.Scanner;

/**
 * @author shkstart
 * @create 14:57
 *
 * -Xms100m -Xmx100m
 *
 *
 * -XX:-UsePerfData 作用:
 * 如果某Java进程关闭了默认开启的该参数，
 * 那么jps命令以及jstat命令将无法探知到该Java进程!
 */
public class ScannerTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String info = scanner.next();
    }
}
