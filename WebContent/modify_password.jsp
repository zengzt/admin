<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">

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
	<form action="Manager?action=modify_password&username=<%= username %>" method="post">	
		原密码：<input type="password" value="" name="low"><br>
		新密码：<input type="password" value="" name="new"><br>
		确认密码：<input type="password" value="" name="new1"><br>
		<input type="submit" value="提交" >
	</form>
</center>
</body>
</html>