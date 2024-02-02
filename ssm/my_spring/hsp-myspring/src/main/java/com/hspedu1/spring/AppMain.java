package com.hspedu1.spring;

import com.hspedu1.spring.component.MonsterDao;
import com.hspedu1.spring.component.MonsterService;
import com.hspedu1.spring.component.SmartAnimalable;
import com.hspedu1.spring.ioc.HspSpringApplicationContext;
import com.hspedu1.spring.ioc.HspSpringConfig;

/**
 * @author yangda
 * @description:
 * @create 2023-09-16-15:10
 */
public class AppMain {
    public static void main(String[] args) {
        HspSpringApplicationContext ioc =
                new HspSpringApplicationContext(HspSpringConfig.class);


        //MonsterDao monsterDao1 = (MonsterDao) ioc.getBean("monsterDao");
        //MonsterDao monsterDao2 = (MonsterDao) ioc.getBean("monsterDao");
        //
        //System.out.println("monsterDao1= " + monsterDao1);
        //System.out.println("monsterDao2= " + monsterDao2);
        //
        //MonsterService monsterService1 = (MonsterService) ioc.getBean("monsterService");
        //MonsterService monsterService2 = (MonsterService) ioc.getBean("monsterService");
        //
        //System.out.println("monsterService1= " + monsterService1);
        //System.out.println("monsterService2= " + monsterService2);

        // 测试依赖注入
        MonsterService monsterService = (MonsterService) ioc.getBean("monsterService");
        monsterService.m1();
        //System.out.println("monsterService= " + monsterService);
        //System.out.println(monsterService);


        // 实现后置处理器机制
        // 首先要先实现初始化方法
        SmartAnimalable smartDog = (SmartAnimalable)ioc.getBean("smartDog");
        smartDog.getSum(10,2);
        System.out.println("----------------");
        smartDog.getSub(10,2);

        System.out.println("ok");


    }
}
