package com.hspedu.mapper;

import com.hspedu.entity.Monster;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author yangda
 * @create 2023-10-17-15:47
 * @description:
 * 这是一个接口 用于定义操作monster表的方法
 * 这些方法可以通过注解或者xml文件来实现
 */


public interface MonsterMapper {


    /**
     * @Param("age") 注解的作用
     * 这里的'age'就是Mapper.xml中test里用来取出方法形参Integer age 的传入的实参值的一个引用
     * ，否则<if test="age >= 0">中的age取不出来值 具体可看老韩mybatis的动态sql-if标签视频
     *
     * 在 MyBatis 的 <if> 标签中使用 test="某条件" 是为了根据特定的条件来决定是否应用 SQL 语句的某个部分。这在编写动态 SQL 时非常有用。但是，当你在这个 <if> 标签中引用方法的参数时，事情就会变得有些复杂，特别是当方法的参数是一个简单类型（如 Integer、String 等），而不是一个对象（POJO）时。
     * 在 MyBatis 中，如果方法参数是简单类型，你通常不能直接在 <if> 测试表达式中通过名称引用它，除非你使用了 @Param 注解来明确命名这个参数。
     * 解决方案1（推荐）：
     * 使用 @Param("age") 将方法参数命名为 age，这样你就可以在 <if> 表达式中直接通过 age 引用这个参数：
     * public List<Monster> findMonsterByAge(@Param("age") Integer age);
     * 然后在 MyBatis 的 XML 文件中：
     * <if test="age >= 0">
     *     AND `age` > #{age}
     * </if>
     * 这样，age 就明确指向方法参数，没有歧义。
     *
     */
    //根据 age 查询结果
    // public List<Monster> findMonsterByAge(Integer age);
    public List<Monster> findMonsterByAge(@Param("age") Integer age);

    //根据 id 和名字来查询结果
    public List<Monster> findMonsterByIdAndName(Monster monster);

    //测试 choose 标签的使用
    public List<Monster>
    findMonsterByIdAndName_choose(Map<String, Object> map);

    //测试 foreach 的标签使用(传入的是一个Map类型)
    public List<Monster>
    findMonsterById_forEach(Map<String, Object> map);

    //测试 foreach 的标签使用2(传入的是一个List类型)
    public List<Monster>
    findMonsterById_forEach2(List<Monster> monsters);

    //trim 标签的使用
    public List<Monster> findMonsterByName_Trim(Map<String, Object> map);

    //测试 Set 标签
    public void updateMonster_set(Map<String, Object> map);
}
