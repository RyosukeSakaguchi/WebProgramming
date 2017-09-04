<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="userManagement.UserInfo"%>
<%@ page import="java.util.List "%>

<!DOCTYPE html>
<html lang="ja">
<head>
<title>ユーザー一覧</title>
<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="userManagement.css">
</head>
<body>
	<font size="5">

		<header>
			<font size="2">
				<pencil>${loginUser.name}</pencil>&nbsp;&nbsp;
				<pencil>さん</pencil>&nbsp;&nbsp;&nbsp;
				<button class="redbutton" onclick="location.href='/UserManagement/Logout'"><pencil>ログアウト</pencil></button>
			</font>
		</header><br>

		<div align="center">
			<font size="7"> <pencil>ユーザー一覧</pencil><br><br></font>
			<font size="2" color = "green"> <pencil>${sucMsg}</pencil></font>
			<div align="right">
				<font size="2"> <button class="bluebutton" onclick="location.href='/UserManagement/SignUp'"><pencil>新規登録</pencil></button></font>
			</div><br> <br>
			<form action="/UserManagement/UserList" method="post">
				<pencil>ログインID&nbsp;:&nbsp;<input type="text" value="${loginId}"  name="loginId"></pencil><br><br><br>
				<pencil>ユーザー名&nbsp;:&nbsp;<input type="text" value="${name}" name="name"></pencil><br><br><br>
				<pencil>生年月日&nbsp;:&nbsp;<input type="date" style="width: 310px" value="${startBirth}" name="startBirth">&nbsp;〜&nbsp;
				<input type="date" style="width: 310px" value="${endBirth}" name="endBirth"></pencil><br><br><br>
				<font size="2"><button class="blackbutton" type="submit"><pencil>検索</pencil></button></font><br><br><br><br><br>
			</form>
		</div>

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
				UserInfo loginUser= (UserInfo)session.getAttribute("loginUser");
				for (int i = 0; i < u.size(); i++) {
					if(!u.get(i).getId().equals("1")){
			%>
				<tr>
					<td><pencil><%=u.get(i).getLoginId()%> </pencil></td>
					<td><pencil><%=u.get(i).getName()%></pencil></td>
					<td><pencil><%=u.get(i).getBirthDate()%></pencil></td>
					<td>
						<font size="2">
							<button class="blackbutton" onclick="location.href='/UserManagement/UserDetail?id=<%=u.get(i).getId()%>'"><pencil>詳細</pencil></button>
							<%if(loginUser.getId().equals("1")){%>
								<button class="blackbutton"onclick="location.href='/UserManagement/UserUpdate?id=<%=u.get(i).getId()%>'"><pencil>更新</pencil></button>
							<%}else if(loginUser.getId().equals(u.get(i).getId())){%>
								<button class="blackbutton"onclick="location.href='/UserManagement/UserUpdate?id=<%=u.get(i).getId()%>'"><pencil>更新</pencil></button>
							<%}%>
							<%if(loginUser.getId().equals("1")){%>
								<button class="blackbutton" onclick="location.href='/UserManagement/UserDelete?id=<%=u.get(i).getId()%>'"><pencil>削除</pencil></button>
							<%}%>
						</font>
					</td>
				</tr>
			<%		}
				}
			%>
			</tbody>
		</table><br>

		<div align="center">
			<%if(loginUser.getId().equals("1")){%>
			<button class="redbutton" onclick="location.href='/UserManagement/UserDelete'"><pencil>全ユーザー削除</pencil></button>
			<%}%>
		</div><br>
	</font>
</body>
</html>