<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="library.Ma" %>
<script type="text/javascript">
	function borrow(from){
		if (from.member.value=="") {
			alert ("请选项一个会员");
			return false;
		}
		else if (from.books.value=="") {
			alert ("请选择一本书");
			return false;
		}
	}
</script>
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
			<td>可借会员  | </td>
			<td>可借图书  | </td>
			<td>借/还  | </td>
			<td>借阅数量</td>
		</tr>
	<form name="from" action="Manager?action=borrow" method="post">
		<tr>
			<td>
			<select name="member">
			<option value=""></option>
			<%
		ResultSet member=ma.member();
		try {
			while(member.next()){
				%>
				  	<option value="<%= member.getInt(1) %>"><%= member.getString(2) %></option>
				<%
				}}catch(Exception e){}
				%>
				</select>
			</td>
			<td>
			<select name="books">
			<option value=""></option>
			<%
		ResultSet books=ma.books();
		try {
			while(books.next()){
				%>
				  	<option value="<%= books.getInt(1) %>"><%= books.getString(2) %></option>
				<%
				}}catch(Exception e){}finally{dao1.close();}
				%>
				</select></td>
			<td><center><a href="still.jsp">->还</a></center> </td>
			<td><center>1</center></td>
		</tr>
		<tr>
			<td><input type="submit" value="提交" onclick="return borrow(from)"></td>
		</tr>
	</form>
	</table>
</center>
	
</body>
</html>