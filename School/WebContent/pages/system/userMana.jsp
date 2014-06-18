<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理</title>
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

#queryUser .value {
	background-color: #FFF;
	border-style: none;
	text-align: right;
	float: right;
	width: inherit;
}
#queryUser form {
	margin: 20px;
}
#queryUser form div {
	display: inline-block;
	margin: 5px 10px;
	border-bottom: solid thin;
	width: 250px;
}
#queryUser form div label {
	position: absolute;
	z-index: 1;
}
#queryUser form hr {
	border-style: none;
	margin: 10px;
}
</style>
</head>
<body>
<div id="queryUser">
	<form>
		<div id="pID">
        	<label for="pID">ID:</label>
            <input type="number" class="value" name="pID" disabled />
        </div>
		<div id="pType">
			<label for="pType">类型:</label>
			<select name="pType" class="value" style="width: auto;">
				<option value="Tea">教师</option>
				<option value="Stu">学生</option>
				<option value="Admin">管理员</option>
			</select>
		</div>
		<div id="pDepart">
        	<label for="pDepart">学院:</label>
            <input type="text" class="value" name="pDepart"/>
        </div>
		<div id="sMajor" class="student">
        	<label for="sMajor">专业:</label>
            <input type="text" class="value" name="sMajor"/>
        </div>
		<div id="sStudyYear" class="student">
        	<label for="sStudyYear">学制:</label>
            <input type="text" class="value" name="sStudyYear"/>
        </div>
        <div id="sStudyType" class="student">
        	<label for="sStudyType">就读学位:</label>
            <input type="text" class="value" name="sStudyType"/>
        </div>
        <div id="tTeacherType" class="teacher">
        	<label for="tTeacherType">职称:</label>
            <input type="text" class="value" name="tTeacherType" disabled />
        </div>
        <div id="Buttons">
        	<button type="submit">提交</button>
        	<button type="button">查询新用户</button>
        </div>
	</form>
</div>
<script type="text/javascript">

</script>
</body>
</html>