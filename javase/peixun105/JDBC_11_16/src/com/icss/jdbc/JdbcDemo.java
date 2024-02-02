package com.icss.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo {

	public static void main(String[] args) {
		
		try {
			//加载驱动程序
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//获得连接对象
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			System.out.println(123);
			//创建语句对象
			Statement sta = conn.createStatement();
			//执行SQL语句 执行DELETE,UPDATE和INSERT Statement对象的executeUpdate方法执行
			int x = sta.executeUpdate("insert into users values(seq_users.nextval,'tom','123','男',12345,0)");
			System.out.println("x:"+x);
			
			//关闭资源
			sta.close();
			conn.close();
			System.out.println("子嘻嘻嘻");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

}
