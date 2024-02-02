package com.atguigu.runnable;


/**
 * @author yangda
 * @create 2021-12-11-10:12
 */
public class SimpleDateFormatTest   {
//    @Test
//    public void test1(){
//        Date date = new Date();
//        //现在Sat Dec 11 10:15:06 CST 2021是一个日期类型
//        System.out.println(date);//Sat Dec 11 10:15:06 CST 2021这里打印的是一个String类型的
//
//        SimpleDateFormat sdf = new SimpleDateFormat();//创建一个SimpleDateFormat类的对象，通过对象调用类中方法
//        String format = sdf.format(date);//通过对象.方法调用format方法，并使用String类型接收
//        System.out.println(format);//此时打印的是一个String类型的字符串
//
////
////        Date date1 = sdf.parse("gdk");
//
//
//    }
public static void main(String[] args) {

    a a = new a();
    a.setName("线程a");
    a.start();

    b b = new b();
    b.setName("线程b");
    b.start();


    Thread.currentThread().setName("线程main");
    for (int i = 0; i < 2000; i++) {//遍历奇数
        System.out.println(Thread.currentThread().getName()+i);
    }
    }
}
class a extends Thread{
    public void run() {
        for (int i = 0; i < 199; i++) {//遍历偶数
            if(i % 2 == 0)

                System.out.println(currentThread().getName()+i);
        }
    }

}
class b extends Thread{
    public void run() {
        for (int i = 0; i < 199; i++) {//遍历偶数
            if(i % 2 == 0)
                setName("线程b");
                System.out.println(currentThread().getName()+i);
        }
    }
}
