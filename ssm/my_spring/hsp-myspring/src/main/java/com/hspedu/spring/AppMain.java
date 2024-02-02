package com.hspedu.spring;

import com.hspedu.spring.component.MonsterService;
import com.hspedu.spring.component.SmartAnimalable;
import com.hspedu.spring.ioc.HspSpringApplicationContext;
import com.hspedu.spring.ioc.HspSpringConfig;

/**
 * @author yangda
 * @description:
 * @create 2023-09-12-15:45
 */
public class AppMain {
    public static void main(String[] args) {
        HspSpringApplicationContext ioc = new HspSpringApplicationContext(HspSpringConfig.class);


        //Object monsterDao = ioc.getBean("monsterDao");
        //Object monsterDao2 = ioc.getBean("monsterDao");
        //
        //System.out.println("monsterDao= " + monsterDao);
        //System.out.println("monsterDao2= " + monsterDao2);
        //
        //MonsterService monsterService = (MonsterService)ioc.getBean("monsterService");
        //Object monsterService2 = ioc.getBean("monsterService");
        //
        //System.out.println("monsterService= " + monsterService);
        //System.out.println("monsterService2= " + monsterService2);

        MonsterService monsterService = (MonsterService)ioc.getBean("monsterService");
        monsterService.m1();
        System.out.println("--------------------");
        //这里我们测试AOP是否生效
        SmartAnimalable smartDog = (SmartAnimalable)ioc.getBean("smartDog");

        //System.out.println("smartDog运行类型= " + smartDog.getClass());
        smartDog.getSum(10,2);
        System.out.println("--------------------");
        smartDog.getSub(10,2);
        System.out.println("--------------------");
        System.out.println("ok");


    }
}
