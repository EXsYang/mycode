package com.hspedu.spring.component;

import com.hspedu.spring.annotation.Component;

/**
 * @author yangda
 * @description:
 * @create 2023-09-16-12:39
 */
@Component(value = "smartDog")
public class SmartDog implements SmartAnimalable{
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
