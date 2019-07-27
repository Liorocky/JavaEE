package com.itheima.service;

import java.util.List;

import com.itheima.domain.Book;
import com.itheima.domain.PageBean;

public interface BookService {
	public List<Book> findAllBooks() throws Exception;
	
	public void addBook(Book book) throws Exception;
	
	public Book findBookById(String id) throws Exception;
	
	public Book updateBookById(Book book) throws Exception;
	
	public void deleteBookById(String id) throws Exception;
	
	public void deleteAllBookById(String[] id) throws Exception;
	
	public List<Book> findProductByManyCondition(String sql) throws Exception;

	public PageBean findBooksPage(int currentPage, int pageSize) throws Exception;

    List<Object> searchBookByName(String name) throws Exception;
}
