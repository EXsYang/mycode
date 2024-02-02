package com.hspedu.furns.dao.daoimpl;

import com.hspedu.furns.dao.BasicDAO;
import com.hspedu.furns.dao.OrderDAO;
import com.hspedu.furns.entity.Order;

import java.util.List;

/**
 * @author yangda
 * @description: 订单表的DAO层实现类
 * @create 2023-08-05-17:58
 */
public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {

    /**
     * saveOrder 保存订单方法
     * @param order
     * @return 受影响的行数
     */
    @Override
    public Integer saveOrder(Order order) {
        // 构建sql
        String sql = "INSERT INTO `order`(`id`,`create_time`,`price`,`status`,`member_id`) " +
                "VALUES(?,?,?,?,?)";

        //数据库是datetime类型 java中可以直接传进去一个java.util.Date 类型
        // 即数据库的datetime类型 在java中可以使用Date类型进行对应
        // 虽然 java.util.Date 类型 . 默认输出的日期格式是国外的方式 但是没影响
        //new Date()

        ////格式化当前日期 在构建Order对象时 格式化日期并放入Order对象中
        ////1. 使用 now() 返回表示当前日期时间的 对象
        //LocalDateTime ldt = LocalDateTime.now(); //LocalDate.now();//LocalTime.now()
        //System.out.println(ldt);
        ////2. 使用 DateTimeFormatter 对象来进行格式化
        //// 创建 DateTimeFormatter 对象
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //String format = dateTimeFormatter.format(ldt);
        //System.out.println("格式化的日期=" + format);//2023-08-05 18:17:46

        return update(sql,order.getId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getMemberId());

    }

    @Override
    public List<Order> queryOrderByMemberId(Integer memberId) {

        // 构建sql
        String sql = "SELECT `id`,`create_time` `createTime`,`price`,`status`,`member_id` `memberId` FROM `order` WHERE `member_id` = ?";

        return queryMulti(sql,Order.class,memberId);
    }

    @Override
    public Order queryOrderById(String id) {

        // 构建sql
        String sql = "SELECT `id`,`create_time` `createTime`,`price`,`status`,`member_id` `memberId` FROM `order` WHERE `id` = ?";


        return querySingle(sql,Order.class,id);
    }
}
