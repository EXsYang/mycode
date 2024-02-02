package com.hspedu.spring.factory;

import com.hspedu.spring.bean.Monster;

import java.util.HashMap;

/**
 * @author yangda
 * @description: 实例工厂类
 * @create 2023-09-01-22:44
 */
public class MyInstanceFactory {
    private HashMap<String, Monster> monster_map;

    //通过普通代码块进行初始化
    {
        monster_map = new HashMap<>();
        monster_map.put("monster03",new Monster(333,"猪八戒!","猪八戒拳！"));
        monster_map.put("monster04",new Monster(444,"孙悟空!","金箍棒！"));
    }
    //写一个方法返回Monster对象
    public Monster getMonster(String key){
        return monster_map.get(key);
    }

}
