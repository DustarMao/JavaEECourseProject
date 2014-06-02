<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="edu.tongji.sse.j2ee.bean.*"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>授课表</title>
<style type="text/css">
* {
	font-family: "Microsoft Yahei UI","Microsoft Yahei",Verdana,Simsun,"Segoe UI","Segoe UI Web Regular","Segoe UI Symbol","Helvetica Neue","BBAlpha Sans","S60 Sans",Arial,sans-serif;
	list-style-type: none;
}
#main a {
	color: #0000FF;
	text-decoration: underline;
}
h2 {
	margin: 0;
	font-size: xx-large;
}
div#courses {
	width: 300px;
	margin-bottom: 20px;
	margin-left: 20px;
	margin-right: 0px;
	margin-top: 20px;
}
div.course {
	margin: 20px 0;
}
div.view {
	margin: 5px 30px;
	overflow-y: auto;
	overflow-x: auto;
}
</style>
</head>
<body id="main">
<script>
$.getJSON("TestData/teachingCourse.json",null,
	function callback(json) {
		$(json.teachingCourse).each(function(index, element) {
			cDiv=$("#courses").append("<div/>").children().last().attr("id",this.cID).addClass("course");
			cDiv.append("<span/>").children().last().addClass("courseId").text(this.cID);
			cDiv.append("<h2/>").children().last().addClass("courseName").text(this.name);
			cDiv.append("<span/>").children().last().addClass("courseTime").text(this.time);
			cDiv.append("<br/>");
			cDiv.append("<span/>").children().last().addClass("courseLocal").text(this.place);
			cDiv.append("<br/>");
			cDiv.append("<a/>").children().last().addClass("courseListen").text("Students");
        });
	});
</script>
<div id="courses">
</div>
</body>
</html>