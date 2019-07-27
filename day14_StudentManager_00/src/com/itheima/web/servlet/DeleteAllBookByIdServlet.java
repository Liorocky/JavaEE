package com.itheima.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.service.impl.BookServiceImpl;

/**
 * Servlet implementation class DeleteAllBookByIdServlet
 */
@WebServlet("/DeleteAllBookByIdServlet")
public class DeleteAllBookByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAllBookByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//获取数据
		String[] id = request.getParameterValues("ids");
		//调用业务逻辑
		BookServiceImpl bsi = new BookServiceImpl();
		try {
			bsi.deleteAllBookById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//分发转向
		request.getRequestDispatcher("BookListServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
