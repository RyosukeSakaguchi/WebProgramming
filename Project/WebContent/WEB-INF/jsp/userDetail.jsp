<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="userManagement.UserInfo"%>

<!DOCTYPE html>
<html>
<head>
<title>ユーザー情報詳細参照</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="userManagement.css">
</head>
<body>
	<font size="5">

		<header>
			<font size="2">
				<pencil>${loginUser.name}</pencil>&nbsp;&nbsp;<pencil>さん</pencil>&nbsp;&nbsp;&nbsp;
				<button class="redbutton" onclick="location.href='/UserManagement/Logout'" ><pencil>ログアウト</pencil></button>
			</font>
		</header><br>

		<div align="center">
			<font size="7"> <pencil>ユーザー情報詳細参照</pencil></font><br><br><br>
			<%
			UserInfo u = (UserInfo) request.getAttribute("userInfo");
			%>
			<pencil>ログインID&nbsp;:&nbsp;<%=u.getLoginId()%></pencil><br><br><br>
			<pencil>ユーザー名&nbsp;:&nbsp;<%=u.getName()%></pencil><br><br><br>
			<pencil>生年月日&nbsp;:&nbsp;<%=u.getBirthDate()%></pencil><br><br><br>
			<pencil>登録日時&nbsp;:&nbsp;<%=u.getCreateDate()%></pencil><br><br><br>
			<pencil>更新日時&nbsp;:&nbsp;<%=u.getUpdateDate()%></pencil><br><br><br>
		</div>

		<font size="2"><button class="bluebutton" onclick="location.href='/UserManagement/UserList'" ><pencil>戻る</pencil></button></font>

	</font>
</body>
</html>