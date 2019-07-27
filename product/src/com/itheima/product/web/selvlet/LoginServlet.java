package com.itheima.product.web.selvlet;

import com.itheima.product.domain.User;
import com.itheima.product.exception.UserException;
import com.itheima.product.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService us = new UserService();
        User user = null;
        try {
            user = us.login(username, password);
            if (user != null) {
                request.getSession().setAttribute("user", user);

                if (user.getRole().equals("admin")) {
                    request.getRequestDispatcher("/admin/login/home.jsp").forward(request, response);
                    return;
                }

                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (UserException e) {
            e.printStackTrace();
            request.setAttribute("user_msg", e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
