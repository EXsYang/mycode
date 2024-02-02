
package com.atguigu.extends_thread;
/**
 * @author yangda
 * @description:
 * @create 2022-11-07-16:13
 */

public class WindowTest {
    public static void main(String[] args) {
        //三个窗口卖票问题，100张票
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
class Window extends Thread{
    private static int ticket = 100;

    @Override
    public void run() {
        while (true){
            if (ticket > 0){
                System.out.println(getName() + "卖票，票号:" +ticket);
                ticket--;
            }else{
                break;
            }
//            System.out.println("run");//不加break 一直跑
        }
    }
}
