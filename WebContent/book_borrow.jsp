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
	<form action="Manager?action=book_borrow" method="post">
		<center>
		根据借书人和图书名字进行查询：<input type="text" value="" name="a">
		<input type="submit" value="查询"><br><br>
			<table>
				<tr>
					<td><center>借阅id</center></td>
					<td><center>借书人</center></td>
					<td><center>图书名字</center></td>
					<td><center>借阅时间</center></td>
					<td><center>还书时间</center></td>
					<td><center>1是还未还</center></td>
				</tr>
				<%	
					String s="";
					String t=request.getParameter("c");
					if (t!=null){
						s=t;
					}
					ResultSet book_borrow=ma.book_borrow(s);
					try {
						while(book_borrow.next()){
							%>
				<tr>
					<td><center><%= book_borrow.getInt(1) %></center></td>
					<td><center><%= book_borrow.getString(2) %></center></td>
					<td><center><%= book_borrow.getString(3) %></center></td>
					<td><center><%= book_borrow.getDate(4) %></center></td>
					<td><center><%= book_borrow.getDate(5) %></center></td>
					<td><center><%= book_borrow.getInt(6) %></center></td>
				</tr>
				<%
				}}catch(Exception e){}
				%>
			</table>
		</center>
	</form>
</body>
</html>