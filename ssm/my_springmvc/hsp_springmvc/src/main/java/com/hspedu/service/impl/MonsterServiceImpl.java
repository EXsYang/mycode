package com.hspedu.service.impl;

import com.hspedu.entity.Monster;
import com.hspedu.hspspringmvc.annotation.Service;
import com.hspedu.service.MonsterService;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yangda
 * @create 2023-10-01-17:18
 * @description: MonsterServiceImpl 作为service注入到spring容器中
 */
@Service
public class MonsterServiceImpl implements MonsterService {
    @Override
    public List<Monster> listMonster() {
        //这里模拟数据 -> DB
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(100,"牛魔王","牛魔王拳",400));
        monsters.add(new Monster(200,"蜘蛛精","吐口水",200));
        return monsters;
    }

    @Override
    public List<Monster> findMonsterByName(String name) {

        //这里模拟数据 -> DB
        ArrayList<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(100,"牛魔王","牛魔王拳",400));
        monsters.add(new Monster(200,"蜘蛛精","吐口水",200));
        monsters.add(new Monster(300,"孙悟空","金箍棒",500));
        monsters.add(new Monster(400,"猪八戒","九齿钉耙",400));
        monsters.add(new Monster(500,"沙和尚","挑扁担",300));

        //创建集合返回查询到的monster集合
        ArrayList<Monster> findMonsters = new ArrayList<>();

        //遍历集合 返回满足条件的
        for (Monster monster : monsters) {
            if (monster.getName().contains(name)){
                findMonsters.add(monster);
            }
        }

        return findMonsters;
    }

    @Override
    public boolean login(String name) {
        //实际上要到DB去验证 这里模拟数据
        if ("白骨精".equals(name)){
            return true;
        }else {

            return false;
        }

    }
}
