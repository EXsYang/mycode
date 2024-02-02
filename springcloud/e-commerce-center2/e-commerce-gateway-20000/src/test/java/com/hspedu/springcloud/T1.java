package com.hspedu.springcloud;

import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;

import java.util.function.Function;

/**
 * @author yangda
 * @create 2024-01-02-22:51
 * @description:
 */
public class T1 {

    public static void main(String[] args) {

        //传入的第二个参数,就相当于实现了函数式接口 Function<String, Cat> fn
        //即就是相当于重写了apply(T t)方法,又因为在hi()方法中 fn.apply(str); 把str传到了apply方法中
        //所以这里就相当于把"小花猫"通过形参(String str)传入了这个匿名实现方法内部
        // Dog dog = hi("小花猫", (String str) -> {
        //     Cat cat = new Cat();
        //     cat.setName(str);
        //     return cat;
        // });

        //对上面的lambda表达式简写 当只有一个形参时,可以去掉小括号同时对类型进行简写
        // Dog dog = hi("小花猫", str -> {
        //     Cat cat = new Cat();
        //     cat.setName(str);
        //     return cat;
        // });

        //对上面的lambda表达式简写
        // Dog dog = hi("小花猫", str -> {
        //     return new Cat(str);
        // });


        //对上面的lambda表达式简写，这个函数只有一句话，再次简写
        Dog dog = hi("小花猫", str -> new Cat(str));


        System.out.println(dog);
    }


    /**
     * 下面是函数式接口Function<T, R>的定义
     * @FunctionalInterface
     * public interface Function<T, R> {
     *
     *      * Applies this function to the given argument.
     *      * @param t the function argument
            //抽象方法,根据T类型返回R类型
            R apply(T t);


     *
     * public Builder route(String id, Function<PredicateSpec, Route.AsyncBuilder> fn) {
     * Route.AsyncBuilder routeBuilder = fn.apply(new RouteSpec(this).id(id)); //这里的.id(id)返回的类型就是PredicateSpec
     * add(routeBuilder);
     * return this;
     * }
     * <p>
     * routes.route("member_route04",r -> r.path("/member/get/**")
     * .uri("http://localhost:10000"))
     * .build();
     */


    //模拟Cat -> Dog
    public static Dog hi(String str, Function<String, Cat> fn) {
        Cat cat = fn.apply(str);
        Dog dog = new Dog();
        dog.setName(cat.getName() + "@变成了小狗名");
        return dog;
    }
}

class Dog {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Cat {

    private String name;

    public Cat() {
    }

    public Cat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


class Hsp {


}
