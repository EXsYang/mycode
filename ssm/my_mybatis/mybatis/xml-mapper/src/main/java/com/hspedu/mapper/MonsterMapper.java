package com.hspedu.mapper;

import com.hspedu.entity.Monster;

import java.util.List;
import java.util.Map;

/**
 * @author yangda
 * @create 2023-10-17-15:47
 * @description:
 * 这是一个接口 用于定义操作monster表的方法
 * 这些方法可以通过注解或者xml文件来实现
 */


public interface MonsterMapper {

    //通过 id 或者名字查询
    public List<Monster> findMonsterByNameORId(Monster monster);
    //查询名字中含义'精'妖怪
    public List<Monster> findMonsterByName(String name);

    //查询 id > 10 并且 salary 大于 40, 要求传入的参数是 HashMap
    public List<Monster>
    findMonsterByIdAndSalary_ParameterHashMap(Map<String, Object> map);

    //查询 id > 10 并且 salary 大于 40, 要求传入的参数是 HashMap
    public List<Map<String, Object>>
    findMonsterByIdAndSalary_ParameterHashMap_ReturnHashMap(Map<String, Object> map);

}
