package com.itheima.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ServletDemo4")
public class ServletDemo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int width = 110;
		int height = 25;
		//创建图像对象
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		
		//创建画笔
		Graphics g = img.getGraphics();
		
		//添加背景色
		g.setColor(Color.PINK);
		g.fillRect(1, 1, width-2, height-2);
		
		//添加边框
		g.setColor(Color.RED);
		g.drawRect(0, 0, width-1, height-1);
		 
		//设置文本样式
		g.setColor(Color.BLUE);
		g.setFont(new Font("宋体", Font.BOLD|Font.ITALIC, 15));
		
		//给图片添加文本
		Random rand = new Random();
		int position = 20;
		
		for (int i = 0; i < 4; i++) {
			g.drawString(rand.nextInt(10)+"", position, 20);
			position += 20;
		}
		
		//添加9条干扰线
		for (int i = 0; i < 9; i++) {
			g.drawLine(rand.nextInt(width), rand.nextInt(height),rand.nextInt(width), rand.nextInt(height));
		}
		
		//将图片以流的方式输出
		ImageIO.write(img, "jpg", response.getOutputStream());
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
