<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="edu.tongji.sse.j2ee.bean.*"
%>
<%	// This part is for Test
	User test = new User(0,true,true,"测试员");
	session.setAttribute("user", test);
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
        	<a>教务通知</a>
        </li>
        <li id="person">
        	<a>个人信息</a>
        </li>
        <li id="course">
        	<a>课程管理</a>
            <ul class="student" style="display: none;">
            	<li id="newCourse"><a>选课</a></li>
                <li id="studingCourse"><a>课程表</a></li>
                <li id="studiedCourse"><a>已修课</a></li>
            </ul>
            <ul class="teacher" style="display: none;">
            	<li id="applyCourse"><a>开课</a></li>
                <li id="teachingCourse"><a>授课表</a></li>
                <li id="taughtCourse"><a>已授课</a></li>
            </ul>
        </li>
        <li id="system">
        	<a>系统管理</a>
            <ul class="normal" style="display: none;">
            	<li id="passwordMana"><a>密码管理</a></li>
            </ul>
            <ul class="admin" style="display: none;">
            	<li id="userMana"><a>用户管理</a></li>
            	<li id="courseMana"><a>课程管理</a></li>
                <li id="noticeMana"><a>通知管理</a></li>
                <li id="passwordMana"><a>密码管理</a></li>
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
	$("#menu a").mouseout(function() {
		$(this).css("background-color","#EEE");
	});
	
	$("#menu a").mouseover(function() {
		$(this).css("background-color","#CCC");
	});
	
	$("#menu a").mousedown(function(e) {
		$(this).css("background-color","#AAA");
	});
	
	$("#menu a").mouseup(function(e) {
		$(this).css("background-color","#CCC");
	});
	$("#menu li a").click(function(e) {
        $(this).parent().children("ul").slideToggle();
    });
	</script>
</div>
<script>
$("#notice").click(function(e) {
    $("#main").load("pages/notice.jsp");
});
$("#person").click(function(e) {
    $("#main").load("pages/person.jsp");
});
$("#newCourse").click(function(e) {
    $("#main").load("pages/course/choiceCourse.jsp");
});
$("#studingCourse").click(function(e) {
    $("#main").load("pages/course/studingCourse.jsp");
});
$("#studiedCourse").click(function(e) {
    $("#main").load("pages/course/studiedCourse.jsp");
});
$("#userMana").click(function(e) {
    $("#main").load("pages/system/userMana.jsp");
});
$("#courseMana").click(function(e) {
    $("#main").load("pages/system/courseMana.jsp");
});
$("#noticeMana").click(function(e) {
    $("#main").load("pages/system/noticeMana.jsp");
});
$("#passwordMana").click(function(e) {
    $("#main").load("pages/system/password.jsp");
});
</script>
<div id="main">
</div>

</body>

</html>