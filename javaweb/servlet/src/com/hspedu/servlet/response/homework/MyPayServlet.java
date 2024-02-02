package com.hspedu.servlet.response.homework;

import com.hspedu.servlet.servletcontext.WebUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2023-05-27-18:00
 */
public class MyPayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //System.out.println("MyPayServlet 被调用");
//<form action="/servlet/myPayServlet" method="post">
//                用户编号: <input type="text" name="userid"><br/>
//                支付金额: <input type="text" name="money"><br/>
//    <input type="submit" value="点击支付">
//</form>
        // 重定向
        // 获取项目路径

        String contextPath = getServletContext().getContextPath();

        String money = request.getParameter("money");
        System.out.println("money= " + money.getClass()); //如果支付页面什么都不填，是一个空字符串 ""

        int iMoney = WebUtils.parseString(money);
        if (iMoney > 100){
            response.sendRedirect(contextPath + "/payok.html");
            //response.sendRedirect("http://www.baidu.com");
        } else {
            response.sendRedirect(contextPath + "/pay.html");
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
