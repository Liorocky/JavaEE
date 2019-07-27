package com.itheima.web.servlet;

import com.itheima.domain.Book;
import com.itheima.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "FindBookInfoServlet", urlPatterns = "/FindBookInfoServlet")
public class FindBookInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");

        BookServiceImpl bsi = new BookServiceImpl();
        try {
            Book book = bsi.findBookById(id);
            request.setAttribute("book", book);
            request.getRequestDispatcher("/product_info.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
