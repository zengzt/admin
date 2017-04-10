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
<form action="Manager?action=book_increase" method="post">
	<center>
		<table>
			<tr>
				<td><center>图书名字</center></td>
				<td><center>图书数量</center></td>
				<td><center>图书类型</center></td>
				<td><center>推荐与否</center></td>
			</tr>
			<tr>
				<td><center><input type="text" name="bookname"></center></td>
				<td><center><input type="text" name="number"></center></td>
				<td><center>
					<select name="bookstype">
						<%
				ResultSet bookstype=ma.bookstype();
				try {
					while(bookstype.next()){
				%>
				  	<option value="<%= bookstype.getInt(1) %>"><%= bookstype.getString(2) %></option>
				<%
				}}catch(Exception e){}finally{dao1.close();}
				%>
					</select>
				</center></td>
				<td><center><select name="recommend">
				  	<option value="1">推荐</option>
				  	<option value="0">不推荐</option>
				</select></center></td>
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