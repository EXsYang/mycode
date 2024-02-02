package com.atguigu.exer;

/**
 * @author yangda
 * @description:
 * @create 2022-11-12-19:31
 */
public class Homework4 {
    public static void main(String[] args) {
//            Cellphone cellphone = new Cellphone();
        Homework4 homework4 = new Homework4();
        Cellphone cellphone = homework4.new Cellphone();
         Homework4.Cellphone cellphone1= homework4.new Cellphone();
        cellphone.testWork(new Computer() {
            @Override
            public void work4() {
                System.out.println();
            }

            @Override
            public void work(double a, double b) {
                double s = a * b;
                System.out.println("s\t" + s);//s	7.26
                System.out.println(" a * b " + a * b);// a * b 7.26
                System.out.println( s);//7.26
                System.out.println(a * b);//7.26        都可以正常输出结果
            }
        },2.2,3.3);




    }
    class Cellphone{
        void testWork(Computer com,double a,double b){
            com.work(a,b);
//            Computer.work();
        }


    }
}
interface Computer{
     void work4();


     void work(double a,double b);
     static void workq(double a,double b){

         System.out.println("计算");

     };
     default void work2(){
        System.out.println("计算");
    };
     default void work3(int i,int j){
         int s = i + j;
         System.out.println("s和为：");
        System.out.println("直接拼接的和为："+ i + j);
    };
//     default static void work2(){
//         Illegal combination of modifiers: 'static' and 'default'
//         修饰符的非法组合:'default'和'static'
//         System.out.println("计算2");
//    };
}
