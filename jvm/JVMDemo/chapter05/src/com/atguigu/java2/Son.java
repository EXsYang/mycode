package com.atguigu.java2;

interface MethodInterface {
    void methodA();
}

/**
 * 解析调用中非虚方法、虚方法的测试
 * <p>
 * invokestatic指令和invokespecial指令调用的方法称为非虚方法
 *
 * @author shkstart
 * @create 2020 下午 12:07
 */
class Father {
    public Father() {
        System.out.println("father的构造器");
    }

    public static void showStatic(String str) {
        System.out.println("father " + str);
    }

    public final void showFinal() {
        System.out.println("father show final");
    }

    public void showCommon() {
        System.out.println("father 普通方法");
    }
}

public class Son extends Father {
    public Son() {
        //invokespecial
        super();
    }

    public Son(int age) {
        //invokespecial
        this();
    }

    //不是重写的父类的静态方法，因为静态方法不能被重写！
    public static void showStatic(String str) {
        System.out.println("son " + str);
    }

    public static void main(String[] args) {
        Son so = new Son();
        so.show();
    }

    private void showPrivate(String str) {
        System.out.println("son private" + str);
    }

    public void show() {
        //invokestatic
        showStatic("atguigu.com");
        //invokestatic
        super.showStatic("good!");
        //invokespecial
        showPrivate("hello!");
        //invokespecial
        super.showCommon();

        //invokevirtual
        showFinal();//因为此方法声明有final，不能被子类重写，所以也认为此方法是非虚方法。
        //因为showFinal()方法声明有final，不能被子类重写，
        // 如果显示的使用super.调用父类中的showFinal()方法,则字节码中为invokespecial。 所以也认为此方法是非虚方法。
        // super.showFinal();

        //虚方法如下：
        //invokevirtual
        showCommon();
        info();//调用子类自定义的info()方法,也表现为虚方法 invokevirtual
        /**
         * 在您提供的代码中，info()是Son类定义的一个实例方法，
         * 没有被声明为final、static或private，
         * 这意味着它是可以被子类覆盖（重写）的。
         * 因此，当通过invokevirtual指令调用info()方法时，
         * JVM将进行动态方法查找，以确定实际调用的方法版本。
         * 即使在当前的情况下Son类没有子类覆盖info()方法，
         * 调用机制仍然假设存在这种可能性。
         */


        MethodInterface in = null;
        //invokeinterface
        in.methodA();
    }

    public void info() {

    }

    public void display(Father f) {
        f.showCommon();
    }
}
