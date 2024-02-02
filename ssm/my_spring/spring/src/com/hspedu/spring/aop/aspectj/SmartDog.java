package com.hspedu.spring.aop.aspectj;

// 下面这里的包引错了 导致报错
// expected single matching bean but found 2: com.hspedu.spring.test.Man#0,com.hspedu.spring.test.Man#1
// 解决 方法 不写导包import 默认找同包下的SmartAnimalable类
//import com.hspedu.spring.aop.proxy3.SmartAnimalable;


import org.springframework.stereotype.Component;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Component //使用@Component 当spring容器启动时，将 SmartDog注入到容器
public class SmartDog implements SmartAnimalable {
    @Override
    public float getSum(float i, float j) {
        //System.out.println("日志-方法名-getSum-参数 " + i + " " + j);
        float result = i + j;
        System.out.println("方法内部打印result = " + result);
        //result = 2/0;
        //System.out.println("日志-方法名-getSum-结果result= " + result);
        return result;
    }

    @Override
    public float getSub(float i, float j) {
        //System.out.println("日志-方法名-getSub-参数 " + i + " " + j);
        float result = i - j;
        System.out.println("方法内部打印result = " + result);
        //System.out.println("日志-方法名-getSub-结果result= " + result);
        return result;
    }
}
