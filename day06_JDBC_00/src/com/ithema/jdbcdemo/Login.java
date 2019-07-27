package com.ithema.jdbcdemo;

import java.util.Scanner;

import com.ithema.entity.User;

public class Login {
 public static void main(String[] args) {
	Scanner input = new Scanner(System.in);
	
	System.out.println("请输入用户名：");
	String name = input.next();
	System.out.println("请输入密码：");
	String password = input.next();
	int pwd = Integer.parseInt(password);
	DoLogin dl = new DoLogin();
	User u = dl.findUser(name, pwd);
	
	if (u != null) {
		System.out.println("欢迎" + name);
	} else {
		System.out.println("用户名或密码错误！");
	}
}
	
}
