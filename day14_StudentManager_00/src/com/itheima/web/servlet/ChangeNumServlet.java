package com.itheima.web.servlet;

import com.itheima.domain.Book;
import com.itheima.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet(name = "ChangeNumServlet", urlPatterns = "/ChangeNumServlet")
public class ChangeNumServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num = request.getParameter("num"); //商品购买数量
        String id = request.getParameter("id"); // 商品id

        Book b = new Book();
        b.setId(id);

        HttpSession session = request.getSession();
        Map<Book, String> cart = (Map<Book, String>) session.getAttribute("cart");

        BookServiceImpl bsi = new BookServiceImpl();
        Book book = new Book();

        //如果购买数量为零，则删除此书
        if ("0".equals(num)) {
            cart.remove(b);
        }


        try {
            book = bsi.findBookById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //判断与id相同的书
        if (cart.containsKey(b)) {
            cart.put(book, num);
        }

        //分发转向
        response.sendRedirect( request.getContextPath()+"/cart.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
