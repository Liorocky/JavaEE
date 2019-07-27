package com.itheima.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.entity.Book;
import com.itheima.util.DBUtil;
@WebServlet("/ShowAllBook")
public class ShowAllBook extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    public ShowAllBook() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.print("本站有以下好书：<br/>");
		Map<String, Book> books = DBUtil.findAllBooks();
		for (Map.Entry<String, Book> book : books.entrySet()) {
			out.print("<a href='"+request.getContextPath()+"/AddCart?id="+book.getKey()+"' target='_blank'>"+book.getValue().getName()+"</a><br/>");
		}
		
		
		out.print("<a href='"+request.getContextPath()+"/ShowCart'>查看购物车</a>");
	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
