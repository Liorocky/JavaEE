package com.itheima.web.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.itheima.domain.Book;
import com.itheima.service.impl.BookServiceImpl;
import com.itheima.util.UUIDUtil;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

/**
 * Servlet implementation class AddBookServlet
 */
@WebServlet("/AddBookServlet")
public class AddBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		//创建一个ServletFileUpload对象 解析表单
		ServletFileUpload sfu = new ServletFileUpload(factory);
		sfu.setHeaderEncoding("UTF-8");//解决上传文件乱码
		//解析request对象，返回所有表单项
		List<FileItem> fileItems = new ArrayList<FileItem>(0);

		try {
			fileItems = sfu.parseRequest(request);
			Map<String, String[]> map = new HashMap<String, String[]>();

			//迭代fileItems表单项
			for (FileItem fileItem : fileItems) {
				if (fileItem.isFormField()) {
					//普通表单
					String name = fileItem.getFieldName();//得到字段名
					String value = fileItem.getString("UTF-8"); //得到字段值并转码
					map.put(name, new String[]{value}); //向map中赋值

				} else {
					//文件表单
					InputStream inputStream = fileItem.getInputStream();
					String fileName = fileItem.getName();
					String extension = FilenameUtils.getExtension(fileName);

					if (!("jsp".equals(extension) || "exe".equals(extension))) {
						File storeDirectory = new File(this.getServletContext().getRealPath("/upload"));
						if (!storeDirectory.exists()) {
							storeDirectory.mkdirs(); //如果目录不存在，就创建
						}

						//消除文件名前面的路径
						if (fileName != null) {
							fileName = FilenameUtils.getName(fileName);
						}

						//文件上传
						fileItem.write(new File(storeDirectory, fileName));
						fileItem.delete(); // 清除缓存
					}

					map.put(fileItem.getFieldName(), new String[]{fileName});

				}
			}

			Book book = new Book();
			BeanUtils.populate(book, map);
			book.setId(UUIDUtil.getUUID());//设置图书编号

			//处理业务逻辑
			BookServiceImpl bsi = new BookServiceImpl();
			try {
				bsi.addBook(book);
			} catch (Exception e) {
				e.printStackTrace();
			}

			//分发转向
			request.getRequestDispatcher("BookListServlet").forward(request, response);
		} catch (Exception e) {
		e.printStackTrace();
	}

//		request.setCharacterEncoding("UTF-8");
//		response.setCharacterEncoding("UTF-8");
//		//获取表单数据
//		Book book = new Book();
//		try {
//			BeanUtils.populate(book, request.getParameterMap());
//			book.setId(UUIDUtil.getUUID());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		//处理业务逻辑
//		BookServiceImpl bsi = new BookServiceImpl();
//		try {
//			bsi.addBook(book);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//
//		//分发转向
//		request.getRequestDispatcher("BookListServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
