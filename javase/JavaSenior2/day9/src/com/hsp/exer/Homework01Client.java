package com.hsp.exer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * @author yangda
 * @description: TCP 编程 客户端 字符流
 * @create 2022-12-03-17:13
 */
public class Homework01Client {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(InetAddress.getLocalHost(), 8888);

        //得到socket相关的 输出流
        OutputStream outputStream = socket.getOutputStream();

        //客户端发送“name” 服务端接收到后 返回“我是nova”
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));

//        bufferedWriter.write("name");
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        while (flag) {
            System.out.println("请输入你的问题：");
            String question = scanner.next();
            bufferedWriter.write(question);
//        bufferedWriter.write("age");
            bufferedWriter.newLine();//设置结束标记
            bufferedWriter.flush();//字符流，必须进行刷新，将数据写入数据通道中
            //客户端发送“hobby” 服务端接收到后 返回“编写java程序”
            System.out.println("卡在readLine上面");
            String line1 = bufferedReader.readLine();//读不到换行符,就会一直等着  阻塞   读不到结束标记就不会往下走！！！！
            System.out.println("卡在readLine下面");
            System.out.println(line1);
            if ("再见".equals(question)) {
                flag = false;
            }

        }


        //关闭最外层流
        bufferedWriter.close();
        bufferedReader.close();
        socket.close();//关闭资源


    }
}
