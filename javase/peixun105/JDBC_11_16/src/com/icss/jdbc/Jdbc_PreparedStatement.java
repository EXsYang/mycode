package com.icss.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_PreparedStatement {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		String password = sc.next();
		try {
			//加载驱动程序
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//获得连接对象
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			//System.out.println(123);
			//创建语句对象
			PreparedStatement pre =conn.prepareStatement("select * from users where name =? and password = ?" );			
			pre.setString(1,"tom");
			pre.setString(2,"123");
			ResultSet rst =pre.executeQuery();
			while(rst.next()){
				System.out.println(rst.getInt("id"));
				System.out.println(rst.getString("name"));
				System.out.println(rst.getString("password"));
				System.out.println(rst.getString("sex"));
				System.out.println(rst.getLong("pnone"));
				System.out.println(rst.getInt("flag"));
				
			}
			//关闭资源
			rst.close();
			pre.close();
			conn.close();
			//System.out.println("子嘻嘻嘻");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}
}
