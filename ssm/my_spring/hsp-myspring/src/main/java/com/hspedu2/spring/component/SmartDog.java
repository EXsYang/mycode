package com.hspedu2.spring.component;


import com.hspedu2.spring.annotation.Component;
import com.hspedu2.spring.annotation.Scope;

/**
 * @author yangda
 * @description:
 * @create 2023-09-16-12:39
 */
@Scope
@Component(value = "smartDog")
public class SmartDog implements SmartAnimalable {
    // 这里是要被切入的方法
    public float getSum(float i, float j) {
        float res = i + j;
        System.out.println("SmartDog-getSum-res=" + res);
        return res;
    }

    public float getSub(float i, float j) {
        float res = i - j;
        System.out.println("SmartDog-getSub-res=" + res);
        return res;
    }
}
