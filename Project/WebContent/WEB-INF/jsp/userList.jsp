<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="userManagement.UserInfo"%>
<%@ page import="java.util.List "%>



<!DOCTYPE html>
<html lang="ja">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザー一覧</title>
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

	<div class="background">
		<h3>
			<header>


				<font size="2"> <pencil>${userInfo.name}</pencil>&nbsp;
					&nbsp;<pencil>さん</pencil>&nbsp;&nbsp;&nbsp;<a class="redbutton"
					onclick="location.href='/UserManagement/LoginScreen'" href="#"><pencil>ログアウト</pencil></a>
				</font>


			</header>
			<br>
			<Div Align="center">
				<font size="7"> <pencil>ユーザー一覧</pencil><br><br>
				</font> <font size="2" color = "green"> <pencil>${sucMsg}</pencil>
				</font>
				<Div Align="right">
					<font size="2"> <a class="bluebutton"
						onclick="location.href='/UserManagement/SignUp'" href="#"><pencil>新規登録</pencil></a>
					</font>
				</Div>
				<br> <br>
				<form action="/UserManagement/UserList" method="post">
					<pencil>ログインID&nbsp;:&nbsp;<input type="text" value="${loginId}"  name="loginId"></pencil>
					<br> <br>
					<pencil>ユーザー名&nbsp;:&nbsp;<input type="text" value="${name}" name="name"></pencil>
					<br> <br>
					<pencil>生年月日&nbsp;:&nbsp;<input type="date" style="width: 310px" value="${startBirth}" name="startBirth">&nbsp;〜&nbsp;
					<input type="date" style="width: 310px" value="${endBirth}" name="endBirth"></pencil>
					<br>
					<br> <font size="2">
						<button class="blackbutton" type="submit" href="#">
							<pencil>検索</pencil>
						</button>
					</font><br>
					<br>
				</form>
			</Div>

			<!-- Table -->
			<table class="table table-condensed">
				<thead>
					<tr>
						<th><pencil>ログインID</pencil></th>
						<th><pencil>ユーザー名</pencil></th>
						<th><pencil>生年月日</pencil></th>
						<th></th>
					</tr>
				</thead>
				<tbody>

					<%
						List<UserInfo> u = (List<UserInfo>) request.getAttribute("userList");
						for (int i = 0; i < u.size(); i++) {
					%>
					<tr>
						<td><pencil><%=u.get(i).getLoginId()%> </pencil></td>
						<td><pencil><%=u.get(i).getName()%></pencil></td>
						<td><pencil><%=u.get(i).getBirthDate()%></pencil></td>
						<td><font size="2"> <a class="blackbutton"
								onclick="location.href='/UserManagement/UserDetail?id=<%=u.get(i).getId()%>'"
								href="#"><pencil>詳細</pencil></a> <a class="blackbutton"
								onclick="location.href='/UserManagement/UserUpdate?id=<%=u.get(i).getId()%>'"
								href="#"><pencil>更新</pencil></a> <a class="blackbutton"
								onclick="location.href='/UserManagement/UserDelete?id=<%=u.get(i).getId()%>'"
								href="#"><pencil>削除</pencil></a>
						</font></td>
					</tr>
					<%
						}
					%>

				</tbody>
			</table>


		</h3>
	</div>

</body>

</html>