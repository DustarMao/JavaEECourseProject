<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通知管理</title>
<style type="text/css">
* {
	font-family: "Microsoft Yahei UI","Microsoft Yahei",Verdana,Simsun,"Segoe UI","Segoe UI Web Regular","Segoe UI Symbol","Helvetica Neue","BBAlpha Sans","S60 Sans",Arial,sans-serif;
	list-style-type: none;
}

body {
	margin: 0;
}

div {
	margin: 0;
	overflow-x: hidden;
	overflow-y: hidden;
}
button {
	padding: 5px 10px;
	background-color: #69F;
	border: thin solid #39F;
}

#addNotice .value {
	background-color: #FFF;
	border-style: none;
	text-align: right;
	float: right;
	width: inherit;
}
#addNotice form {
	margin: 20px;
}
#addNotice form div {
	display: inline-block;
	margin: 5px 10px;
	border-bottom: solid thin;
	width: 250px;
}
#addNotice form div label {
	position: absolute;
	z-index: 1;
}
#addNotice form hr {
	border-style: none;
	margin: 10px;
}
</style>
</head>
<body id="main">
<div id="addNotice">
	<p style="margin: 20px;">新通知</p>
	<form action="AddNotice" method="post">
		<div id="nTitle">
			<label for="nTitle">标题</label>
			<input type="text" name="nTitle" class="value" value="" />
		</div>
		<div id="nHref">
			<label for="nHref">链接</label>
			<input type="text" name="nHref" class="value" value="" />
		</div>
		<div id="Buttons" style="display: block; border-bottom: none;">
			<button type="submit">提交</button>
		</div>
	</form>
</div>
</body>
</html>