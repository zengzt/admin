<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
失败，3秒之后跳转到首页，或者是点击<a href="main.jsp">这里</a>跳转
	<% response.setHeader("refresh", "3;URL=main.jsp"); %>
</body>
</html>