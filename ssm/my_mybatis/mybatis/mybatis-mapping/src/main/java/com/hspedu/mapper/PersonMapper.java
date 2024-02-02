package com.hspedu.mapper;

import com.hspedu.entity.Person;

/**
 * @author yangda
 * @create 2023-11-01-22:01
 * @description:
 * 注意事项：
 * 1. 表是否设置外键, 对 MyBatis 进行对象/级联映射没有影响
 * 2. 举例: 去掉 person 表的外键 , 进行测试, 依然可以获取相应的级联对象
 */
public interface PersonMapper {

    //级联方法
    //通过 Person 的 id 获取到 Person,包括这个 Person 关联的 IdenCard 对象[级联查询]
    public Person getPersonById(Integer id);

    //级联方法
    //通过 Person 的 id 获取到 Person,包括这个 Person 关联的 IdenCard 对象
    public Person getPersonById2(Integer id);

    //普通的方法 只是从数据库中查询一个Person记录
    public Person getPersonById3(Integer id);

    //普通的方法 通过card_id从数据库中查询一个Person对象/数据
    public Person getPersonByCardId(Integer cardId);
}
