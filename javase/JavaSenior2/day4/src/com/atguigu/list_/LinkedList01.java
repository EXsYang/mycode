package com.atguigu.list_;

import java.util.LinkedList;

/**
 * @author yangda
 * @description:模拟双向链表
 * @create 2022-11-14-21:29
 */
public class LinkedList01 {
    public static void main(String[] args) {

        Node jack = new Node("jack");
        Node tom = new Node("tom");
        Node hsp = new Node("hsp");

        //连接三个结点，形成双向链表
        //jack -> tom -> hsp
        jack.next = tom;
        tom.next = hsp;

        //hsp -> tom ->jack
        hsp.prev = tom;
        tom.prev = jack;

        Node first = jack;
        Node last = hsp;

        //正着遍历双向链表
//        while (true){
//            if(first == null){
//                break;
//            }
//            System.out.println(first);
//            first = first.next;
//        }

        //倒着遍历
        while (true){
            if(last == null){
                break;
            }
            System.out.println(last);
            last = last.prev;
        }

//        System.out.println(tom);

        //在tom后面插入yd
        Node yd = new Node("yd");
        yd.prev = tom;
        yd.next = hsp;
        tom.next = yd;
        hsp.prev =yd;
        System.out.println("************");
        //正着遍历双向链表
        while (true){
            if(first == null){
                break;
            }
            System.out.println(first);
            first = first.next;
        }

    }
}


//定义一个Node类，Node 对象 表示双向链表的一个结点
class Node {

    public Object item;//真正存放数据
    public Node prev;//指向前一个结点
    public Node next;//指向下一个结点


    public Node(Object item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item='" + item + '\'' +
                '}';
    }
}