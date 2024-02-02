package com.hspedu.furns.web;

import com.hspedu.furns.entity.Cart;
import com.hspedu.furns.entity.Member;
import com.hspedu.furns.entity.Order;
import com.hspedu.furns.entity.OrderItem;
import com.hspedu.furns.service.OrderItemService;
import com.hspedu.furns.service.OrderService;
import com.hspedu.furns.service.impl.OrderItemServiceImpl;
import com.hspedu.furns.service.impl.OrderServiceImpl;
import com.hspedu.furns.utils.DataUtils;
import com.hspedu.furns.utils.JDBCUtilsByDruid;
import com.jhlabs.image.DoGFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-08-05-18:54
 */
public class OrderServlet extends BasicServlet {

    //提供属性
    private OrderService orderService = new OrderServiceImpl();

    private OrderItemService orderItemService = new OrderItemServiceImpl();

    /**
     * 生成订单方法
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void saveOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("OrderServlet saveOrder() 被调用...");

        // 从session中获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");


        //如果cart == null , 说明会员没有购买任何家居, 转发到首页
        //这里我们需要补充一个逻辑, 购物车在session, 但是没有家居数据
        //购物车为null 或 买过一次但是购物车是空的 如果购物车里没有商品 就转发到index.jsp
        if (cart == null || cart.isEmpty()) {
            //直接重定向到主页面 进行购物
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;//直接返回
        }

        //获取当前登录的memberId
        // 从session中获取member对象
        Member member = (Member) req.getSession().getAttribute("member");
        if (member == null) { // //说明用户没有登录, 转发到登录页面
            req.getRequestDispatcher("/views/member/login.jsp").forward(req, resp);
            return;//直接返回
        }

        //就可以生成订单
        // 到这里购物车不为null 并且 购物车中有商品 并且登录过member不为空 可以下订单
        //String orderId = orderService.saveOrder(cart, member.getId());


        //String orderId = null;
        //try {
        //    // 下面这行代码 发生异常 不论connection是否设置了自动提交
        //    // 即这里发生了异常 就会先走本类里的try-catch 不会走上一个调用者
        //    // BasicServlet.java  doPost()中
        //    // declaredMethod.invoke(this, req, resp); 外面的try-catch
        //
        //    orderId = orderService.saveOrder(cart, member.getId());
        //    JDBCUtilsByDruid.commit();
        //} catch (Exception e) {
        //    JDBCUtilsByDruid.rollback();
        //    e.printStackTrace();
        //}

        String orderId = orderService.saveOrder(cart, member.getId());

        // 下完订单后checkout.jsp 将返回的订单号 放入到域对象中用于前端展示
        req.getSession().setAttribute("orderId", orderId);

        //使用重定向方式到 checkout.jsp
        resp.sendRedirect(req.getContextPath() + "/views/order/checkout.jsp");


    }

    /**
     * 订单管理
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void orderManage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("OrderServlet orderManage() 被调用...");
        // 根据memberId 取出所有的相关联的订单 list 并展示

        int memberId = DataUtils.parseInt(req.getParameter("memberId"), 0);

        List<Order> orders = orderService.queryOrderByMemberId(memberId);

        // 将订单信息返回给前端
        req.getSession().setAttribute("orders", orders);

        // 重定向到order.jsp
        resp.sendRedirect(req.getContextPath() + "/views/order/order.jsp");

    }

    /**
     * 订单详情 显示一个订单中的所有的订单项
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void orderDetail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 根据前端传过来的orderId 查询所有的订单项
        System.out.println("OrderServlet orderDetail() 被调用...");

        String orderId = req.getParameter("orderId");

        List<OrderItem> orderItems = orderItemService.queryOrderItemByOrderId(orderId);

        // 将该订单对象也放入到域对象中 在前端页面order_detail可以用来获取订单号
        Order order = orderService.queryOrderById(orderId);
        req.setAttribute("order", order);

        // 将查询的结果orderItems设置到域对象中
        req.setAttribute("orderItems", orderItems);

        // 订单里的商品总数
        int totalCount = 0;
        for (OrderItem orderItem : orderItems) {
            totalCount += orderItem.getCount();
        }
        req.setAttribute("totalCount", totalCount);

        // 请求转发到order_detail.jsp
        //2 老师说明: 如果请求带来的参数 orderId=? , 而且是请求转发到下一个页面, 在下一个页面可以通过 param.orderId
        // 使用的请求转发可以使用 param
        req.getRequestDispatcher("/views/order/order_detail.jsp").forward(req, resp);


    }
}
