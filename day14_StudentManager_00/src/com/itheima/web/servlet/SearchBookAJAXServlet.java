package com.itheima.web.servlet;

import com.itheima.service.impl.BookServiceImpl;
import net.sf.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchBookAJAXServlet", urlPatterns = "/SearchBookAJAXServlet")
public class SearchBookAJAXServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("name");

        BookServiceImpl bsi = new BookServiceImpl();
        try {
            List<Object> list = bsi.searchBookByName(name);

//            //把集合中的数据转换成字符串返回到网页
//            String str = "";
//            for (int i = 0; i < list.size(); i++) {
//                if (i > 0) {
//                    str += ",";
//                }
//                str += list.get(i);
//            }
            String str = JSONArray.fromObject(list).toString();

            //把数据响应到客户端
            response.getWriter().write(str);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
