package com.hspedu.javabean;

import java.util.Date;

/**
 * @author yangda
 * @description:
 * @create 2023-06-29-0:48
 */
public class User {
    private Integer id;
    private String username;
    //private String pwd;   一般情况下javabean 属性名和数据库列名一定要一样
    // 第一次和数据库列名不一样
    // 但是数据库是password 列名不一定要相同！！！ 可以在查询时用别名的方式进行区分
    /*

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

        // 没有用到带参构造器，走的是set()方法
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
*/


    private String password;
    private String email;

    public User() { // 一定要给空参构造器，反射要用  //必须提供一个无参构造器, 是给我们的反射使用
    }

    public User(Integer id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
