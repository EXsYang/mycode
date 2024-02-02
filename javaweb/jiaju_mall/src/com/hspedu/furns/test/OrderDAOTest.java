package com.hspedu.furns.test;

import com.hspedu.furns.dao.OrderDAO;
import com.hspedu.furns.dao.daoimpl.OrderDAOImpl;
import com.hspedu.furns.entity.Order;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-08-05-18:09
 */
public class OrderDAOTest {

    private OrderDAO orderDAO = new OrderDAOImpl();

    @Test
    public void saveOrder(){
        //long start = System.currentTimeMillis();

        //1. 使用 now() 返回表示当前日期时间的 对象
        //LocalDateTime ldt = LocalDateTime.now(); //LocalDate.now();//LocalTime.now()
        //System.out.println(ldt);
        //2. 使用 DateTimeFormatter 对象来进行格式化
        // 创建 DateTimeFormatter 对象
        //DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //String format = dateTimeFormatter.format(ldt);
        //System.out.println("格式化的日期=" + format);//2023-08-05 18:17:46
        //// 创建Order对象
        Order order = new Order("sn-00002", new Date(), new BigDecimal(330), 0, 10);

        Integer affectedRow = orderDAO.saveOrder(order);
        System.out.println(affectedRow);


        //System.out.println(new Date());//Sat Aug 05 18:13:46 CST 2023 默认输出是没有格式化过的




    }

    @Test
    public void queryOrderByMemberId(){

        List<Order> orders = orderDAO.queryOrderByMemberId(10);
        System.out.println(orders);


    }
    @Test
    public void queryOrderById(){

        Order order = orderDAO.queryOrderById("1691403140053-10");
        System.out.println(order);


    }
}
