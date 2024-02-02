package com.hspedu.furns.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yangda
 * @description: 表示订单
 * @create 2023-08-05-12:54
 */
public class Order {
//    CREATE TABLE `order`(
//            `id` VARCHAR(64) PRIMARY KEY, -- 订单号	可能是sn-000001 而不是一个纯数字!
//            `create_time` DATETIME NOT NULL, -- 订单生成时间
//`price` DECIMAL(11,2) NOT NULL, -- 订单的金额
//`status` TINYINT NOT NULL, -- 状态 0 未发货 1 已发货 2 已结账
//`member_id` INT NOT NULL  --  该订单对应的会员id
//)CHARSET utf8 ENGINE INNODB;

    private String id;
    private Date createTime;
    private BigDecimal price;
    private Integer status;
    private Integer memberId;

    //一定要提供无参构造器, 否则反射会出问题.
    public Order() {
    }

    public Order(String id, Date createTime, BigDecimal price, Integer status, Integer memberId) {
        this.id = id;
        this.createTime = createTime;
        this.price = price;
        this.status = status;
        this.memberId = memberId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", createTime=" + createTime +
                ", price=" + price +
                ", status=" + status +
                ", memberId=" + memberId +
                '}';
    }
}
