package com.hspedu.controller;

import com.hspedu.entity.Monster;
import com.hspedu.hspspringmvc.annotation.*;
import com.hspedu.service.MonsterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yangda
 * @create 2023-09-30-22:36
 * @description:
 */
@Controller
public class MonsterController {


    // @AutoWired表示要完成属性的装配
    @AutoWired
    private MonsterService monsterService;


    //编写方法,可以列出妖怪列表
    //springmvc 是支持原生的servlet api , 为了看到底层机制
    //这里我们设计两个参数
    @RequestMapping(value = "/monster/list")
    public void listMonster(HttpServletRequest request, HttpServletResponse response) {
        //设置编码和返回类型
        response.setContentType("text/html;charset=utf-8");

        //调用monsterService
        List<Monster> monsters = monsterService.listMonster();
        StringBuilder content = new StringBuilder("<h1>妖怪列表信息!</h1>");
        content.append("<table width='500px' border='1px'" +
                " style='border-collapse:collapse'>");
        for (Monster monster : monsters) {
            content.append("<tr><td>" + monster.getId() + "</td>" +
                    "<td>" + monster.getName() + "</td>" +
                    "<td>" + monster.getSkill() + "</td>" +
                    "<td>" + monster.getAge() + "</td></tr>");
        }
        content.append("</table>");


        //获取writer返回信息
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/monster/find")
    //public void findMonsterByName(HttpServletRequest request, HttpServletResponse response,
    //                              @RequestParam(value = "name") String name){
    public void findMonsterByName(HttpServletRequest request,
                                  HttpServletResponse response,
                                  String name) {
    //public void findMonsterByName(String name) {
        //设置编码和返回类型
        response.setContentType("text/html;charset=utf-8");


        // 这里没有设置 request.setCharacterEncoding("utf-8"); 接收到的就是中文
        // 可能通过地址栏直接传到后端的数据 发送时的编码格式就是utf-8
        // 但是通过form表单 提交的数据 使用的是url编码 到后端时 出现中文乱码问题
        //
        System.out.println("接收到的name= ===== " + name);




        //调用monsterService
        List<Monster> monsters = monsterService.findMonsterByName(name);
        StringBuilder content = new StringBuilder("<h1>通过名字查询到的列表!</h1>");
        content.append("<table width='500px' border='1px'" +
                " style='border-collapse:collapse'>");
        for (Monster monster : monsters) {
            content.append("<tr><td>" + monster.getId() + "</td>" +
                    "<td>" + monster.getName() + "</td>" +
                    "<td>" + monster.getSkill() + "</td>" +
                    "<td>" + monster.getAge() + "</td></tr>");
        }
        content.append("</table>");


        //获取writer返回信息
        try {
            PrintWriter printWriter = response.getWriter();
            printWriter.write(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // 处理妖怪登录的方法 返回要请求转发/重定向的字符串
    // login() 方法的形参名 mName 不能乱写 需要和前端传递时用到的字段名保持一致
    @RequestMapping("/monster/login")
    //public String login(HttpServletRequest request,
    //                    HttpServletResponse response,
    //                    @RequestParam(value = "mNamex") String mName){
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        String mName){
        System.out.println("接收到mName= " + mName);

        // 解决前端使用post提交表单 接收到的的参数是中文乱码问题
        // 1. 可以在这里设置 request.setCharacterEncoding("utf-8");
        //try {
        //    request.setCharacterEncoding("utf-8");
        //} catch (UnsupportedEncodingException e) {
        //    e.printStackTrace();
        //}

        // 2. 可以在真正调用此方法之前  设置  request.setCharacterEncoding("utf-8");
        //    即在底层解决 真正调用该方法的位置是在中央控制器 HspDispatcherServlet
        //    的executeDispatch() 方法中 通过反射调用目标方法 时调用的
        //    可以在调用之前 从前端获取参数 封装到参数数组之前 即在获取前端参数之前
        //    设置 request.setCharacterEncoding("utf-8"); 解决中文乱码问题

        //
        // 不管是否登录成功 都将mName设置到request域中
        request.setAttribute("mName",mName);
        HttpSession session = request.getSession();
        // 不管是否登录成功 都将mName设置到session域中
        session.setAttribute("mName",mName);

        boolean b = monsterService.login(mName);
        if (b){
            System.out.println("登录成功");
            //// 将登录成功的用户信息 放到request域中
            //request.setAttribute("mName",mName);

            //测试请求转发
            //return "forward:/login_ok.jsp";
            //测试重定向
            //return "redirect:/login_ok.jsp";

            //测试默认
            return "/login_ok.jsp";
        }else{
            System.out.println("登录失败");

            //// 将登录失败的用户信息 放到request域中
            //request.setAttribute("mName",mName);

            return "forward:/login_error.jsp";
            //return "redirect:/login_error.jsp";
        }

    }


    // 编写方法返回json格式数据
    // 1.目标方法返回的结果是给springmvc底层通过反射调用的位置
    // 2.我们在springmvc底层反射调用的位置,接收到结果并解析即可
    // 3.方法上标注了 @ResponseBody 说明想要以json格式返回给客户端/浏览器
    @RequestMapping(value = "/monster/list/json")
    @ResponseBody
    public List<Monster> listMonsterByJson(HttpServletRequest request,
                                           HttpServletResponse response){

        List<Monster> monsters = monsterService.listMonster();



        return monsters;
    }




}
