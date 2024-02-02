package com.hspedu.service;

import com.hspedu.entity.Monster;

import java.util.List;

/**
 * @author yangda
 * @create 2023-10-01-17:18
 * @description:
 */
public interface MonsterService {
    //增加方法-返回monster列表
    public List<Monster> listMonster();
    //增加方法-通过传入的name,返回monster列表
    public List<Monster> findMonsterByName(String name);

    //增加方法 处理登录
    public boolean login(String name);
}
