<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/RegServlet" method="post">
		用户名：<input type="text" name="username"/><br/>
		密码：<input type="password" name="password"/><br/>
		确认密码：<input type="password" name="repassword"/><br/>
		邮箱：<input type="text" name="email"/><br/>
		生日：<input type="text" name="birthday"/><br/>
		<input type="submit" value="注册"/><br/>
	
	</form>
</body>
</html>