<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>密码管理</title>
<style type="text/css">
#changePass {
	margin: 20px;
	float: left;
}
#changePass div {
	margin: 10px;
}
#changePass div label {
	width: 100px;
	display: inline-block;
	margin: 5px;
}
#btnChangePass {
	float: right;
}
#btnChangePass button {
	padding: 5px 10px;
	background-color: #69F;
	border: thin solid #39F;
}
#passInfor {
	padding: 5px;
	margin: 20px 10px;
}
</style>
</head>
<body>
<form id="changePass">
	<div id="oldPass">
	    <label for="OldPass">旧密码:</label>
        <input type="password" id="OldPass" />
    </div>
    <div id="newPass">
    	<label for="NewPass">新密码:</label>
        <input type="password" id="NewPass" />
    </div>
    <div id="repeatPass">
    	<label for="RepeatPass">重复新密码:</label>
        <input type="password" id="RepeatPass" />
    </div>
    <div id="btnChangePass">
    	<button type="submit" id="submitChange">提交</button>
        <button type="reset" id="resetInputs">重置</button>
    </div>
    <div id="passInfor" style="padding: 5px;margin: 20px 10px;"></div>
</form>
<script>
$("#OldPass").change(function(e) {
	$.post("ChangePassword", {
		oPass: $("#OldPass").val(),
		nPass: ""
	}, function callback(data) {
		$("#passInfor").text(data);
	});
});
$("#NewPass").change(function(e) {
	if ($(this).val()==$("#OldPass")) {
		$("#passInfor").text("新旧密码相同");
	}
	else {
		$("#passInfor").text("");
	}
		
});
$("#RepeatPass").change(function(e) {
	if ($(this).val()!=$("#NewPass").val()) {
		$("#passInfor").text("重复新密码错误");
	}
	else {
		$("#passInfor").text("");
	}
});
$("#submitChange").click(function(e) {
	if ($("#NewPass").val()!=$("#RepeatPass").val()) {
		alert("重复新密码错误");
		return;
	}
	if ($("#NewPass").val()==$("#OldPass").val()) {
		alert("新旧密码相同");
		return;
	}
	$.post("ChangePassword", {
		oPass: $("#OldPass").val(),
		nPass: $("#NewPass").val()
	}, function callback(data) {
		if (data=="Success") {
			$("#passInfor").text("密码修改成功");
		}
		else {
			alert(data);
		}
	});
});
</script>
</body>
</html>