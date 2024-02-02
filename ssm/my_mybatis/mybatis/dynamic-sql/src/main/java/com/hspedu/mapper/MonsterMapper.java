package com.hspedu.mapper;

import com.hspedu.entity.Monster;
import org.apache.ibatis.annotations.Param;

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


    //根据 age 查询结果
    // public List<Monster> findMonsterByAge(Integer age);
    public List<Monster> findMonsterByAge(@Param("age") Integer age);

    //根据 id 和名字来查询结果
    public List<Monster> findMonsterByIdAndName(Monster monster);

    //测试 choose 标签的使用
    public List<Monster>
    findMonsterByIdAndName_choose(Map<String, Object> map);

    //测试 foreach 的标签使用
    public List<Monster>
    findMonsterById_forEach(Map<String, Object> map);

    //trim 标签的使用
    public List<Monster> findMonsterByName_Trim(Map<String, Object> map);

    //测试 Set 标签
    public void updateMonster_set(Map<String, Object> map);
}
