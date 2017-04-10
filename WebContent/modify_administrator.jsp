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
	function still(){
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
	<form action="Manager?action=modify_administrator" name="form2" method="post">
	<table>
			<tr>
				<td><input type="checkbox" value="" name="checkbox" onclick="selectAll(this.checked)"></td>
				<td><center>管理员账号</center></td>
				<td><center>管理员密码</center></td>
				<td><center>图书管理权限</center></td>
				<td><center>会员管理权限</center></td>
				<td><center>管理管理员权限</center></td>
			</tr>

	
		<%
			ResultSet get_administrator=ma.get_administrator();
			try {
				while(get_administrator.next()){
		%>	
			<tr>
				<td><% if (get_administrator.getInt(1)!=1){ %><input type="checkbox"  value="<%= get_administrator.getInt(1) %>" name="checkbox1"><% } %></td>
				<td><center><%= get_administrator.getString(2) %></center></td>
				<td><center><input type="text" name="<%= get_administrator.getInt(1) %>"  value="<%= get_administrator.getString(3) %>"></center></td>
				<td><input type="checkbox"  name="<%= String.valueOf(get_administrator.getInt(1)) %>(1)" <% if (get_administrator.getInt(4)==1){ %> checked="checked" <% } %>></td>
				<td><input type="checkbox"  name="<%= String.valueOf(get_administrator.getInt(1)) %>(2)" <% if (get_administrator.getInt(5)==1){ %> checked="checked" <% } %>></td>
				<td><input type="checkbox"  name="<%= String.valueOf(get_administrator.getInt(1)) %>(3)" <% if (get_administrator.getInt(6)==1){ %> checked="checked" <% } %>></td>
			</tr>
			<% }}catch(Exception e){}finally{dao1.close();} %>
	</table>
	<input type="submit" value="确认" onclick="return still()">
	</form>
</center>
</body>
</html>