package com.itheima.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.itheima.dao.BookDao;
import com.itheima.domain.Book;
import com.itheima.util.C3P0Util;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

public class BookDaoImpl implements BookDao {

	/**
	 * 查找所有图书
	 */
	@Override
	public List<Book> findAllBooks() throws Exception {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from book", new BeanListHandler<Book>(Book.class));

	}

	@Override
	public void addBook(Book book) throws Exception {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("insert into book values(?,?,?,?,?,?,?)", book.getId(), book.getName(), book.getPrice(), book.getAmount(), book.getCategory(), book.getDescription(), book.getImg_url());
	}

	@Override
	public Book findBookById(String id) throws Exception {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from book where id=?", new BeanHandler<Book>(Book.class), id);
	}

	@Override
	public Book updateBookById(Book book) throws Exception {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("update book set name=?, price=?,amount=?,category=?,description=? where id = ?", book.getName(), book.getPrice(), book.getAmount(), book.getCategory(), book.getDescription(), book.getId());
		return qr.query("select * from book where id=?", new BeanHandler<Book>(Book.class), book.getId());
	}

	@Override
	public void deleteBookById(String id) throws Exception {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		qr.update("delete from book where id = ?", id);
	}

	@Override
	public void deleteAllBookById(String[] id) throws Exception {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		Object[][] params = new Object[id.length][];
		for (int i = 0; i < params.length; i++) {
			params[i] = new Object[]{id[i]};
		}
		qr.batch("delete from book where id = ?", params);
		
	}

	@Override
	public List<Book> findProductByManyCondition(String sql) throws Exception {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query(sql, new BeanListHandler<Book>(Book.class));
	}

	@Override
	public int count() throws Exception {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		long l = (Long)qr.query("select count(*) from book", new ScalarHandler(1));
		return (int) l;
	}

	@Override
	public List<Book> findBooks(int currentPage, int pageSize) throws Exception {
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select * from book limit ?,?", new BeanListHandler<Book>(Book.class), (currentPage-1)*pageSize, pageSize);
	}

	@Override
	public List<Object> searchBookByName(String name) throws Exception{
		QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
		return qr.query("select name from book where name like ?", new ColumnListHandler(), "%"+name+"%");
	}

}
