<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	import="edu.tongji.sse.j2ee.bean.*"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已授课</title>
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
	min-height: 100%;
	float: left;
	background-color: #CEF;
}
div.term{
	padding: 10px 0 10px 10px;
	border-top: thick solid #6699FF;
	border-left: thick solid #CCEEFF;
	margin-top: 10px;
}
div.course {
	padding: 10px 0 10px 20px;
	border-left: thick solid #CCEEFF;
}
div#viewStudents {
	position: fixed;
	top: 40px;
	left: 480px;
	right: 0;
	bottom: 0;
	overflow-y: auto;
}
div#viewStudents table {
	width: 100%;
	border-collapse: collapse;
	border-left: none;
	border-right: none;
}
div#viewStudents th, div#viewStudents td {
	border: thin solid #39F;
	padding: 3px 7px;
	text-align: right;
}
div#viewStudents th {
	text-align: center;
}
div#viewStudents thead {
	color: #FFF;
	background-color: #69F;
}
</style>
</head>
<body id="main">
<div id="courses">
</div>
<div id="viewStudents">
</div>
<script>
$.getJSON("TaughtCourse",null,
	function callback(json) {
		$(json.taughtCourse).each(function(index, element) {
			tDiv=$("#courses").append("<div/>").children().last().attr("id",this.term).addClass("term").text(this.term);
			for (var i=0;i<this.courses.length;i++) {
				course=this.courses[i];
				cDiv=tDiv.append("<div/>").children().last().attr("id",course.cID).addClass("course");
				cDiv.append("<span/>").children().last().addClass("courseId").text(course.cID);
				cDiv.append("<h2/>").children().last().addClass("courseName").text(course.name);
				cDiv.append("<span/>").children().last().addClass("courseTime").text(course.time);
				cDiv.append("<br/>");
				cDiv.append("<span/>").children().last().addClass("courseLocal").text(course.place);
				cDiv.append("<br/>");
				cDiv.mouseover(function(e) {
					if (!$(this).hasClass("choosen"))
						$(this).css("background-color","#DEF");
				});
				cDiv.mouseout(function(e) {
					if (!$(this).hasClass("choosen"))
						$(this).css("background-color","#CEF");
				});
				cDiv.click(function(e) {
					if (!$(course).hasClass("choosen")) {
						$(".course").removeClass("choosen").css("background-color","#CEF");
						$(this).addClass("choosen").css("background-color","#FFF");
						$.getJSON("TestData/CourseInfor.json",null,	// The URL should be CourseListener, the Data should be sth from session
							function callback(json) {
								vTable=$("#viewStudents").empty()
									.append("<p/>").children().last().text("Students of "+json.CourseInfor.name+"("+json.CourseInfor.cID+"):").css("margin","20px")
									.append("<table/>").children().last();
								vTable.prepend("<thead/>").children().first().append("<tr/>").children().last()
									.append("<th>ID</th>")
									.append("<th>Name</th>")
									.append("<th>Sex</th>")
									.append("<th>Phone</th>")
									.append("<th>Email</th>");
								vTable=vTable.append("<tbody/>").children().last();
								$(json.CourseInfor.students).each(function(index, element) {
									vStu=vTable.append("<tr/>").children().last().attr("id",this.ID).addClass("student");
									vStu.append("<td/>").children().last().addClass("ID").text(this.ID);
									vStu.append("<td/>").children().last().addClass("name").text(this.name);
									vStu.append("<td/>").children().last().addClass("sex").text(this.sex);
									vStu.append("<td/>").children().last().addClass("phone").text(this.phone);
									vStu.append("<td/>").children().last().addClass("email").text(this.email);
								});
							});
					}
				});
			}
        });
		$("#courses .course").first().click();
	});
</script>
</body>
</html>