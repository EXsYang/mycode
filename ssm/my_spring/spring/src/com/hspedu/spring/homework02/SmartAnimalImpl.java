package com.hspedu.spring.homework02;

/**
 * @author yangda
 * @description:
 * @create 2023-09-07-16:03
 */
public class SmartAnimalImpl implements SmartAnimal {
    @Override
    public double getSum(double a, double b) {
        //System.out.println("日志-方法名-getSum-参数 " +a +" " +  b);
        double result = a + b;
        System.out.println("方法内部打印result= " + result);
        //System.out.println("日志-方法名-getSum-结果result=  " + result);

        return result;
    }

    @Override
    public double getSub(double a, double b) {
        //System.out.println("日志-方法名-getSub-参数 " +a +" " +  b);
        double result = a - b;
        //result = 2/0;
        System.out.println("方法内部打印result= " + result);
        //System.out.println("日志-方法名-getSub-结果result=  " + result);
        return result;
    }
}
