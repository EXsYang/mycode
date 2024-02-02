package com.icss.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("g2");
		String name = req.getParameter("username");
		System.out.println(name);
		
		String hobbys[] = req.getParameterValues("hobby");
		for (int i = 0; i < hobbys.length; i++) {
			System.out.println(hobbys[i]);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("p2");
	}

}
