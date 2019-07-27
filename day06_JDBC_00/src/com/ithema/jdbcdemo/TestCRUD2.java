package com.ithema.jdbcdemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.ithema.entity.User;
import com.ithema.util.DBUtils;

public class TestCRUD2 {
	@Test
	public void testSelect() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			conn = DBUtils.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from orders");
			
			List<User> list = new ArrayList<User>();
			
			while (rs.next()) {
				User u = new User();
				u.setO_id(rs.getInt("O_id"));
				u.setOrderDate(rs.getDate("OrderDate"));
				u.setOrderPrice(rs.getInt("OrderPrice"));
				u.setCustomer(rs.getString("Customer"));
				list.add(u);
			}
			
			DBUtils.closeAll(rs, stmt, conn);
			
			for (User user : list) {
				System.out.println(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
