package com.atguigu.exer1;

/**
 * @author yangda
 * @description:
 * @create 2022-11-08-16:28
 */
public class Productor extends Thread{
    //生产者 判断店员手里够不够20个产品，如果够20店员会让生产者等一下再生产
    //如果店中有空位，再通知生产者继续生产
   private Clerk clerk;

   public Productor(Clerk clerk){
       this.clerk = clerk;
   }

    @Override
    public void run() {
       //生产产品
        while (true){

            clerk.produceProduct();
        }

    }
}
