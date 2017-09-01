<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="userManagement.UserInfo"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザー削除確認</title>
<!-- BootstrapのCSS読み込み -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- jQuery読み込み -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- BootstrapのJS読み込み -->
<script src="js/bootstrap.min.js"></script>
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
	rel="stylesheet">
<LINK rel="stylesheet" type="text/css" href="userManagement.css">
</head>
<body>
	<h3>
		<header>
			<font size="2"> <pencil>ユーザー名</pencil>&nbsp; &nbsp;<pencil>さん</pencil>&nbsp;&nbsp;&nbsp;<a
				class="redbutton" onclick="location.href='loginScreen.html'"
				href="#"><pencil>ログアウト</pencil></a>
			</font>
		</header>
		<br>
		<Div Align="center">
			<font size="7"> <pencil>ユーザー削除確認</pencil><br> <br>
			</font>
		</Div>
		<%
			UserInfo u = (UserInfo) request.getAttribute("userInfo");
		%>
		<pencil> ログインID&nbsp;:&nbsp;<%=u.getLoginId()%><br>
		を本当に削除してよろしいでしょうか。</pencil>
		<br> <br> <br> <br>
			<div Align="center" class="wrapper">
				<div>
					<font size="2"> <a class="blackbutton" onclick="location.href='/UserManagement/UserList'" href="#"><pencil>キャンセル</pencil></a>
					</font>
				</div>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<form action="/UserManagement/UserDelete" method="post">
					<input type="hidden" value="<%=u.getId()%>" name="id">
					<div>
						<font size="2">
							<button class="blackbutton" type="submit">
								<pencil>ＯＫ</pencil>
							</button>
						</font>
					</div>
				</form>
			</div>

	</h3>
</body>
</html>