package classloader.private_;

/**
 * @author yangda
 * @create 2024-07-31-22:58
 * @description:
 */
public class Parent {
    private int a = 2;

    public int b = 2;
    private void t1() {
        System.out.println(a);
    }



    public void t2() {
        System.out.println(b);
    }

}

class Son extends Parent {

    public void t3() {
        // 访问父类的私有属性会报错
        // 'a' has private access in 'classloader.private_.Parent'
        // System.out.println(a);

        // 访问父类的私有方法会报错
        // 't1()' has private access in 'classloader.private_.Parent'
        // super.t1();
    }


    public static void main(String[] args) {
        //访问父类的属性 , 但不能访问父类的 private 属性 [案例]super.属性名
        // public void hi() {
        //     System.out.println(super.n1 + " " + super.n2 + " " + super.n3 );
        // }

        //super.test400();//不能访问父类 private 方法
    }

}