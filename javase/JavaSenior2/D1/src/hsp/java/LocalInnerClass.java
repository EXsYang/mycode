package hsp.java;

/**
 * @author yangda
 * @create 2022-10-31-22:08
 */
public class LocalInnerClass {
    public static void main(String[] args) {
        Outer outer = new Outer();
        System.out.println(outer);
        outer.say();

    }
}

class Outer{
//局部内部类
    private int a = 1;
    public void m1(){
        System.out.println("m1方法");
    }
    public void say(){
        final class Inner{
            //private int a = 2;
            private int a = 2;
            private void say1(){
                m1();
                System.out.println("Inner:" + a +"\tOuter:" + Outer.this.a);
                System.out.println(Outer.this);
            }
        }
        Inner inner = new Inner();
        inner.say1();
    }

}
class Outer1{
    //匿名内部类



}
