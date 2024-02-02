package com.hspedu.mapper;

import com.hspedu.entity.IdenCard;
import org.apache.ibatis.annotations.Select;

/**
 * @author yangda
 * @create 2023-11-02-16:40
 * @description: 使用注解方式 实现一对一映射
 */
public interface IdenCardMapperAnnotation {

    //根据 id 获取到身份证
    //这个方法不需要返回任何级联对象
    @Select("SELECT * FROM idencard WHERE id=#{id}")
    public IdenCard getIdenCardById(Integer id);
}
