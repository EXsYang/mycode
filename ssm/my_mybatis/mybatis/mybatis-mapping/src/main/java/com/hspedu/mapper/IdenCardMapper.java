package com.hspedu.mapper;

import com.hspedu.entity.IdenCard;
import com.hspedu.entity.Person;

/**
 * @author yangda
 * @create 2023-11-01-22:02
 * @description:
 */
public interface IdenCardMapper {

    //根据 id 获取到身份证号
    public IdenCard getIdenCardById(Integer id);

    //普通的方法 只是从数据库中查询一个Person记录
    public IdenCard getIdenCardById2(Integer id);
}
