<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="edu.tongji.sse.j2ee.bean.*"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	// This part is for Test
	User test = new User(0,true,true,"测试员");
	session.setAttribute("user", test);
%>
<%
	User user = (User)session.getAttribute("user");
%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>山寨教务管理系统</title>
<link href="index.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="jquery-1.11.1.js"></script>
</head>

<body>
<div id="head">
	<div id="logo">
    	<span id="title">山寨教务管理系统</span>
    </div>
    <div id="user">
    	<span id="uID"><%=user.getuID() %></span>
    	<span id="uName"><%=user.getName() %></span>
    	<span id="uType"><%=user.isAdmin()?"管理员":(user.isStude()?"学生":"教师") %></span>
    </div>
    <div id="logout">
    	<img src="img/1401527178_common_logout_signout_exit_.png" width="20" height="20" title="注销" />
    </div>
    <script>
		$("#head div").mouseover(function(e) {
            $(this).css("background-color","#0066CC");
        });
		$("#head div").mouseout(function(e) {
            $(this).css("background-color","#0066FF");
        });
		$("#head div").mousedown(function(e) {
            $(this).css("background-color","#00337F");
        });
		$("#head div").mouseup(function(e) {
            $(this).css("background-color","#0066CC");
        });
	</script>
</div>
<div id="side">
	<ul id="menu">
    	<li id="notice">
        	<span>教务通知</span>
        </li>
        <li id="person">
        	<span>个人信息</span>
        </li>
        <li id="course">
        	<span>课程管理</span>
            <ul class="student" style="display: none;">
            	<li id="newCourse"><span>选课</span></li>
                <li id="studingCourse"><span>课程表</span></li>
                <li id="studiedCourse"><span>已修课</span></li>
            </ul>
            <ul class="teacher" style="display: none;">
            	<li id="applyCourse"><span>开课</span></li>
                <li id="teachingCourse"><span>授课表</span></li>
                <li id="taughtCourse"><span>已授课</span></li>
            </ul>
        </li>
        <li id="system">
        	<span>系统管理</span>
            <ul class="normal" style="display: none;">
            	<li id="passwordMana"><span>密码管理</span></li>
            </ul>
            <ul class="admin" style="display: none;">
            	<li id="courseMana"><span>课程管理</span></li>
                <li id="noticeMana"><span>通知管理</span></li>
                <li id="passwordMana"><span>密码管理</span></li>
            </ul>
        </li>
    </ul>
    <script>
	if (<%=((User)session.getAttribute("user")).isStude()%>)
		$(".teacher").remove();
	else
		$(".student").remove();
	if (<%=((User)session.getAttribute("user")).isAdmin()%>)
		$(".normal").remove();
	else
		$(".admin").remove();
	</script>
	<script>
	$("#menu span").mouseout(function() {
		$(this).css("background-color","#EEE");
	});
	
	$("#menu span").mouseover(function() {
		$(this).css("background-color","#CCC");
	});
	
	$("#menu span").mousedown(function(e) {
		$(this).css("background-color","#AAA");
	});
	
	$("#menu span").mouseup(function(e) {
		$(this).css("background-color","#CCC");
	});
	$("#menu li span").click(function(e) {
        $(this).parent().children("ul").slideToggle();
    });
	</script>
</div>
<div id="main">
</div>

</body>

</html>