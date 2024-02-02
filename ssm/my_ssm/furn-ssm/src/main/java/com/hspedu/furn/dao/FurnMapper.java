package com.hspedu.furn.dao;

import com.hspedu.furn.bean.Furn;
import com.hspedu.furn.bean.FurnExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FurnMapper {

    /* example 美/ɪɡˈzæmpl/n.例子;实例; */
    long countByExample(FurnExample example);

    int deleteByExample(FurnExample example);

    int deleteByPrimaryKey(Integer id);

    // 这个方法 会在生成sql语句时 对所有的自动进行插入
    // 如果没有设置传进来的furn对象的相关属性 就会默认插入null
    // 如果创建的数据库表的字段有非空约束 就会报错
    int insert(Furn record);

    // 方法名带Selective的这个方法 会有选择性/动态地生成sql语句
    int insertSelective(Furn record);

    List<Furn> selectByExample(FurnExample example);

    Furn selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Furn record, @Param("example") FurnExample example);

    int updateByExample(@Param("record") Furn record, @Param("example") FurnExample example);

    // 有选择性/动态地生成sql语句
    int updateByPrimaryKeySelective(Furn record);

    // 该方法 会在生成sql语句时 对对应的所有的表的字段进行update
    // 如果传进来的furn对象没有设置相关的属性值 默认 set 该字段 为 null
    // 如果创建的数据库表的字段有非空约束 就会报错
    int updateByPrimaryKey(Furn record);
}