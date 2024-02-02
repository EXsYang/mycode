package com.hspedu.mapper;

import com.hspedu.entity.Pet;
import com.hspedu.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yangda
 * @create 2023-11-03-16:55
 * @description: 使用注解方式完成 级联操作 多对一 双向映射
 */
public interface UserMapperAnnotation {

   /* private Integer id;
    private String name;
    // 一个主人对应多个宠物, mybatis使用集合List<Pet>体现一对多的关系
    private List<Pet> pets;*/

    /**
     *  1. 注解的配置方式 就是对 Mapper.xml 文件配置的改写
     *  2. @Results 注解 就对应 Mapper.xml 文件 resultMap标签
     *
     *  <resultMap id="resultUserMap" type="User">
     *         <id property="id" column="id"/>
     *         <result property="name" column="name"/>
     *         将查询返回的n条宠物数据/信息封装到这个pets集合-->
     *         解读: 因为 pets 属性是集合，因此这里需要使用 collection 标签来处理
     *         1. ofType="Pet" 需要指定返回来的集合中的存放的数据类型 "ofTyp" 属性。这个属性非常重要，它用来将 JavaBean（或字段）属性的类型和集合存储的类型区分开来。
     *         2. collection 表示 pets 是一个集合
     *         3. property="pets" 表示返回的user对象的属性 pets
     *         4. column="id"  SELECT * FROM `mybatis_user` WHERE `id` = #{id}; 返回的id字段对应的值
     *         -->
     *         <collection property="pets" column="id" ofType="Pet"
     *                     select="com.hspedu.mapper.PetMapper.getPetByUserId"/>
     *     </resultMap>
     *     <select id="getUserById" parameterType="Integer" resultMap="resultUserMap">
     *         SELECT * FROM `mybatis_user` WHERE `id` = #{id};
     *     </select>
     */

    //通过 id 获取 User 对象
    @Select("SELECT * FROM `mybatis_user` WHERE `id` = #{id};")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            // 按照主人id 查询多条pet数据 封装成集合。 注意pets对应的是集合
            @Result(property = "pets",column = "id",many = @Many(select = "com.hspedu.mapper.PetMapperAnnotation.getPetByUserId"))
    })
    public User getUserById(Integer id);
}
