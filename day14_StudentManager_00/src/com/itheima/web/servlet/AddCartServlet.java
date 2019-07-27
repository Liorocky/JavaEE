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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AddCartServlet", urlPatterns = "/AddCartServlet")
public class AddCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Book b = new Book();

        BookServiceImpl bsi = new BookServiceImpl();

        try {
            b = bsi.findBookById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //从session把购物车取出来
        HttpSession session = request.getSession();
        Map<Book, String> cart = (Map<Book, String>) session.getAttribute("cart");
        int num = 1;

        //如果购物车为空，则创建
        if (cart == null) {
            cart = new HashMap<Book, String>();
        }
        //查看当前集合中是否存在b这本书，如果存在，取出数量并+1
        if (cart.containsKey(b)) {
            num = Integer.parseInt(cart.get(b)) + 1;
        }
        //把图书及数量放入购物车
        cart.put(b, num + "");

        //将cart放回session
        session.setAttribute("cart", cart);

        //分发转向
        response.sendRedirect( request.getContextPath()+"/cart.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
