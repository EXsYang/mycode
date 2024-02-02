package com.hspedu.mapper;

import com.hspedu.entity.Monster;

import java.util.List;

/**
 * @author yangda
 * @create 2023-10-17-15:47
 * @description:
 * 这是一个接口 用于定义操作monster表的方法
 * 这些方法可以通过注解或者xml文件来实现
 */


public interface MonsterMapper {

    //添加monster
    public void addMonster(Monster monster);

    //删除monster
    public void delMonster(Integer id);

    //修改 Monster
    public void updateMonster(Monster monster);

    //查询-根据 id
    public Monster getMonsterById(Integer id);

    //查询所有的 Monster
    public List<Monster> findAllMonster();

}
