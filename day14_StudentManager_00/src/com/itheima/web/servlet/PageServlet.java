package com.itheima.web.servlet;

import com.itheima.domain.Book;
import com.itheima.domain.PageBean;
import com.itheima.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/PageServlet")
public class PageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //初始化每页显示的记录数
        int pageSize = 4;

        //初始化当前页
        int currentPage = 1;

        String currPage = request.getParameter("currentPage");
        if (currPage != null) {
            currentPage = Integer.parseInt(currPage);
        }

        BookServiceImpl bsi = new BookServiceImpl();
        try {
            PageBean pb = bsi.findBooksPage(currentPage, pageSize);

            request.setAttribute("pb", pb);
            request.getRequestDispatcher("/product_list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
