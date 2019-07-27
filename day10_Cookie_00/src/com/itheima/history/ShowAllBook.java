package com.itheima.history;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.entity.Book;
import com.itheima.util.DBUtil;

@WebServlet("/ShowAllBook")
public class ShowAllBook extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShowAllBook() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		out.write("本站有以下好书：<br/>");
		Map<String, Book> books = DBUtil.findAllBooks();
		for (Map.Entry<String, Book> book : books.entrySet()) {
			out.write("<a href='" + request.getContextPath() + "/ShowBookDetail?id=" + book.getKey() 
			+ "' target='_blank' > " + book.getValue().getName() + "</a>");
		}

		out.write("<hr/>您最近浏览过的书：<br/>");
		Cookie[] cookies = request.getCookies();
		for (int i = 0; cookies!=null && i < cookies.length; i++) {
			if ("historyBookId".equals(cookies[i].getName())) {
				String value = cookies[i].getValue();
				String[] values = value.split("-");
				for (int j = 0; j < values.length; j++) {
					Book book = DBUtil.findBookById(values[j]);
					out.print(book.getName()+"<br/>");
				}
			}
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
