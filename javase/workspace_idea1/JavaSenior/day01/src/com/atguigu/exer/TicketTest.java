package com.atguigu.exer;

/**
 * @author yangda
 * @create 2021-11-24-21:07
 */
public class TicketTest {
    public static void main(String[] args) {
        Window w1 = new Window();
        Window w2 = new Window();
        Window w3 = new Window();
        w1.setName("窗口一");
        w2.setName("窗口二");
        w3.setName("窗口三");
        w1.start();
        w2.start();
        w3.start();

    }
}
