package com.hspedu.controller;

import com.hspedu.hspspringmvc.annotation.Controller;
import com.hspedu.hspspringmvc.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author yangda
 * @create 2023-10-01-16:50
 * @description:
 */
@Controller
public class OrderController {

    @RequestMapping(value = "/order/list")
    public void listOrder(HttpServletRequest request, HttpServletResponse response){
        //设置编码和返回类型
        response.setContentType("text/html;charset=utf-8");
        //获取writer返回信息
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("<h1>订单列表信息</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping(value = "/order/add")
    public void addOrder(HttpServletRequest request, HttpServletResponse response){
        //设置编码和返回类型
        response.setContentType("text/html;charset=utf-8");
        //获取writer返回信息
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write("<h1>添加订单... </h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
