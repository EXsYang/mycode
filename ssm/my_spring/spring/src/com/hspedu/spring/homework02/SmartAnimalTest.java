package com.hspedu.spring.homework02;

import org.junit.jupiter.api.Test;

/**
 * @author yangda
 * @description:
 * @create 2023-09-07-16:13
 */
public class SmartAnimalTest {

    @Test
    public void testSumOrSub(){

        SmartAnimal smartAnimalImpl = new SmartAnimalImpl();

        SmartAnimalProxyProvider smartAnimalProxyProvider = new SmartAnimalProxyProvider(smartAnimalImpl);
        SmartAnimal proxy = smartAnimalProxyProvider.getProxy();

        double sum = proxy.getSum(10, 2);

        System.out.println("============");

        double sub = proxy.getSub(10, 2);


    }
}
