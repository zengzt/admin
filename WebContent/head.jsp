<%@page import="javafx.scene.control.Alert"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="library.*" %>
<script type="text/javascript">
	
</script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%	
		Dao dao1=new Dao();
		String username=(String)session.getAttribute("username");
		%>
		<%= username %>欢迎您。。。
		<%
		if(username==null || "".equals(username)){
			response.sendRedirect("login.jsp");
		}
	%>
	<a href="#">刷新</a>
	<HR style="FILTER: alpha(opacity=100,finishopacity=0,style=3)" width="100%" color=#987cb9 SIZE=3>
	
</body>
</html>