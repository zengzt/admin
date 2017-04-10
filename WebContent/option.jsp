<%@page import="java.sql.ResultSet"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="library.Ma" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	Ma ma=new Ma();
	ResultSet rs=ma.a_privileges(username);
	try {
	if (rs.next()){
		%>
		<table>
			<tr>
				<td><a href="main.jsp">首页</a></td>
			</tr>
			<tr>
				<td>借阅</td>
				<td><a href="borrow.jsp">借</a></td>
				<td><a href="still.jsp">还</a></td>
			</tr>
			<tr>
				<td>查询</td>
				<td><a href="member_query.jsp">会员查询</a></td>
				<td><a href="book_query.jsp">图书查询</a></td>
				<td><a href="book_borrow.jsp">图书借阅记录</a></td>
			</tr>
		<% if(rs.getInt(2)==1){ %>
			<tr>
				<td>图书管理</td>
				<td><a href="book_increase.jsp">增加图书</a></td>
				<td><a href="book_type.jsp">增加图书类型</a></td>
				<td><a href="book_modify.jsp">修改图书</a></td>
				<td><a href="del_books.jsp">删除图书</a></td>
			</tr>
			<% } %> 
			<% if(rs.getInt(3)==1){ %>
			<tr>
				<td>会员管理</td>
				<td><a href="member_increase.jsp">增加会员</a></td>
				<td><a href="member_type.jsp">增加会员类型</a></td>
				<td><a href="member_modify.jsp">修改会员</a></td>
				<td><a href="del_member.jsp">删除会员</a></td>
			</tr>
			<% } %> 
			
			
			<% if(rs.getInt(4)==1){ %>
			<tr>
				<td>管理员</td>
				<td><a href="add_administrator.jsp">增加管理员</a></td>
				<td><a href="modify_administrator.jsp">修改权限</a></td>
				<td><a href="del_administrator.jsp">删除管理员</a></td>
			</tr>
			<% } %> 
			<tr>
				<td><a href="modify_password.jsp">修改密码</a></td>
			</tr>
			<tr>
				<td><a href="logout.jsp">退出登录</a></td>
			</tr>
			
		</table>
		<%
	}
	}catch(Exception e){
	}
	%>
<HR style="FILTER: alpha(opacity=0,finishopacity=100,style=1)" width="100%" color=#987cb9 SIZE=3>
	
</body>
</html>