<%@page import="oracle.net.aso.l"%>
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
<center>
	<table>
		<tr>
			<td><a>图书id</a> | </td>
			<td><a>数量</a> | </td>
			<td><a>类型 </a>| </td>
			<td><a>可借阅的天数</a></td>
		</tr>
		<%
		ResultSet display=ma.display();
		while (display.next()){
			%>
			<tr>
			<td><center><%= display.getInt(1) %></center></td>
			<td><center><%= display.getInt(2) %></center></td>
			<td><center><%= display.getString(3) %></center></td>
			<td><center><%= display.getString(4) %></center></td>
		</tr>	
		<% } dao1.close(); %>	
	</table>
</center>
</body>
</html>