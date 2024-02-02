package com.hspedu.tomcat;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author yangda
 * @description: 这是第一个版本的tomcat，可以完成，接受浏览器的请求，并返回信息
 * @create 2023-05-28-16:02
 */
public class HspTomcatV1 {

    public static void main(String[] args) throws IOException {
        String requestHeadersStr = "GET /calServlet?num1=33&num2=22 HTTP/1.1\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7\n" +
                "Accept-Encoding: gzip, deflate, br\n" +
                "Accept-Language: zh-CN,zh;q=0.9\n" +
                "Cache-Control: no-cache\n" +
                "Connection: keep-alive\n" +
                "Cookie: Idea-65e6711a=d94fa027-a2d1-4fcc-9756-" +
                "1cb426f6a580\n" +
                "Host: localhost:8080\n" +
                "Pragma: no-cache\n" +
                "Referer: http://localhost:8080/cal.html\n" +
                "Sec-Fetch-Dest: document\n" +
                "Sec-Fetch-Mode: navigate\n" +
                "Sec-Fetch-Site: same-origin\n" +
                "Sec-Fetch-User: ?1\n" +
                "Upgrade-Insecure-Requests: 1\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36\n" +
                "sec-ch-ua: \"Google Chrome\";v=\"113\", \"Chromium\";v=\"113\", \"Not-A.Brand\";v=\"24\"\n" +
                "sec-ch-ua-mobile: ?0\n" +
                "sec-ch-ua-platform: \"Windows\"\n";

        String responseHeadersStr = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html;charset=utf-8\r\n\r\n";

        //创建一个ServerSocket 在8080端口监听
        ServerSocket serverSocket = new ServerSocket(8080);

        //如果服务器Socket没有关闭，就一直在8080端口监听，等待连接
        System.out.println("==========服务器在8080端口等待连接============");
        while (!serverSocket.isClosed()){

            Socket socket = serverSocket.accept();
            System.out.println("连接成功...");

            //拿到Socket 相关的输入流
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
            String buf = "";
            while ((buf = bufferedReader.readLine()) != null){
                if (buf.length() == 0){
                    break;
                }
                //将读取到的信息打印到控制台
                System.out.println(buf);
            }

            // 给浏览器回送数据
            OutputStream outputStream = socket.getOutputStream();
            String resp = responseHeadersStr + "<h1>hello world hsp</h1>";
            //outputStream.write(requestStr.getBytes()); //这里注意不要写错
            outputStream.write(resp.getBytes());
            System.out.println("回送的数据：" + resp);

            //刷到数据通道中
            outputStream.flush();
            //关闭输出流
            outputStream.close();
            //关闭输入流
            bufferedReader.close();
            //关闭socket
            socket.close();



        }









    }


}
