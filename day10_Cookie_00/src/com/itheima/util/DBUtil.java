package com.itheima.util;

import java.util.HashMap;
import java.util.Map;

import com.itheima.entity.Book;

public class DBUtil {
	private static Map<String, Book> books = new HashMap<String, Book>();
	
	static {
		books.put("1", new Book("1", "活着", 15, "余华"));
		books.put("2", new Book("2", "放学后", 30, "东野圭吾"));
		books.put("3", new Book("3", "状元媒", 36, "叶广岑"));
		books.put("4", new Book("4", "三体", 15, "刘慈欣"));
	}
	
	public static Map<String, Book> findAllBooks() {
		return books;
	}
	
	public static Book findBookById(String id) {
		return books.get(id);
	}
	
	
}
