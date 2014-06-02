<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="edu.tongji.sse.j2ee.bean.*"
%>
<%	// This part is for Test
	User test = new User(1000000,false,true,"测试员");
	session.setAttribute("user", null);
%>
<%
	User user = (User)session.getAttribute("user");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>山寨教务管理系统</title>
<link href="index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="jquery-1.11.1.js"></script>
<script>
function sideControl() {
	
}
</script>
</head>

<body>
<div id="head">
	<div id="logo" class="clickable">
    	<span id="title">山寨教务管理系统</span>
    </div>
    
</div>
<div id="corner">
</div>

<div id="side" style="display: none">
</div>

<div id="main">
</div>

<script>
$("#corner").load("login.jsp");
$("#main").load("pages/notice.jsp");
</script>

</body>

</html>