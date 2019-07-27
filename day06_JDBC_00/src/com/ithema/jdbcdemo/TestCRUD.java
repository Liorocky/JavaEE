package com.ithema.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.junit.Test;

public class TestCRUD {
	@Test
	public void testInsert() throws Exception {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		
		Statement stat = conn.createStatement();
		
		int i = stat.executeUpdate("INSERT into orders VALUES(7,'1992-09-21',2323,'Tom')");
		
		if (i > 0) {
			System.out.println("success");
		}
		
		stat.close();
		conn.close();
	}
	
	@Test
	public void testUpdate() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		Statement stat = conn.createStatement();
		int i = stat.executeUpdate("UPDATE orders SET OrderDate='2003-08-02',OrderPrice=1111,Customer='Tom' where O_Id=7");
		
		if (i > 0) {
			System.out.println("Success" + "修改了" + i + "行");
		}
		
		stat.close();
		conn.close();
	}
	
	@Test
	public void testDelete() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		Statement stat = conn.createStatement();
		int i = stat.executeUpdate("DELETE FROM orders WHERE O_Id=7");
		
		if (i > 0) {
			System.out.println("Success" + "删除了" + i + "行");
		}
	}
	

}
