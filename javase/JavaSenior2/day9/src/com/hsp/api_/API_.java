package com.hsp.api_;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author yangda
 * @description: InetAddress 类的使用
 * @create 2022-12-02-14:35
 */
public class API_ {
    public static void main(String[] args) throws UnknownHostException {
        // 1. 获取本机的InetAddress 对象
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(localHost);//F2/192.168.1.3

        // 2.根据指定主机名 获取 InetAddress对象
        InetAddress host1 = InetAddress.getByName("F2");
        System.out.println("host1=" + host1 );//host1=F2/192.168.1.3

        // 3. 根据域名返回 InetAddress对象, 比如 www.baidu.com
        InetAddress host2 = InetAddress.getByName("www.baidu.com");
        System.out.println("host2=" + host2);//host2=www.baidu.com/110.242.68.3

        // 4. 通过 InetAddress 对象，获取对应的地址
        String hostAddress = host2.getHostAddress();//IP 110.242.68.3
        System.out.println("host2对应的主机名/域名=" + hostAddress);//host2对应的主机名/域名=110.242.68.4

        // 5. 通过InetAddress 对象，获取对应的主机名/或者域名
        String hostName = host2.getHostName();
        System.out.println("host2对应的主机名/域名=" + hostName);//host2对应的主机名/域名=www.baidu.com


    }
}
