package com.itheima.product.web.selvlet;

import com.itheima.product.domain.PageBean;
import com.itheima.product.service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "PageServlet", urlPatterns = "/PageServlet")
public class PageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String category = request.getParameter("category");

        //初始化每页显示的记录数
        int pageSize = 4;

        //初始化当前页
        int currentPage = 1;

        String currPage = request.getParameter("currentPage");
        if (currPage != null) {
            currentPage = Integer.parseInt(currPage);
        }

        ProductService ps = new ProductService();
        try {
            PageBean pb = ps.findBooksPage(currentPage, pageSize, currPage);

            request.setAttribute("pb", pb);
            request.getRequestDispatcher("/product_list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
