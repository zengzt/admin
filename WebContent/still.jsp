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
	<table>
			<tr>
				<td><input type="checkbox" value="" name="checkbox" onclick="selectAll(this.checked)"></td>
				<td><center>会员   </center></td>
				<td><center>图书   </center></td>
				<td><center>时间   </center></td>
				<td><center>借阅数量</center></td>
			</tr>
		<form action="Manager?action=still" name="form2" method="post">
		<%
			ResultSet still=ma.still();
			try {
				while(still.next()){
					%>	
			<tr>
				<td><input type="checkbox" value="<%= still.getInt(1) %>" name="checkbox1"></td>
				<td><center><%= still.getString(2) %></center></td>
				<td><center><%= still.getString(3) %></center></td>
				<td><center><%= still.getString(4) %></center></td>
				<td><center>1</center></td>
			</tr>
			<%
				}
			}catch(Exception e){}
		%>
			<tr><td><input type="submit" value="还书" onclick="return still()"></td></tr>
		</form>
	</table>
</center>
</body>
</html>