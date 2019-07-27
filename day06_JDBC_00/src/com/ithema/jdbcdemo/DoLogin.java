package com.ithema.jdbcdemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.ithema.entity.User;
import com.ithema.util.DBUtils;

public class DoLogin {
	public User findUser(String name, int password) {
		Connection conn = null;
		PreparedStatement stmt = null;
		
		ResultSet rs = null;
		User u = null;
		
		try {
			conn = DBUtils.getConnection();
			String sql = "select * from orders where customer=? and orderprice=?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setLong(2, password);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				u = new User();
				u.setO_id(rs.getInt("O_Id"));
				u.setOrderDate(rs.getDate("OrderDate"));
				u.setOrderPrice(rs.getInt("OrderPrice"));
				u.setCustomer(rs.getString("Customer"));
				return u;
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeAll(rs, stmt, conn);
		}
		
		return u;
	}
}
