package Test_.java.extendsx;

/**
 * @author yangda
 * @create 2023-11-29-16:21
 * @description:
 */
public class Son extends Father{

    public static void main(String[] args) {
        Father son = new Son();
        //动态绑定 走的是子类的h2()方法
        son.h2();
    }

    public void h2() {

        System.out.println("子类的h2方法");
    }
}
