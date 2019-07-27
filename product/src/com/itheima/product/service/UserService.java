package com.itheima.product.service;

import com.itheima.product.dao.UserDao;
import com.itheima.product.domain.User;
import com.itheima.product.exception.UserException;
import com.itheima.product.utils.SendJMail;

import java.sql.SQLException;

public class UserService {

    UserDao ud = new UserDao();

    //注册用户
    public void regist(User user) throws UserException {
        try {
            ud.addUser(user);

            //发送激活邮件
            String emailMsg = "注册成功，请<a href='http://localhost:8080/product_war_exploded/ActiveServlet?activeCode=" + user.getActiveCode() + "' >激活</a>后登录。";
            SendJMail.sendMail(user.getEmail(), emailMsg);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("注册失败！");
        }
    }

    //根据激活码查找用户
    public void activeUser(String activeCode) throws UserException {
        User user = null;
        try {
            user = ud.findUserByActiveCode(activeCode);
            if (user != null) {
                //激活用户
                ud.activeCode(activeCode);
                return;
            }
            throw new UserException("激活失败！");//当user不存在时抛出异常
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //根据用户名及密码登录
    public User login(String username, String password) throws UserException {
        User user = null;
        try {
            user = ud.findUserByUserNameAndPassword(username, password);
            if (user == null) {
                throw new UserException("登录失败，用户名或密码错误！");
            }

            if (user.getState() == 0) {
                throw new UserException("登录失败，用户未激活，请前往注册邮箱激活后登录。");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    //修改用户信息
    public void modifyUserInfo(String username, String password, String newpassword, String gender, String telephone) throws UserException{
        try {
            ud.modifyUserInfoByUserNameAndPassword(username, password, newpassword, gender, telephone);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UserException("修改失败，请重试。");
        }
    }
}
