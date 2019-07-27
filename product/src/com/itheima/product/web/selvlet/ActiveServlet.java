package com.itheima.product.web.selvlet;

import com.itheima.product.exception.UserException;
import com.itheima.product.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ActiveServlet", urlPatterns = "/ActiveServlet")
public class ActiveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String activeCode = request.getParameter("activeCode");
        UserService us = new UserService();

        try {
            us.activeUser(activeCode);
        } catch (UserException e) {
            e.printStackTrace();
            //激活失败时向用户提示信息
            response.getWriter().write(e.getMessage());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
