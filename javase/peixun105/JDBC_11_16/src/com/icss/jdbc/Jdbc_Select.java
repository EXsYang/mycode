package com.icss.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Jdbc_Select {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		String password = sc.next();
		
		try {
			//������������
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//������Ӷ���
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			System.out.println(123);
			//����������
			Statement sta = conn.createStatement();
			//ִ��SQL��� ִ��DELETE��UODATE��INSERT Statement�����executeUpdate����ִ��
			ResultSet rst = sta.executeQuery("select * from users where name='"+name+"' and password = '"+password+"'");
			while(rst.next()){
				System.out.println(rst.getInt("id"));
				System.out.println(rst.getInt("name"));
				System.out.println(rst.getInt("password"));
				System.out.println(rst.getInt("sex"));
				System.out.println(rst.getInt("phone"));
				System.out.println(rst.getInt("flag"));
			}
			
			//�ر���Դ
			sta.close();
			conn.close();
			System.out.println("xxxxxx");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			
		}
	}

}
