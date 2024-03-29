package com.itheima.dao;

import java.util.List;

import com.itheima.domain.Book;

public interface BookDao {
	
	/**
	 * 查询所有图书
	 * @return
	 * @throws Exception 
	 */
	public List<Book> findAllBooks() throws Exception;
	
	/**
	 * 添加图书
	 * @param book
	 * @throws Exception
	 */
	public void addBook(Book book) throws Exception;
	
	/**
	 * 根据id查找图书
	 * @param id
	 * @return
	 */
	public Book findBookById(String id) throws Exception;
	
	/**
	 * 根据id更新图书
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Book updateBookById(Book book) throws Exception;
	
	/**
	 * 根据id删除图书
	 * @param id
	 * @throws Exception
	 */
	public void deleteBookById(String id) throws Exception;
	
	/*
	 * 根据多个id批量删除图书
	 */
	public void deleteAllBookById(String[] id) throws Exception;
	
	/**
	 * 根据条件返回图书
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public List<Book> findProductByManyCondition(String sql) throws Exception;

	/**
	 * 返回所有图书
	 * @return
	 * @throws Exception
	 */
    public int count() throws Exception;

	/**
	 * 根据页码返回图书
	 * @param currentPage
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<Book> findBooks(int currentPage, int pageSize) throws Exception;

    List<Object> searchBookByName(String name) throws Exception;
}
