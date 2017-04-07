<%@page import="org.apache.jasper.tagplugins.jstl.core.If"%>
<%@page import="org.eclipse.jdt.internal.compiler.lookup.NullTypeBinding"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="head.jsp" %>
	<%@ include file="option.jsp" %>
	<% response.setContentType("utf-8"); %>
	<% request.setCharacterEncoding("utf-8"); %>
<form action="Manager?action=add_administrator" method="post">
	<center>
		<table>
			<tr>
				<td><center>管理员账号</center></td>
				<td><center>管理员密码</center></td>
				<td><center>图书管理权限</center></td>
				<td><center>会员管理权限</center></td>
				<td><center>管理管理员权限</center></td>
			</tr>
			<tr>
				<td><center><input type="text" name="username"></center></td>
				<td><center><input type="text" name="pswd"></center></td>
				<td><center><input type="checkbox" name="b_istration"></center></td>
				<td><center><input type="checkbox" name="m_istration"></center></td>
				<td><center><input type="checkbox" name="a_istration"></center></td>
			</tr>
			<tr></tr>
			<tr>
				<td><center><input type="submit" value="提交"></center></td>
				<td><center><input type="reset" value="重置"></center></td>
			</tr>
			
		</table>
	</center>
</form>
</body>
</html>