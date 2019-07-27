package com.ithema.entity;

import java.util.Date;

public class User {
	private int O_id;
	private Date OrderDate;
	private int OrderPrice;
	private String Customer;
	public int getO_id() {
		return O_id;
	}
	public void setO_id(int o_id) {
		O_id = o_id;
	}
	public Date getOrderDate() {
		return OrderDate;
	}
	public void setOrderDate(Date orderDate) {
		OrderDate = orderDate;
	}
	public int getOrderPrice() {
		return OrderPrice;
	}
	public void setOrderPrice(int orderPrice) {
		OrderPrice = orderPrice;
	}
	public String getCustomer() {
		return Customer;
	}
	public void setCustomer(String customer) {
		Customer = customer;
	}
	
	@Override
	public String toString() {
		return "User [O_id=" + O_id + ", OrderDate=" + OrderDate + ", OrderPrice=" + OrderPrice + ", Customer=" + Customer + "]";
	}
	
	
}
