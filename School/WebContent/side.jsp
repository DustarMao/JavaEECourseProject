<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
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
$("#applyCourse").click(function(e) {
    $("#main").load("pages/course/applyCourse.jsp");
});
$("#teachingCourse").click(function(e) {
    $("#main").load("pages/course/teachingCourse.jsp");
});
$("#taughtCourse").click(function(e) {
    $("#main").load("pages/course/taughtCourse.jsp");
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
</body>
</html>