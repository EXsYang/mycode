package com.atguigu.runnable;

/**
 * @author yangda
 * @description:
 * @create 2022-11-07-16:52
 */
public class WindowTest {
    //使用实现Runnable接口的方式，解决三个窗口卖票问题

    public static void main(String[] args) {

        int[] i = new int[2];
        System.out.println(i.length);

        char a = 'a';
        System.out.println(a);

        Window w = new Window();

        Thread w1 = new Thread(w);
        Thread w2 = new Thread(w);
        Thread w3 = new Thread(w);

        w1.setName("窗口一");
        w2.setName("窗口二");
        w3.setName("窗口三");

        w1.start();
        w2.start();
        w3.start();

    }






}
class Window implements Runnable{
    private int ticket = 100;
    @Override
    public void run() {
        boolean flag = true;
        while (flag){
            if (ticket > 0){
                //放在打印之前错票的几率大大增加

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }//一会儿再打印啊，先等一会儿，有可能三个都在这等着谁也不打印
                //第一个先往下走，走了就减1，后两个满足条件也进来了(第一个还没出去，ticket还没减1，)，也在if语句中
                //假设上面的判断条件正好票数是1，第一个走了之后，ticket--，变成0了，第二个再打印，此时判断的ticket就是0
                //同理，第二个走了之后，就变成-1了，此时第三个再打印就是-1
                //第三个走后，ticket-- ，此时最后ticket变为-2，只要有-1，就会有-2
                System.out.println(Thread.currentThread().getName() + ":卖票，票号：" + ticket);
                //放在打印之后重票的几率大大增加
//                try {
//                    Thread.sleep(100);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }//一会儿再减啊，先等一会儿，有可能三个都在这等着谁也不减，但是已经打印完了
                if(ticket == -1){//最后一个出去的线程，修改flag，另外两个线程此时可能已经在走到69行之前或是直接跳过去执行完了
                                //都没出去之前且在69行之前，会有三个“验证成功”输出
                    flag = false;
                }
                ticket--;
            }else {
                break;
            }
        }
        if (flag){
            System.out.println(Thread.currentThread().getName() + "验证成功！" + ticket);
        }

    }
}