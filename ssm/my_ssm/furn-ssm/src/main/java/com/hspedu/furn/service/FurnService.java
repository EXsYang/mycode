package com.hspedu.furn.service;

import com.hspedu.furn.bean.Furn;

import java.util.List;

/**
 * @author yangda
 * @create 2023-11-14-12:37
 * @description:
 */
public interface FurnService {
    //添加
    public void save(Furn furn);

    //查询所有家居
    public List<Furn> findAll();

    //更新数据
    // public void update(Furn furn);

    //更新数据
    public int update(Furn furn);

    //删除数据
    // public void del(Integer id);

    public int del(Integer id);

    public Furn findFurnById(Integer id);

    //根据家居名字进行查询
    public List<Furn> findByCondition(String name);
}
