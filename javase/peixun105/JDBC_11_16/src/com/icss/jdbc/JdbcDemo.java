package com.icss.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo {

	public static void main(String[] args) {
		
		try {
			//������������
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//������Ӷ���
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","scott","tiger");
			System.out.println(123);
			//����������
			Statement sta = conn.createStatement();
			//ִ��SQL��� ִ��DELETE,UPDATE��INSERT Statement�����executeUpdate����ִ��
			int x = sta.executeUpdate("insert into users values(seq_users.nextval,'tom','123','��',12345,0)");
			System.out.println("x:"+x);
			
			//�ر���Դ
			sta.close();
			conn.close();
			System.out.println("��������");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){
			e.printStackTrace();
		}
	}

}
