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
	<form action="Manager?action=book_query" method="post">
		<center>
		根据图书名字进行模糊查询：<input type="text" value="" name="a">
		<input type="submit" value="查询"><br><br>
			<table>
				<tr>
					<td><center>图书id</center></td>
					<td><center>图书名字</center></td>
					<td><center>图书类型</center></td>
					<td><center>是否推荐1推荐</center></td>
					<td><center>可借阅天数</center></td>
					<td><center>现剩余数量</center></td>
					<td><center>总数量</center></td>
				</tr>
				<%	
					String s="";
					String t=request.getParameter("c");
					if (t!=null){
						s=t;
					}
					ResultSet book_query=ma.book_query(s);
					try {
						while(book_query.next()){
							%>
				<tr>
					<td><center><%= book_query.getInt(1) %></center></td>
					<td><center><%= book_query.getString(2) %></center></td>
					<td><center><%= book_query.getString(3) %></center></td>
					<td><center><%= book_query.getInt(4) %></center></td>
					<td><center><%= book_query.getInt(5) %></center></td>
					<td><center><%= book_query.getInt(6) %></center></td>
					<td><center><%= book_query.getInt(7) %></center></td>
				</tr>
				<%
				}}catch(Exception e){}
				%>
			</table>
		</center>
	</form>
</body>
</html>