package Test_.java.extendsx;

/**
 * @author yangda
 * @create 2023-11-29-16:21
 * @description:
 * 在父类中定义 h2()方法 ，子类Son重写h2()方法
 * 父类中显示的调用h2() 通过子类的对象调用h2()方法
 * 判断走的是哪一个h2()
 */
public class Father {

    public void h1(){

        h2();
    }

    public void h2() {

            System.out.println("父类的h2方法");
    }
}
