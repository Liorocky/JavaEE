package com.itheima.product.web.selvlet;

import com.itheima.product.domain.User;
import com.itheima.product.exception.UserException;
import com.itheima.product.service.UserService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理验证码
        String ckCode = request.getParameter("ckCode");
        String checkcode_session = (String) request.getSession().getAttribute("checkcode_session");
        if (!checkcode_session.equals(ckCode)) {
            //如果两个验证码不同，跳回注册页面
            request.setAttribute("ckcode_msg", "验证码错误！");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }

        //获取表单数据
        User user = new User();
        try {
            BeanUtils.populate(user, request.getParameterMap());
            user.setActiveCode(UUID.randomUUID().toString()); //手动设置激活码
            //调用业务逻辑
            UserService us = new UserService();
            us.regist(user);

            //分发转向
//            request.getSession().setAttribute("user", user);

            request.getRequestDispatcher("/registersuccess.jsp").forward(request, response);

        }catch (UserException e) {
            request.setAttribute("user_msg", e.getMessage());
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            return;
        }catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
