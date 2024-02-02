package com.hspedu.entity;

/**
 * @author yangda
 * @create 2023-11-01-21:59
 * @description:
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

