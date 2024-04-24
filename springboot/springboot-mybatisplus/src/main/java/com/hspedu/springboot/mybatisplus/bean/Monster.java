package com.hspedu.springboot.mybatisplus.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author yangda
 * @create 2023-12-17-21:19
 * @description:
 *
 * 1.如果实体类Monster和表名monster是对应的，就可以映射上，则@TableName可以省略
 * 2.如果实体类Monster和表名monster_不对应，则需要使用@TableName进行指定
 *
 * @TableName("your_table_name")
 * public class YourEntity {
 *     // 类的属性与数据库列的映射
 *     // ...
 * }
 *`YourEntity` 类通过 `@TableName` 注解与数据库中的 `your_table_name` 表相关联。如果不使用 `@TableName` 注解，MyBatis-Plus 默认会使用类名的下划线形式作为表名（例如，`UserDetail` 类默认对应 `user_detail` 表）。
 *
 * 除了 `@TableName`，还有其他一些注解也很有用，比如：
 *
 * - `@TableId`：指定类的某个属性为数据库表的主键。
 * - `@TableField`：指定类的属性与数据库表中的某个字段的映射关系，尤其在属性名和字段名不一致时非常有用。
 *
 * 通过这些注解，你可以灵活地定义 JavaBean 类和数据库表之间的映射关系，从而使得 ORM（对象关系映射）更加直观和容易管理。
 *
 * 在 MyBatis-Plus 或 MyBatis 中，配置项 map-underscore-to-camel-case 主要影响的是属性（字段）的命名映射，而不是类名与表名的映射。
 * 当这个配置项设置为 true时some_column）映射到 Java 实体类的属性（通常使用驼峰命名法，如 someColumn）。
 * 但对于类名到表名的映射，如果不使用 @TableName注Monster 类在 MyBatis-Plus 中默认对应的数据库表名将是 monster。
 *
 * --------------------------------
 *在springboot+mybatis-plus中指定表名和JavaBean类名的映射关系可以用@TableId，指定表的字段名和JavaBean属性的映射关系可以使用@TableField，而在springboot+mybatis中是用的在mapper.xml文件中resultType指定的表名和JavaBean属性的映射关系，在resultMap中也可以指定表名和JavaBean属性的映射关系以及表的字段名和JavaBean属性的映射关系对吗？你有什么补充的吗？
 *
 * GPT
 * 您的理解基本正确，我可以为您补充一些细节：
 * Spring Boot + MyBatis-Plus：
 *
 * =====================================================================================
 *@TableName 注解用于指定一个实体类映射到数据库中的哪个表。如果实体类的名称和表的名称不一致，就需要使用这个注解来建立映射关系。
 *@TableId 注解用于指定实体类中哪个属性映射到数据库表的主键字段。这个注解还可以指定主键生成策略，例如自增、UUID等。
 *@TableField 注解用于指定实体类中的属性与数据库表中的字段的映射关系。这个注解非常有用，特别是当属性名和字段名不一致时。
 * 在Mybatis-Plus中默认启用自动驼峰命名，除非字段名与属性名的映射不遵循下划线转驼峰的规则，或者有特殊映射需求，才需要显式使用@TableField。
**
* 示例如下：
 * @TableName("tbl_wife") //假设数据库中有一个表名为 tbl_wife，而你希望映射到这个表的实体类名为 Wife
 * public class Wife {
 *     @TableId(value = "wife_id", type = IdType.AUTO) //type = IdType.AUTO 表明主键是自增类型，MyBatis Plus 会自动处理这种自增主键。
 *     private Integer id;
 *
 *     @TableField("wife_name") //假设数据库表 tbl_wife 中有一个字段名为 wife_name，而实体类 Wife 中对应的属性名为 name
 *      private String name;
 *     // getter and setter
 * }
 * =====================================================================================
 * Spring Boot + MyBatis：
 *
 * 在MyBatis中，通常使用XML映射文件或注解来定义SQL语句和结果映射。
 * resultType 是当返回的结果是单一实体时使用，它可以直接映射到实体类，前提是SQL查询的列名和实体类属性名要匹配。
 * resultMap 更为复杂和灵活，它允许你详细地指定如何从数据库结果集中映射数据到Java对象，包括不同名的字段映射，以及复杂的类型映射（如集合和关联对象）。
 * 需要注意的是，MyBatis-Plus提供了更多自动化和简化的操作，而纯MyBatis则提供了更多的灵活性和控制权。选择使用哪个主要取决于项目需求和个人偏好。
 */
// @TableName("monster_")
@TableName("monster")
@Data
public class Monster {
    private Integer id;
    private Integer age;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date birthday;
    private String email;
    private String name;
    private String gender;
    private Double salary;
}
