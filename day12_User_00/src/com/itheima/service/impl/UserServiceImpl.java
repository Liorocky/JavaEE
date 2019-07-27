package com.itheima.service.impl;

import com.itheima.dao.UserDao;
import com.itheima.dao.impl.UserDaoImpl;
import com.itheima.domain.User;
import com.itheima.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao userDao = new UserDaoImpl();
	@Override
	public void register(User user) throws Exception {
		userDao.addUser(user);		
		
	}
	@Override
	public User login(User user) {
		User u = null;
		try {
			u = userDao.findUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

}
