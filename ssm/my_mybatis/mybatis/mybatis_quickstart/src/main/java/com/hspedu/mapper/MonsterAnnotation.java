package com.hspedu.mapper;

import com.hspedu.entity.Monster;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author yangda
 * @create 2023-10-29-14:29
 * @description: 使用注解方法来配置接口方法
 */
public interface MonsterAnnotation {

    //添加monster
    /*
      1.使用注解方式配置接口方法addMonster
      2.回顾xml如何配置
        <insert id="addMonster" parameterType="Monster" useGeneratedKeys="true" keyProperty="id">
            INSERT INTO `monster`
            (`age`,`birthday`,`email`,`gender`,`name`,`salary`)
            VALUES(#{age},#{birthday},#{email},#{gender},#{name},#{salary});
        </insert>

      3. @Options 注解属性 解读       option:选择，选项
         useGeneratedKeys = true  返回数据库中设置为自增长的字段的值
         keyProperty = "id" 自增值对应对象的属性
         keyColumn = "id"   自增值对应表的字段
         注意:如果 keyProperty和keyColumn 相同 可以省略keyColumn 比如
              数据库中表的字段是"id" , javaBean 中的属性 也是"id"
              建议keyProperty和keyColumn都写上！更加明确


      */

    @Insert("INSERT INTO `monster` " +
            "(`age`,`birthday`,`email`,`gender`,`name`,`salary`) " +
            "VALUES(#{age},#{birthday},#{email},#{gender},#{name},#{salary});")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    public void addMonster(Monster monster);

    //删除monster
    /*
      xml文件中的配置
        <delete id="delMonster" parameterType="java.lang.Integer">
            DELETE FROM `monster` WHERE `id`=#{id};
        </delete>
    */
    @Delete("DELETE FROM `monster` WHERE `id`=#{id};")
    public void delMonster(Integer id);

    //修改 Monster
    /*
       xml文件中的配置
        <update id="updateMonster" parameterType="Monster">
            UPDATE `monster` SET `age`=#{age},`birthday` = #{birthday},`email` = #{email},
            `gender`=#{gender},`name`=#{name},`salary` = #{salary} WHERE id = #{id};
        </update>
     */
    @Update(" UPDATE `monster` " +
            "SET `age`=#{age},`birthday` = #{birthday},`email` = #{email}, " +
            "`gender`=#{gender},`name`=#{name},`salary` = #{salary} WHERE id = #{id};")
    public void updateMonster(Monster monster);

    //查询-根据 id
      /*
      xml文件中的配置
        <select id="getMonsterById" resultType="Monster">
              SELECT * FROM `monster` WHERE id = #{id};
        </select>
      */
    @Select("SELECT * FROM `monster` WHERE id = #{id};")
    public Monster getMonsterById(Integer id);

    //查询所有的 Monster
    /*
     xml文件中的配置
         <select id="findAllMonster" resultType="Monster">
              SELECT * FROM `monster`;
         </select>
     */
    @Select("SELECT * FROM `monster`;")
    public List<Monster> findAllMonster();




}
