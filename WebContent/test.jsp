<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

function check() {
	var id=document.getElementById("select").value;
	if(id=='1'){
	     
		
		document.form.action="user/user_login";
	}
	else if(id=='2'){
		
		
		document.form.action="admin/login";
	}
	
	
	
}

</script>
</head>
<body onload="xianshi()">
<form action="" name="form" method="post" onsubmit="return check()">
	账号<input type="text" name="username" id="username1">
	密码<input type="password" name="password" id="password1"/>
	<select id="select">
			<option value="1">普通用户</option>
			<option value="2">管理员</option>
	</select>
	<input type="submit" value="提交"/>
</form>
</body>
</html>