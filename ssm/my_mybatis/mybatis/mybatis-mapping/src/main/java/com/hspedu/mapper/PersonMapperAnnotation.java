package com.hspedu.mapper;

import com.hspedu.entity.Person;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

/**
 * @author yangda
 * @create 2023-11-02-16:46
 * @description:
 */
public interface PersonMapperAnnotation {

    //注解形式 就是对前面的xml文件配置形式的体现

    //通过 Person 的 id 获取到 Person,包括这个 Person 关联的 IdenCard 对象
    //想：你到底返回什么结果，然后你想办法给他
    /*
        private Integer id;
        private String name;
        private IdenCard card;
    */

    @Select("SELECT * FROM `person` WHERE `person`.`id` = #{id};")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "card",column = "card_id",one = @One(select = "com.hspedu.mapper.IdenCardMapper.getIdenCardById"))
    })
    public Person getPersonById(Integer id);


}
