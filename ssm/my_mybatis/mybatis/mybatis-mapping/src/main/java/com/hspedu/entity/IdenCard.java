package com.hspedu.entity;

/**
 * @author yangda
 * @create 2023-11-01-21:59
 * @description:
 *
 * MyBatis 和 MyBatis Plus 关于表名的映射关系说明：
   结论:
 * MyBatis: 需要在每个 SQL 映射中手动指定表名，没有内置的自动映射功能。
 * MyBatis Plus: 通过 @TableName 注解提供表名的自动映射，支持动态表名，增加了开发的便利性和灵活性。
 *
 * 在 MyBatis 中，表名通常是硬编码在 XML 映射文件或者注解中的。
 * MyBatis 本身不提供表名的自动映射功能，开发者需要在每个 SQL 映射中明确指定使用的表名。
 *
 * MyBatis Plus 提供了更高级的映射机制，包括对表名的自动映射。
 * 这是通过在实体类上使用 @TableName 注解来实现的。这样，你可以在一个地方定义表名，MyBatis Plus 会在执行任何 CRUD 操作时自动使用正确的表名。
 *
 *
 * ========================================================================================
 * MyBatis 和 MyBatis Plus 关于entity属性名和数据库字段名的映射关系说明：
  结论:
 * MyBatis: 需要属性名和字段名完全一致，或者通过 <resultMap> 明确指定映射关系。
 * MyBatis Plus: 提供自动的驼峰命名转换，更加灵活
 *
 * 详细解释如下:
 * 如果在 MyBatis 的 <resultMap> 中没有明确指定映射关系，MyBatis 默认采用属性名与字段名相同的规则来自动匹配。这意味着，JavaBean 的属性名必须与数据库表的字段名完全一致（包括字母的大小写），否则这些字段将无法被正确封装，其对应的属性值会是 null。
 *
 * 例如，如果数据库中的字段名为 wife_name，而 JavaBean 中的属性名为 wifeName，不使用 <result> 指定映射关系的话，wifeName 属性将会得到 null 值。
 *
 * 2. 指定映射关系
 * 为了解决字段名与属性名不一致的问题，可以在 <resultMap> 中使用 <result> 标签明确指定映射关系。例如：
 *
 * xml
 * Copy code
 * <resultMap id="resultWifeMap" type="Wife">
 *     <result property="wifeName" column="wife_name"/>
 * </resultMap>
 * 这样配置后，wife_name 字段的值就会正确地映射到 wifeName 属性上。
 *
 * ------------------------------------------------
 * MyBatis Plus 是 MyBatis 的一个扩展框架，它提供了更加强大的映射功能，
 * 包括自动的驼峰命名转换（即自动将数据库中的下划线分隔命名转换为 JavaBean 中的驼峰式命名）。
 * 这意味着，在 MyBatis Plus 中，即便不通过 <resultMap> 明确指定映射关系，
 * wife_name 也能自动映射到 wifeName 属性。
 */
public class IdenCard {


    private Integer id;
    // 身份证号
    private String card_sn;

    // 增加一个属性 用于级联person ，即通过查询IdenCard 也可以级联查询搭配Person
    private Person person;

    public IdenCard() {
    }


    public IdenCard(Integer id, String card_sn, Person person) {
        this.id = id;
        this.card_sn = card_sn;
        this.person = person;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCard_sn() {
        return card_sn;
    }

    public void setCard_sn(String card_sn) {
        this.card_sn = card_sn;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "IdenCard{" +
                "id=" + id +
                ", card_sn='" + card_sn + '\'' +
                ", person=" + person +
                '}';
    }
}

