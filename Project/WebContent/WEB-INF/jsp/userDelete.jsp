<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="userManagement.UserInfo"%>

<!DOCTYPE html>
<html>
<head>
<title>ユーザー削除確認</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="userManagement.css">
</head>
<body>
	<font size="5">
		<header>
			<font size="2">
					<pencil>${loginUser.name}&nbsp;&nbsp;さん</pencil>&nbsp;&nbsp;&nbsp;<button class="redbutton" onclick="location.href='/UserManagement/Logout'"><pencil>ログアウト</pencil></button>
			</font>
		</header><br>
		<div align="center">
			<font size="7"> <pencil>ユーザー削除確認</pencil></font>
		</div><br> <br> <br>
		<%
		UserInfo u = (UserInfo) request.getAttribute("userInfo");
		if(u == null){
		%>
		<div align="center">
			<pencil>全ユーザー情報を本当に削除してよろしいでしょうか。</pencil>
		</div>
		<%
		}else{
		%>
		<div align="center">
			<pencil> ログインID&nbsp;:&nbsp;<%=u.getLoginId()%><br><br>を本当に削除してよろしいでしょうか。</pencil>
		</div>
		<%
		}
		%>
		<br> <br> <br> <br>
		<div class="wrapper">
			<div>
				<font size="2"> <button class="blackbutton" onclick="location.href='/UserManagement/UserList'"><pencil>キャンセル</pencil></button></font>
			</div>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<form action="/UserManagement/UserDelete" method="post">
				<% if(u != null){%>
					<input type="hidden" value="<%=u.getId()%>" name="id">
				<% }%>
				<div>
					<font size="2">
						<button class="blackbutton" type="submit"><pencil>ＯＫ</pencil></button>
					</font>
				</div>
			</form>
		</div>
	</font>
</body>
</html>