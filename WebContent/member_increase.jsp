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
<form action="Manager?action=member_increase" method="post">
	<center>
		<table>
			<tr>
				<td><center>会员名字</center></td>
				<td><center>会员密码</center></td>
				<td><center>手机号码</center></td>
				<td><center>会员类型</center></td>
			</tr>
			<tr>
				<td><center><input type="text" name="name"></center></td>
				<td><center><input type="text" name="password"></center></td>
				<td><center><input type="text" name="phone"></center></td>
				<td><center>
					<select name="membertype">
						<%
				ResultSet bookstype=ma.membertype();
				try {
					while(bookstype.next()){
				%>
				  	<option value="<%= bookstype.getInt(1) %>"><%= bookstype.getString(2) %></option>
				<%
				}}catch(Exception e){}
				%>
					</select>
				</center></td>
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