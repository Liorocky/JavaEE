package com.itheima.service.impl;

import java.util.List;

import com.itheima.dao.impl.BookDaoImpl;
import com.itheima.domain.Book;
import com.itheima.domain.PageBean;
import com.itheima.service.BookService;

public class BookServiceImpl implements BookService {
	BookDaoImpl bookDao = new BookDaoImpl();

	@Override
	public List<Book> findAllBooks() throws Exception {
		return bookDao.findAllBooks();

	}

	@Override
	public void addBook(Book book) throws Exception {
		bookDao.addBook(book);
	}

	@Override
	public Book findBookById(String id) throws Exception {
		return bookDao.findBookById(id);
	}

	@Override
	public Book updateBookById(Book book) throws Exception {
		return bookDao.updateBookById(book);
	}

	@Override
	public void deleteBookById(String id) throws Exception {
		bookDao.deleteBookById(id);
	}

	@Override
	public void deleteAllBookById(String[] id) throws Exception {
		bookDao.deleteAllBookById(id);
	}

	@Override
	public List<Book> findProductByManyCondition(String sql) throws Exception {
		return bookDao.findProductByManyCondition(sql);
	}

	@Override
	public PageBean findBooksPage(int currentPage, int pageSize) throws Exception {
		int count = bookDao.count();
		int totalPage = (int) Math.ceil(count * 1.0 / pageSize);
		List<Book> books = bookDao.findBooks(currentPage, pageSize);

		PageBean pb = new PageBean();
		pb.setBooks(books);
		pb.setCount(count);
		pb.setCurrentPage(currentPage);
		pb.setTotalPage(totalPage);
		return pb;
	}

	@Override
	public List<Object> searchBookByName(String name) throws Exception{
		return bookDao.searchBookByName(name);
	}
}
