package com.atguigu.java1;

/**
 * @author shkstart
 * @create 2020-09-08 10:38
 * <p>
 * 指令7：控制转移指令
 */
public class IfSwitchGotoTest {
    //1.条件跳转指令
    public void compare1(){
        int a = 0;
        if(a != 0){
            a = 10;
        }else{
            a = 20;
        }
    }
    public boolean compareNull(String str){
        if(str == null){
            return true;
        }else{
            return false;
        }
    }
    //结合比较指令
    public void compare2() {
        float f1 = 9;
        float f2 = 10;
        System.out.println(f1 < f2);//true
    }
    public void compare3() {
        int i1 = 10;
        long l1 = 20;
        System.out.println(i1 > l1);
    }

    public int compare4(double d) {
        if (d > 50.0) {
            return 1;
        } else {
            return -1;
        }
    }

    //2.比较条件跳转指令
    public void ifCompare1(){
        // 使用的比较条件跳转指令是： if_icmple 18
        int i = 10;
        int j = 20;
        System.out.println(i > j);//使用的是： if_icmple 18
    }


    public void ifCompare2() {
        // 和上面的ifCompare1()方法的字节码指令基本相同，只是用的数值不同。
        // 即short、byte类型都是按照int类型来处理的
        // 即使用的比较条件跳转指令也是： if_icmple 18
        short s1 = 9;
        byte b1 = 10;
        System.out.println(s1 > b1); //使用的是： if_icmple 18
    }

    public void ifCompare3() {
        Object obj1 = new Object();
        Object obj2 = new Object();
        System.out.println(obj1 == obj2);//false
        // 使用的比较条件跳转指令是:  if_acmpne 28 obj1 != obj2?如果满足则跳转28,否则接着执行下一条字节码指令


        System.out.println(obj1 != obj2);//true
        // 使用的比较条件跳转指令是:  if_acmpeq 44,obj1 == obj2?满足则跳转44


    }

    //3.多条件分支跳转
    public void swtich1(int select){
        int num;
        switch(select){
            case 1:
                num = 10;
                break;
            case 2:
                num = 20;
                //break;
            case 3:
                num = 30;
                break;
            default:
                num = 40;
        }

    }
    public void swtich2(int select){
        int num;
        switch(select){
            case 100:
                num = 10;
                break;
            case 500:
                num = 20;
                break;
            case 200:
                num = 30;
                break;
            default:
                num = 40;
        }
    }
    //jdk7新特性：引入String类型
    public void swtich3(String season){
        switch(season){
            case "SPRING":break;
            case "SUMMER":break;
            case "AUTUMN":break;
            case "WINTER":break;
        }
    }

    //4.无条件跳转指令
    public void whileInt() {
        int i = 0;
        while (i < 100) {
            String s = "atguigu.com";
            i++;
        }
    }

    public void whileDouble() {
        double d = 0.0;
        while(d < 100.1) {
            String s = "atguigu.com";
            d++;
        }
    }

    public void printFor() {
        short i;
        for (i = 0; i < 100; i++) {
            String s = "atguigu.com";
        }

    }

    //思考：如下两个方法的操作有何不同？
    public void whileTest(){
        int i = 1;
        while(i <= 100){

            i++;
        }
        //可以继续使用i
    }
    public void forTest(){
        for(int i = 1;i <= 100;i++){

        }
        //不可以继续使用i
    }
    //更进一步
    public void doWhileTest(){
        int i = 1;
        do{
            i++;
        }while(i <= 100);
    }
}
