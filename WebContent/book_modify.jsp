<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	function selectAll(flag){
// 		选中上面复选框之后全选
		var ids = document.getElementsByName("checkbox1");
		for(var i = 0 ; i < ids.length ; i++){
			ids[i].checked = flag;
		}
	}

	function getSelectedCount(){
		//获取复选框选中的个数
		var ids = document.getElementsByName("checkbox1");
		var count = 0;
		for(var i = 0 ; i < ids.length ; i++)
		{		
			ids[i].checked==true?count++:0;	
		}
		return count;
	}
	
	function still() {
		if (getSelectedCount()<1){
			alert("至少选择一个！！！");
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
	<form action="Manager?action=book_modify" name="form2" method="post">
	<table>
			<tr>
				<td><input type="checkbox" value="" name="checkbox" onclick="selectAll(this.checked)"></td>
				<td><center>图书名字  </center></td>
				<td><center>图书数量   </center></td>
				<td><center>图书类型  </center></td>
				<td><center>推荐与否  </center></td>
			</tr>
		<%
			ResultSet book_modify=ma.get_book();
			try {
				while(book_modify.next()){
		%>	
			<tr>
				<td><input type="checkbox" value="<%= book_modify.getInt(1) %>" name="checkbox1"></td>
				<td><center><%= book_modify.getString(2) %></center></td>
				<td><center><input type="text" name="<%= book_modify.getInt(1) %>" value="<%= book_modify.getInt(3) %>"></center></td>
				<td><select name="<%= book_modify.getInt(1) %>">
					<% 
 						ResultSet bookstype=ma.bookstype();
 						try {
 							while(bookstype.next()){
					%>
 				  	<option value="<%= bookstype.getInt(1) %>"><%= bookstype.getString(2) %></option> 
 				<% }}catch(Exception e){}%> 
 					</select></td>
 				<td><select name="<%= book_modify.getInt(1) %>">
 				  	<option value="1">推荐</option> 
 				  	<option value="0">不推荐</option> 
 				</select></td> 
			</tr>
			<% }}catch(Exception e){} %>
	</table>
	<input type="submit" value="确认" onclick="return still()">
	</form>
</center>
</body>
</html>