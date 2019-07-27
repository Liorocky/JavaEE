package com.itheima.entity;

public class Book {
	private String id;
	private String name;
	private double price;
	private String auther;
	public String getName() {
		return name;
	}
	public Book(String id, String name, double price, String auther) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.auther = auther;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAuther() {
		return auther;
	}
	public void setAuther(String auther) {
		this.auther = auther;
	}
	
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", auther=" + auther + "]";
	}
}
