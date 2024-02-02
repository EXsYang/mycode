package com.icss.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("get");
		//服务器向页面传递代码的乱码问题
		resp.setContentType("text/html;utf-8");
		resp.setCharacterEncoding("utf-8");
		//获取一个输出流
		PrintWriter out = resp.getWriter();
		for (int i = 0; i < 4; i++) {
			out.write("<h1>赵馨东的衣柜a</h1>");
		}
		
		out.close();
	}
			
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("post");
	}

}
