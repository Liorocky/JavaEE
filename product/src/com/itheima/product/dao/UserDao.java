package com.itheima.product.dao;

import com.itheima.product.domain.User;
import com.itheima.product.utils.C3P0Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDao {
    /**
     * 添加用户
     * @param user
     * @throws SQLException
     */
    public void addUser(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        String sql = "INSERT INTO user(username, password, gender, email, telephone, introduce, activeCode, state, registTime) " +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        qr.update(sql, user.getUsername(), user.getPassword(), user.getGender(), user.getEmail(),
                user.getTelephone(), user.getIntroduce(), user.getActiveCode(), user.getState(), user.getRegistTime());

    }

    /**
     * 根据激活码查找用户
     * @param activeCode
     * @return
     * @throws SQLException
     */
    public User findUserByActiveCode(String activeCode) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from user where activeCode=?", new BeanHandler<>(User.class), activeCode);
    }

    /**
     * 修改用户状态
     * @param activeCode
     * @throws SQLException
     */
    public void activeCode(String activeCode) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("update user set state=1 where activeCode=?", activeCode);
    }

    /**
     * 根据用户名和密码查找用户
     * @param username
     * @param password
     */
    public User findUserByUserNameAndPassword(String username, String password) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        return qr.query("select * from user where username=? and password=?", new BeanHandler<>(User.class), username, password);
    }

    /**
     * 修改密码
     * @param username
     * @param password
     * @param newpassword
     */
    public void modifyUserInfoByUserNameAndPassword(String username, String password, String newpassword, String gender, String telephone) throws SQLException {
        QueryRunner qr = new QueryRunner(C3P0Util.getDataSource());
        qr.update("update user set password=?,gender=?,telephone=? where username=? and password=?", newpassword, gender, telephone, username, password);
    }
}
