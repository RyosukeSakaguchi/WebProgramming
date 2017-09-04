<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="userManagement.UserInfo"%>

<!DOCTYPE html>
<html>
<head>
<title>ユーザー情報更新</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="userManagement.css">
</head>
<body>
	<font size="5">
			<header>
				<font size="2">
						<pencil>${loginUser.name}</pencil>&nbsp;&nbsp;<pencil>さん</pencil>&nbsp;&nbsp;&nbsp;
						<button class="redbutton" onclick="location.href='/UserManagement/Logout'"><pencil>ログアウト</pencil></button>
				</font>
			</header><br>
			<div align="center">
				<font size="7">
					<pencil>ユーザー情報更新</pencil><br><br>
				</font>
				<font size="3" color="red">
					<pencil>${errMsg}</pencil>
				</font><br><br>
				<%
				UserInfo u = (UserInfo) request.getAttribute("userInfo");
				%>
				<form action="/UserManagement/UserUpdate" method="post">
					<pencil>ログインID&nbsp;:&nbsp;<%=u.getLoginId()%></pencil><br><br>
					<pencil>パスワード&nbsp;:&nbsp;</pencil><input type="password" name="password"><br><br>
					<pencil>パスワード(確認)&nbsp;:&nbsp;</pencil><input type="password" name="passwordConf"><br><br>
					<pencil>ユーザー名&nbsp;:&nbsp;<input type="text" value="<%=u.getName()%>" name="name"></pencil><br><br>
					<pencil>生年月日&nbsp;:&nbsp;<input type="date" value="<%=u.getBirthDate()%>" name="birthDate"></pencil><br><br>
					<input type="hidden" value="<%=u.getId()%>" name="id">
					<font size="2">
						<button class="blackbutton" type="submit">
							<pencil>更新</pencil>
						</button>
					</font><br><br>
				</form>
			</div>
			<font size="2">
				<button class="bluebutton" onclick="location.href='/UserManagement/UserList'" ><pencil>戻る</pencil></button>
			</font>
	</font>
</body>
</html>