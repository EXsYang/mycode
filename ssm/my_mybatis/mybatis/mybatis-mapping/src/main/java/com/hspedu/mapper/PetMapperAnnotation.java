package com.hspedu.mapper;

import com.hspedu.entity.Pet;
import com.hspedu.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yangda
 * @create 2023-11-03-16:56
 * @description:
 */
public interface PetMapperAnnotation {

   /* private Integer id;
    private String nickname;
    // 一个宠物对应一个主人
    private User user;*/


    //通过 User 的 id 来获取 pet 对象，可能有多个，因此使用 List 接收
    /**
     *  @Results 注解 对应 PetMapper.xml 中的 resultMap 标签
     *  @Results(id = "PetResultMap") 的id属性 对应  <resultMap id=
     *
     *
     *    <resultMap id="resultPetMap" type="Pet">
     *         <id property="id" column="id"/>
     *         <result property="nickname" column="nickname"/>
     *         <association property="user" column="user_id"
     *                      select="com.hspedu.mapper.UserMapper.getUserById"/>
     *     </resultMap>
     *     <select id="getPetByUserId" parameterType="Integer" resultMap="resultPetMap">
     *         SELECT * FROM `mybatis_pet` WHERE `user_id` = #{userId};
     *     </select>
     *
     */
    //id = "PetResultMap" 就是给我们的 Results[Result Map] 指定一个名字
    //，目的是为了后面方便复用
    // 如果注解中有多个值,value = 不能省略 否则报错
    @Results(id = "PetResultMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "nickname",column = "nickname"),
            // 每个Pet对应一个User 一对一
            @Result(property = "user",column = "user_id",one = @One(select = "com.hspedu.mapper.UserMapperAnnotation.getUserById"))
    })
    @Select(" SELECT * FROM `mybatis_pet` WHERE `user_id` = #{userId};")
    public List<Pet> getPetByUserId(Integer userId);


    /**
     *
     *  <select id="getPetById" parameterType="Integer" resultMap="resultPetMap">
     *         SELECT * FROM `mybatis_pet` WHERE `id` = #{id};
     *  </select>
     *
     *   @ResultMap("PetResultMap") 使用/引用我们上面定义的 Results[ResultMap]
     */
    //通过 pet 的 id 获取 Pet 对象, 同时会查询到pet对象关联的user对象
    @Select(" SELECT * FROM `mybatis_pet` WHERE `id` = #{id};")
    @ResultMap("PetResultMap")
    public Pet getPetById(Integer id);
}
