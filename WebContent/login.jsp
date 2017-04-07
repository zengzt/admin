<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script language="javascript">
function check(from){
	if (from.username.value==""){
		alert("请输入管理员名称!");
		from.name.focus();
		return false;
	}
	if (from.pswd.value==""){
		alert("请输入密码!");
		from.pwd.focus();
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
<center>
	<form action="Manager?action=login" method="post" name="from">
		<input type="text" name="username" value=""><br>
		<input type="password" name="pswd" value=""><br>
		<input type="submit" value="登录" onclick="return check(from)">	
		<input type="reset" value="重置">
	</form>
</center>
</body>
</html>