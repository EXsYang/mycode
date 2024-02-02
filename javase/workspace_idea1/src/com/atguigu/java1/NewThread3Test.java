package com.atguigu.java1;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author yangda
 * @create 2021-11-26-20:09
 */
public class NewThread3Test {
    public static void main(String[] args) {
        NewThread3 n = new NewThread3();

        FutureTask futuretask = new FutureTask(n);

        new Thread(futuretask).start();

        try {
            Object sum = futuretask.get();
            System.out.println("总和为："+sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
class NewThread3 implements Callable{
    int sum = 0 ;
    @Override
    public Object call() throws Exception {
        for (int i = 0; i < 100; i++) {
            if(i % 2 == 0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}