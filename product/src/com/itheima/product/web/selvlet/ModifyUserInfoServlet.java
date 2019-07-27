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

@WebServlet(name = "ModifyUserInfoServlet", urlPatterns = "/ModifyUserInfoServlet")
public class ModifyUserInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String repassword = request.getParameter("repassword");
        String gender = request.getParameter("gender");
        String telephone = request.getParameter("telephone");
        UserService us = new UserService();

        User user = (User) request.getSession().getAttribute("user");

        if (!newpassword.equals(repassword)) {
            request.setAttribute("user_msg", "重复密码不同！");
            request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request, response);
            return;
        }

        if (user.getPassword().equals(oldpassword)) {
            try {
                //修改成功
                us.modifyUserInfo(user.getUsername(), user.getPassword(), newpassword, gender, telephone);
                request.getSession().removeAttribute("user");
                request.setAttribute("user_msg", "修改成功！请重新登录。");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } catch (UserException e) {
                //修改失败
                e.printStackTrace();
                request.setAttribute("user_msg", e.getMessage());
                request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request, response);
            }
        }

        request.setAttribute("user_msg", "修改失败，原密码不正确");
        request.getRequestDispatcher("/modifyuserinfo.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
