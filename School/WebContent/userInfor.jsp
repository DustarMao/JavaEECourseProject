<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="edu.tongji.sse.j2ee.bean.*"
%>
<%
	User user = (User)session.getAttribute("user");
	if (user==null)
	user=new User();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Information</title>
</head>
<body>
	<div id="using">
    	<div id="infor">
    		<span id="uID"><%=user.getuID() %></span>
    		<span id="uName"><%=user.getName() %></span>
    		<span id="uType"><%=user.isAdmin()?"管理员":(user.isStude()?"学生":"教师") %></span>
    	</div>
    	<div id="btnLogout" class="clickable btn">
    		<img src="img/sign_logout.png" width="20" height="20" title="注销" />
    	</div>
        <script>
			$("#btnLogout").click(function(e) {
                $("#corner").load("LogoutServlet");
				$("#side").fadeOut();
				$("#main").load("pages/notice.jsp");
            });
		</script>
    </div>
    <script>
	$(".clickable").mouseover(function(e) {
		$(this).css("background-color","#0066CC");
	});
	$(".clickable").mouseout(function(e) {
		$(this).css("background-color","#0066FF");
	});
	$(".clickable").mousedown(function(e) {
		$(this).css("background-color","#00337F");
	});
	$(".clickable").mouseup(function(e) {
		$(this).css("background-color","#0066CC");
	});
	</script>
</body>
</html>