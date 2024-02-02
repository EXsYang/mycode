package com.hspedu.springboot.bean;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yangda
 * @create 2023-11-26-18:57
 * @description:
 */

/**
 * 如果标注了该注解 @RequiredArgsConstructor 则可以声明属性为 private final Dog dog;
 * 、而不会报错 且不用显示初始化
 * 因为该注解生成的构造器 会在构造器中进行属性的初始化
 * <p>
 * public Cat(final Dog dog) {
 * this.dog = dog;
 * }
 */
// @RequiredArgsConstructor
// @RequiredArgsConstructor(onConstructor = @_(@Autowired))
public class Cat {

    // @NonNull
    // private final Dog dog;

    // public Cat() {
    // }

    // public Cat(Dog dog) {
    //     this.dog = dog;
    // }
    // public Cat() {
    //
    // }

    // private final Dog dog;

}
