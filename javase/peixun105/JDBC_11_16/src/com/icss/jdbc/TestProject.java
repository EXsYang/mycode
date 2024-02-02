package com.icss.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class TestProject {
	Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		new TestProject();
	}
	TestProject(){
		Users user = login();
		if(user != null){
			System.out.println("��½�ɹ�");
		}else{
			System.out.println("������"+2+"����");
		}
	}
	
	private Users login() {
		System.out.println("=============");
		System.out.println("=====��¼====");
		System.out.println("�û�����");
		String name = sc.next();
		System.out.println("���룺");
		String password = sc.next();
		Users user = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			PreparedStatement pre =  conn.prepareStatement("select * from users where name = ? and password  = ?");
			pre.setString(1,name);
			pre.setString(2,password);
			ResultSet rst  = pre.executeQuery();
			while(rst.next()) {
				System.out.println(rst.getInt("id"));
				System.out.println(rst.getString("name"));
				System.out.println(rst.getString("password"));
				System.out.println(rst.getString("sex"));
				System.out.println(rst.getLong("phone"));
				System.out.println(rst.getInt("flag"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
		return user;
	}
}
