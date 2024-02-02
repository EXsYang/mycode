package com.hspedu.mhl.domain;

import java.util.Date;

/**
 * @author yangda
 * @description: 这是一个javabean 可以和多张表进行对应
 * @create 2023-05-08-19:50
 */
public class MultiTableBean { //Javabean, POJO, Domain ,entity对象
    private Integer id;
    private String billId;
    private Integer menuId;
    private Integer nums;
    private Double money;
    private Integer diningTableId;
    private Date billDate;
    private String state;
    // 增加一个来自menu 表的列属性 name
//    private String name;

    // 思考，这里的属性名是否需要和表的列名保持一致？
    // 可以不一致 ，但是需要sql做相应修改，规范需要保持一致。
    // 规范：sql语句中通过列名 找对应对象的setName方法，通过 列名 把值 设置 给javaBean的属性，
    // 这里的Name 就是sql语句写的查询语句
    // 或者多个表存在有相同的列名，如订餐人叫name,菜品名也叫name,那就麻烦了，同一个类中两个属性同名不行
    // 在多表的bean 中 只能一个叫name ,一个叫name2 进行区分
    // 此时可以在sql语句中用别名加以区分，SELECT bill.*,NAME AS name2 FROM bill,menu WHERE bill.`menuId`=menu.`id`
    // sql 中使用name2 别名寻找setName2() 进行赋值，可以对此区分
    private String name2;


    // 增加一个来自menu 表的列属性 price
    private Double price;//默认为null

    public MultiTableBean() {
    }

    // 没有用到带参构造器，走的是set()方法 ,有参构造器可要可不要！如DBUtils的土方法，Actor类的封装是用set()赋值的
//    public MultiTableBean(Integer id, String billId, Integer menuId, Integer nums, Double money, Integer diningTableId, Date billDate, String state, String name, Double price) {
//        this.id = id;
//        this.billId = billId;
//        this.menuId = menuId;
//        this.nums = nums;
//        this.money = money;
//        this.diningTableId = diningTableId;
//        this.billDate = billDate;
//        this.state = state;
//        this.name = name;
//        this.price = price;
//    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getNums() {
        return nums;
    }

    public void setNums(Integer nums) {
        this.nums = nums;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getDiningTableId() {
        return diningTableId;
    }

    public void setDiningTableId(Integer diningTableId) {
        this.diningTableId = diningTableId;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name) {
        this.name2 = name;
    }

    @Override
    public String toString() {
        return "MultiTableBean{" +
                "id=" + id +
                ", billId='" + billId + '\'' +
                ", menuId=" + menuId +
                ", nums=" + nums +
                ", money=" + money +
                ", diningTableId=" + diningTableId +
                ", billDate=" + billDate +
                ", state='" + state + '\'' +
                ", name='" + name2 + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
