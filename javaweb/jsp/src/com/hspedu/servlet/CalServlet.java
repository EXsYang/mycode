package com.hspedu.servlet;

import com.hspedu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author yangda
 * @description:
 * @create 2023-06-17-0:54
 */
public class CalServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取表单提交的信息
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");

        boolean flag = false;

        // 添加直接走后端Servlet的验证 前端验证通过才会走到这里，而验证通过的进不去下面的语句  num1 num2
        if (!num1.matches("[-]?([1-9]\\d*|0)") || !num2.matches("[-]?([1-9]\\d*|0)")) {
            System.out.println("验证失败，num1或者num2不是整数");
            // 验证失败 返回calUI界面 并添加 提示信息 num1错误信息：xxx
            // 设置Session 属性
            HttpSession session = req.getSession();
            session.setAttribute("num1", "num1和num2都为整数");
            //resp.sendRedirect(req.getContextPath() + "/cal/calUI.jsp");
            //return;

            //System.out.println("验证失败，num2不是整数");
            // 验证失败 返回calUI界面 并添加 提示信息 num1错误信息：xxx
            // 设置Session 属性
            session.setAttribute("num2", "num1和num2都为整数");
            session.setMaxInactiveInterval(3);
            resp.sendRedirect(req.getContextPath() + "/cal/calUI.jsp");

            return;
        }


        System.out.println("后端验证成功，都是整数");
        // 验证失败 返回calUI 并提示 num1错误信息：xxx


        // 将获取到的数据转换为数值型
        double parseDouble01 = WebUtils.parseDouble(num1, 0);
        double parseDouble02 = WebUtils.parseDouble(num2, 0);

        //进行运算
        //得到应该使用的运算方式
        String operator = req.getParameter("operator");
        double sum = 0;
        String oper = "+";
        if ("add".equals(operator)) {
            sum = parseDouble01 + parseDouble02;
            oper = "+";
        } else if ("subtract".equals(operator)) {
            sum = parseDouble01 - parseDouble02;
            oper = "-";
        } else if ("multiply".equals(operator)) {
            sum = parseDouble01 * parseDouble02;
            oper = "*";
        } else if ("divide".equals(operator)) {
            sum = parseDouble01 / parseDouble02;
            oper = "/";
        }
        //System.out.println(parseDouble01 + oper + parseDouble02 + " = " + sum);
        String formatRes = String.format("%s %s %s = %s", parseDouble01, oper, parseDouble02, sum);
        System.out.println(formatRes);
        // 将结果设置给域对象 request 进行保存
        req.setAttribute("res", formatRes);

        // 请求转发，对页面进行显示
        //System.out.println("req.getContextPath()= " + req.getContextPath());//
        req.getRequestDispatcher("/cal/calRes.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
