package com.hspedu.servlet;


import com.hspedu.homework.Monster;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author yangda
 * @description:
 * @create 2023-06-18-19:19
 */

public class DataServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("DataServlet 被调用...");
        // 准备Monster 数据
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(100, "小妖怪", "巡山的",345.7));
        monsters.add(new Monster(200, "大妖怪", "做饭的",1345.7));
        monsters.add(new Monster(300, "老妖怪", "打扫位置的",11345.7));
        request.setAttribute("monsters", monsters);

        request.getRequestDispatcher("/homework/show.jsp").forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
