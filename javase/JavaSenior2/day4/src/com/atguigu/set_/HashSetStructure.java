package com.atguigu.set_;

/**
 * @author yangda
 * @description:模拟数组+链表结构
 * @create 2022-11-15-11:17
 */
public class HashSetStructure {
    public static void main(String[] args) {

        //创建一个长度为16的Node类型的数组
        Node[] table = new Node[16];

        Node jack = new Node("jack", null);
        table[2] = jack;

        Node tom = new Node("tom", null);
        jack.next = tom;

        Node hsp = new Node("hsp", null);
        tom.next = hsp;

        Node rose = new Node("rose", null);
        table[5] = rose;

        System.out.println();

    }


}
class Node{

    public Object item;
    public Node next;

    public Node(Object item, Node next) {
        this.item = item;
        this.next = next;
    }

    public Node(Object item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Node{" +
                "item=" + item +
                '}';
    }
}