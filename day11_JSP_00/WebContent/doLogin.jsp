<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
//获取表单数据
		String name = (String) request.getParameter("userName");
		String pwd = (String) request.getParameter("pwd");
		
		//处理业务逻辑
		if ("tom".equals(name) && "123".equals(pwd)) {
			request.getRequestDispatcher("/success.jsp").forward(request, response);
			
		} else {
			response.sendRedirect("/day10_JSP_00/login.jsp");
		}
		
		
		//分发转向
	%>

</body>
</html>