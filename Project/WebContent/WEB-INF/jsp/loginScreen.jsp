<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>ログイン画面</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="userManagement.css">
</head>
<body>
	<font size="5"><br> <br>
		<div align="center">
			<font size="7">
				<pencil>ログイン画面</pencil><br><br><br>
			</font>
			<font size="3" color="red">
				<pencil>${errMsg}</pencil>
			</font>
			<font size="3" color="green">
				<pencil>${logout}</pencil>
			</font><br><br><br>
			<form action="/UserManagement/LoginScreen" method="post">
				<pencil>ログインID&nbsp;:&nbsp;<input type="text" value="${loginId}" name="loginId"></pencil><br><br><br>
				<pencil>パスワード&nbsp;:&nbsp;</pencil><input type="password" name="password"><br><br><br>
				<font size="2">
					<button class="blackbutton" type="submit"><pencil>ログイン</pencil></button>
				</font>
			</form>
		</div>
	</font>
</body>
</html>