package com.hspedu.entity;

/**
 * @author yangda
 * @create 2023-11-01-21:59
 * @description:
 */
public class Person {

    private Integer id;
    private String name;

    // 因为我们需要实现级联操作，一个Person需要对应一个身份证
    // 这里需要直接定义 IdenCard对象属性
    private IdenCard card; // 该属性的属性名可以任意 ，级联时会在mapper.xml 文件中指定
    // 对应的表的字段column
    //

    public Person() {
    }

    public Person(Integer id, String name, IdenCard card) {
        this.id = id;
        this.name = name;
        this.card = card;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IdenCard getCard() {
        return card;
    }

    public void setCard(IdenCard card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", card=" + card +
                '}';
    }
}
