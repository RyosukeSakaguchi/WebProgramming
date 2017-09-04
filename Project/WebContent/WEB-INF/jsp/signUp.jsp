<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
<title>ユーザー新規登録</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="userManagement.css">
</head>
<body>
	<font size="5">

		<header>
			<font size="2">
				<pencil>${loginUser.name}</pencil>&nbsp;&nbsp;<pencil>さん</pencil>&nbsp;&nbsp;&nbsp; <button class="redbutton" onclick="location.href='/UserManagement/Logout'"><pencil>ログアウト</pencil></button>
			</font>
		</header><br>

		<div align="center">
			<font size="7"> <pencil>ユーザー新規登録</pencil><br><br></font>
			<font size="3" color="red"><pencil>${errMsg}</pencil></font><br><br>
			<form action="/UserManagement/SignUp" method="post">
				<pencil>ログインID&nbsp;:&nbsp;<input type="text" value="${loginId}" name="loginId"></pencil><br><br>
				<pencil>パスワード&nbsp;:&nbsp;</pencil>
				<input type="password" name="password"><br><br>
				<pencil>パスワード(確認)&nbsp;:&nbsp;</pencil>
				<input type="password" name="passwordConf"><br><br>
				<pencil>ユーザー名&nbsp;:&nbsp;<input type="text" value="${name}" name="name"></pencil><br><br>
				<pencil>生年月日&nbsp;:&nbsp;<input type="date" style="width: 310px" value="${birthDate}" name="birthDate"></pencil><br><br>
				<font size="2"><button class="blackbutton" type="submit"><pencil>登録</pencil></button></font><br><br>
			</form>
		</div>

		<font size="2"><button class="bluebutton" onclick="location.href='/UserManagement/UserList'" ><pencil>戻る</pencil></button></font>
	</font>
</body>
</html>