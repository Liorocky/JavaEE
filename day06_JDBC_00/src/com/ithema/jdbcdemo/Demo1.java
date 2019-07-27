package com.ithema.jdbcdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ithema.entity.User;


public class Demo1 {
	@Test
	public void testSelect() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
		Statement stat = conn.createStatement();
		ResultSet rs = stat.executeQuery("SELECT O_id,OrderDate,OrderPrice,Customer FROM orders");
		
		List<User> list = new ArrayList<User>();
		
		while (rs.next()) {
			User u = new User();
			u.setO_id(rs.getInt("O_Id"));
			u.setOrderDate(rs.getDate("OrderDate"));
			u.setOrderPrice(rs.getInt("OrderPrice"));
			u.setCustomer(rs.getString("Customer"));
			list.add(u);
		}
		
		for (User user : list) {
			System.out.println(user);
		}
		
		rs.close();
		stat.close();
		conn.close();
		
	}
	

	@Test
	public void testRS() throws Exception {
	//register driver
			Class.forName("com.mysql.jdbc.Driver");
			
			//get connection
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			
			//get statement
			Statement stmt = conn.createStatement();
			
			//execute sql 
			ResultSet rs = stmt.executeQuery("select * from orders");
			
			//deal with result
			while (rs.next()) {
				System.out.println(rs.getObject(1));			
				System.out.println(rs.getObject(2));			
				System.out.println(rs.getObject(3));			
				System.out.println(rs.getObject(4));			
				System.out.println("----------");
			}
			
			//close source
			rs.close();
			stmt.close();
			conn.close();
	}

}
