package com.hspedu.spring.factory;

import com.hspedu.spring.bean.Monster;

import java.util.HashMap;

/**
 * @author yangda
 * @description:  静态工厂类-可以返回Monster对象
 * @create 2023-09-01-22:09
 */
public class MyStaticFactory {

    private static HashMap<String, Monster> monsterMap;

    //使用 static代码块 进行初始化
    //在java基础的时候，讲过的
    static {
        monsterMap = new HashMap<>();
        monsterMap.put("monster01",new Monster(111,"牛魔王!","牛魔王拳！"));
        monsterMap.put("monster02",new Monster(222,"铁扇公主!","芭蕉扇！"));
    }

    // 提供静态方法 不然spring beans.xml 文件中 的 bean factory-method属性识别不出来
    // 一定要是静态的方法 否则会报错！
    //public Monster getMonster(String key){
    public static Monster getMonster(String key){
        return monsterMap.get(key);
    }
}
