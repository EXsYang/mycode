package com.hspedu.furn.lambda;

/**
 * @author yangda
 * @create 2023-12-26-20:05
 * @description:
 */
public class Test {
    public static void main(String[] args) {

        //传统方式实现HspFunction接口/得到实现接口的对象 可以使用匿名内部类
        // HspFunction<Desk, String> hf = new HspFunction<Desk, String>() {
        //     @Override
        //     public String apply(Desk desk) {
        //         return "hello desk";
        //     }
        // };
        //
        // String val = hf.apply(new Desk());

        //在下面这里使用lambda表达式的条件是 要满足HspFunction中的抽象方法的规则
        // ，即 R apply(T t); //抽象方法:表示根据类型 T 的参数获取类型 R 的结果
        //这里的Desk类型就对应T,getName方法的返回值是String 正好是根据Desk的类型
        //返回一个String类型,这里两者的类型不关心，满足规则即可
        HspFunction<Desk,String> hf2 = Desk::getName;

        // 不在乎方法干了什么事，只在乎这里是不是通过Desk类型返回一个String类型
        // HspFunction<Desk, String> 是一个函数式接口，它期待一个接受
        // Desk 类型参数并返回 String 类型结果的方法引用。
        // 这里可以引用任何符合这一条件的方法，例如 Desk 类中
        // 返回 String 类型的 getName 或 getBrand 方法。
        HspFunction<Desk,String> hf3 = Desk::getBrand; //灵活


        // HspFunction<Desk,String> hf4 = Desk::getId; //报错
        HspFunction<Desk,Integer> hf4 = Desk::getId; //可以
        HspFunction<Desk,Object> hf5 = Desk::getId; //可以 体现了泛型很灵活


        // 方法不能瞎传
        // Desk::hello 方法 因为hello方法没有返回值
        // ,所以不满足条件 根据类型 T 的参数获取类型 R 的结果 会报错
        // Desk::hello 方法不能用作 HspFunction<Desk, String> 的方法引用，因为它没有返回值，
        // 而 HspFunction<Desk, String> 要求方法必须返回一个 String 类型的结果。
        // HspFunction<Desk,String> hf3 = Desk::hello;
        //这里hf2已经是实现了HspFunction接口的对象了 所以也可以调用默认实现的方法
        hf2.ok();

        String val = hf2.apply(new Desk());
        System.out.println("val= " + val);

    }
}


//定义一个函数式接口：有且只有一个抽象方法的方法
//我们可以使用@FunctionalInterface 来标识一个函数式接口
// HspFunction<T,R> 是一个函数式接口(是自定义泛型接口)
@FunctionalInterface
interface HspFunction<T,R>{

    R apply(T t); //抽象方法:表示根据类型 T 的参数获取类型 R 的结果

    // void hi();

    //函数式接口可以有多个默认实现方法
    default public void ok(){
        System.out.println("ok@ ");
    }
}

//函数式接口可以不使用泛型 但是必须有且只有一个抽象方法
@FunctionalInterface
interface HspInterface{
    public void hi();
}

class Desk{ //Bean // Desk 类用于演示 HspFunction 接口的使用

    private String name = "hello desk22";
    private String brand = "北京牌"; // Desk 的品牌
    private Integer id = 10;

    public Integer getId() {
        return id;
    }

    //满足要求 根据类型 T (这里即为Desk类型) 的参数获取类型 R 的结果
    public String getName() {
        return name;
    }

    //满足要求 根据类型 T 的参数获取类型 R 的结果
    public String getBrand() {
        return brand;
    }

    public void hello(){
        System.out.println("hello");
    }
}