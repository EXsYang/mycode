package com.hspedu.mapper;

import com.hspedu.entity.Hero;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author yangda
 * @create 2023-10-31-17:16
 * @description:
 */
public interface HeroMapper {

    //编写方法，添加hero记录[方法就在Mapper.xml配置]
    public void addHero(Hero hero);

    //编写方法，查询rank 大于 10的所有hero,如果输入的rank不大于0，则输出所有hero
    // 这种入参方式，在mapper.xml test表达式中，任意一个名称都可以取出由此传入的值
    /* AND `rank` > #{rank}; sql 表达式中的rank
    注意:这里在只有一个参数的情况下 #{id}即使写成 #{idx}也可以获取到数据
    即此时可以是任意字符，都可以取出形参(Integer rank)传入的值*/
    // public List<Hero> findHeroByRank(Integer rank);

    /**
     * 在MyBatis中，当你在Mapper接口的方法中使用了@Param注解来标注参数时，这个注解实际上是在为这个参数创建一个指定的名称，这样你就可以在XML映射文件中通过这个名称引用这个参数。
     *
     * 在您提供的代码示例中，@Param("rank") 注解将方法参数 Integer rank 的值与名为 "rank" 的参数名称相关联。这意味着在XML映射文件中，只有当你使用 #{rank} 时，才能正确地引用到这个传入的参数值。这是因为 #{rank} 中的 "rank" 必须与 @Param 注解中指定的值相匹配。
     *
     * 这种做法在处理多个参数的情况下尤为重要，因为它允许你在SQL语句中明确指定哪个参数是哪个值，避免了混淆。例如，如果你有多个参数，如 @Param("rank") Integer rank 和 @Param("name") String name，在XML文件中你可以通过 #{rank} 和 #{name} 分别引用它们。
     *
     * 简而言之，@Param 注解在MyBatis中用于在Mapper接口方法的参数与XML映射文件中的SQL语句之间建立明确的对应关系。
     *
     * 如果我写的@Param("rankx")呢？
     * GPT
     * 如果您在Mapper接口方法中使用 @Param("rankx") 而不是 @Param("rank")，那么您需要在XML映射文件的相应SQL语句中使用 #{rankx} 来引用这个参数。
     *
     * 例如，如果您的方法定义是这样的：
     *
     * java
     * Copy code
     * public List<Hero> findHeroByRank(@Param("rankx") Integer rank);
     * 那么，在您的XML映射文件中，您应该使用 #{rankx} 而不是 #{rank}，如下所示：
     *
     * xml
     * Copy code
     * <select id="findHeroByRank" parameterType="Integer" resultType="Hero">
     *     SELECT * FROM `hero`
     *     <where>
     *         <if test="rankx > 0">
     *             AND `rank` > #{rankx};
     *         </if>
     *     </where>
     * </select>
     * 在这个例子中，#{rankx} 与 @Param("rankx") 中的 "rankx" 相匹配，确保了正确的参数值被传递到SQL语句中。如果您在XML文件中仍然使用 #{rank}，MyBatis将无法找到名为 rank 的参数，因为在方法签名中该参数被命名为 rankx。这可能导致错误或者不正确的行为，因为MyBatis无法识别和匹配正确的参数。
     */
    // 这种入参方式，在mapper.xml test表达式中，
    // 只有和@Param 设置的 value="rank" 中的rank一致可以取出由此传入的值
    /* AND `rank` > #{rank}; sql 表达式中的rank
    只有和@Param 设置的 value="rank" 中的rank一致才可以取出形参(@Param("rank") Integer rank)传入的值*/
    // public List<Hero> findHeroByRank(@Param("rank") Integer rank);

    // 直接传入一个对象的方式 在mapper.xml test表达式中，使用test="rank > 0"
    // ，调用的是入参对象的getter方法，test中不能随意填写，否则会报如下错误信息
    // ReflectionException: There is no getter for property named 'rankx' in 'class com.hspedu.entity.Hero'
     /* AND `rank` > #{rank}; sql 表达式中的rank
    只有和Hero JavaBean 中的属性 rank 一致才可以取出形参(Hero hero)传入的值
    否则抛出异常 .ReflectionException: There is no getter for property named 'rankx' in 'class com.hspedu.entity.Hero'
    */
    // public List<Hero> findHeroByRank(Hero hero);

    // 形参位置传入map的方式 在mapper.xml test表达式中，使用test="rank > 0"
    // ，是根据传入的map集合中是否存在对应的key值="rank"，test中不能随意填写，
    // 否则找不到 即test中不成立，不会进去if 但不会报错
      /* AND `rank` > #{rank}; sql 表达式中的rank
    只有和 map 中的key "rank" 匹配上/一致才可以取出形参(Map<String,Object> map)传入的rank值
    */
    public List<Hero> findHeroByRank(Map<String,Object> map);


    //编写方法，查询rank为 3,6,8[rank可变]的hero
    // public List<Hero> findHeroByRanks_foreach(Map<String,List> map);
    // 使用Map<String,Object> 更好,实际上传入的是 String-List 键值对
    public List<Hero> findHeroByRanks_foreach(Map<String,Object> map);

    //编写方法，修改hero信息，如果没有设置新的属性值，则保持原来的值
    public void updateHero_set(Hero hero);

    //编写方法，可以根据id查询hero,如果没有传入id,就返回所有hero
    public List<Hero> findHeroById(Map<String,Object> map);

    //编写方法，可以根据id或者name查询hero,如果传入了id 就使用id查询,
    // 如果没有传入id,就使用name查询, 如果name也没传入,默认查询 rank < 3 的hero
    public List<Hero> findHeroByIdOrName_choose(Map<String,Object> map);
}
