package Test_.java;

/**
 * @author yangda
 * @create 2022-11-02-21:08
 */
public class F {
    public static void main(String[] args) {
        Son s = new Son(10);
        System.out.println("---------");
        Father1 f = new Father1();
    }
}
class Father1{
    public int age = 30;
    {
        System.out.println("父类代码块");
    }
    public Father1(){
        System.out.println("父类无参构造方法");
        Show();
    }
    public void Show(){
        System.out.println("父类的方法一");
        Show2();
    }
    public void Show2(){
        System.out.println("父类的方法二");
    }
}

class Son extends Father1{
    {
        System.out.println("子类的代码块");
    }
    public Son(){
        System.out.println("子类的无参构造方法"+"---"+age);
    }
    public Son(int age){
        System.out.println("子类的有参构造方法"+"---"+age);
    }

    public void Show(){
        System.out.println("子类的方法一");
        super.Show2();
    }
    public void Show2(){
        System.out.println("子类的方法二");
    }
}

