package com.hspedu.mapper;

import com.hspedu.entity.Monk;

import java.util.List;

/**
 * @author yangda
 * @create 2023-10-17-22:31
 * @description:
 * 这是一个接口 用于定义操作monk表的方法
 * 这些方法可以通过注解或者xml文件来实现
 */
public interface MonkMapper {

    //添加monster
    public void addMonk(Monk monk);

    //删除monster
    public void delMonk(Integer id);

    //修改 Monster
    public void updateMonk(Monk monk);

    //查询-根据 id
    public Monk getMonkById(Integer id);

    //查询所有的 Monster
    public List<Monk> findAllMonk();


}
