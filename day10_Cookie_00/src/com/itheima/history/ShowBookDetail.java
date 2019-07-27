package com.itheima.history;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itheima.entity.Book;
import com.itheima.util.DBUtil;


@WebServlet("/ShowBookDetail")
public class ShowBookDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public ShowBookDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//显示详细信息
		
		//获得id
		String id = request.getParameter("id");
		
		Book book = DBUtil.findBookById(id);
		out.print(book.toString());
		
		//把当前浏览过的书返回到客户端
		String historyBookId = organizeId(id, request);
		Cookie ck = new Cookie("historyBookId", historyBookId);
		ck.setPath("/");
		ck.setMaxAge(Integer.MAX_VALUE);
		
		response.addCookie(ck);	
		
	}
	private String organizeId(String id, HttpServletRequest request) {
		//得到客户端的Cookie
		Cookie[] cookies = request.getCookies();
		if (cookies == null) {
			return id;
		}
		
		//查找有无名为historyBookId的Cookie
		Cookie historyBookId = null;
		for (int i = 0; i < cookies.length; i++) {
			if ("historyBookId".equals(cookies[i].getName())) {
				historyBookId = cookies[i];
			}
		}
		
		if (historyBookId == null) {
			return id;
		}
		
		String value = historyBookId.getValue();
		String[] values = value.split("-");
		LinkedList<String> list = new LinkedList<String>(Arrays.asList(values));

		if (list.size() < 3) {
			if (list.contains(id)) {
				list.remove(id);
			}
		} else {
			if (list.contains(id)) {
				list.remove(id);
			} else {
				list.removeLast();
			}
			
		}
		
		list.addFirst(id);
		
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < list.size(); i++) {
			if (i > 0) {
				sb.append("-");				
			}
			sb.append(list.get(i));
		}
		
		return sb.toString();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
