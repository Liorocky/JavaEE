package com.itheima.product.web.selvlet;

import com.itheima.product.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyAccountServlet", urlPatterns = "/MyAccountServlet")
public class MyAccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {

            //管理员账户
            if (user.getRole().equals("admin")) {
                request.getRequestDispatcher("/admin/login/home.jsp").forward(request, response);
                return;
            }

            //普通账户
            if (user.getRole().equals("普通用户")) {
                request.getRequestDispatcher("/myAccount.jsp").forward(request, response);
                return;
            }
        }

        //用户未登录
        request.setAttribute("user_msg", "用户未登录");
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
