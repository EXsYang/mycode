package interface_;

import org.junit.Test;

/**
 * @author yangda
 * @description:
 * @create 2022-11-18-16:30
 */
public class InterfaceTest {
    public static void main(String[] args) {

//    Car.show()//Non-static method 'show()' cannot be referenced from a static context
    }
    @Test
    public void test1(){
//        Car.show();//Non-static method 'show()' cannot be referenced from a static context
//        不能从静态上下文引用非静态方法'show()'

    }
}
interface Car{
    //1．默认方法使用 default 关键字，一个接口中可以有多个默认方法。
    //2．接口中既可以定义抽象方法，又可以定义默认方法，默认方法不是抽象方法。
    //3．子类实现接口的时候，可以直接调用接口中的默认方法，即继承了接口中的默认方法。
    //4．接口中同时还可以定义静态方法，静态方法通过接口名调用。
    default void show(){
        System.out.println("aaa");
    }

}
class BigCar implements Car{

}