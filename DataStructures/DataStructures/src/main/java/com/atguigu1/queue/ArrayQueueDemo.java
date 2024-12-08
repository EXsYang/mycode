package com.atguigu1.queue;

/**
 * @author yangda
 * @create 2024-11-25-0:04
 * @description:
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {


    }
}


// 使用数组模拟队列-编写一个ArrayQueue类
class ArrayQueue {

    //定义队列的属性
    private int maxSize;
    private int front; //队列头
    private int rear; //队列尾
    private int[] queue; //队列数组

    public ArrayQueue(int arrMaxSize) {
        maxSize = arrMaxSize;
        queue = new int[maxSize];
        front = -1; // 指向队列头部，分析出front是指向队列头的前一个位置.(不包含)
        rear = -1; // 指向队列尾，指向队列尾的数据(即就是队列最后一个数据，包含的)
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列，注意rear 是指向队列尾(即就是队列最后一个数据，包含的)
    public void addQueue(int n) {
        // 判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据~");
            return;
        }
        rear++; // 让rear 后移
        queue[rear] = n;
    }

    // 获取队列的数据, 出队列
    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列空的，不能取数据~");
        }
        front++; // 让front 后移
        return queue[front];
    }

    // 显示队列的所有数据，注意不是取出数据
    public void showQueue() {
        // 遍历
        if (isEmpty()) {
            System.out.println("队列空的，没有数据~~");
            return;
        }
        for (int i = 0; i < queue.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, queue[i]);
        }

    }

    // public int peekQueue() {
    public int headQueue() {
        // 判断
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        // 返回队列中的头数据
        return queue[front + 1];
    }





}