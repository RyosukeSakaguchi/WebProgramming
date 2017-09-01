<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="userManagement.UserInfo" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザー情報詳細参照</title>
    <!-- BootstrapのCSS読み込み -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- jQuery読み込み -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <!-- BootstrapのJS読み込み -->
    <script src="js/bootstrap.min.js"></script>
      <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css" rel="stylesheet">
 <LINK rel="stylesheet" type="text/css" href="userManagement.css">
  </head>
<body>
<h3>
<header>
<font size="2">
<pencil>ユーザー名</pencil>&nbsp; &nbsp;<pencil>さん</pencil>&nbsp;&nbsp;&nbsp;<a class="redbutton" onclick="location.href='loginScreen.html'" href="#"><pencil>ログアウト</pencil></a>
</font>
</header><br>
<Div Align="center">
<font size="7"> <pencil>ユーザー情報詳細参照</pencil><br><br>
</font>
<%
UserInfo u = (UserInfo)request.getAttribute("userInfo");
%>
<pencil>ログインID&nbsp;:&nbsp;<%= u.getLoginId() %></pencil><br><br>
<pencil>ユーザー名&nbsp;:&nbsp;<%= u.getName() %></pencil><br><br>
<pencil>生年月日&nbsp;:&nbsp;<%= u.getBirthDate() %></pencil><br><br>
<pencil>登録日時&nbsp;:&nbsp;<%= u.getCreateDate() %></pencil><br><br>
<pencil>更新日時&nbsp;:&nbsp;<%= u.getUpdateDate() %></pencil><br><br>
</Div>
<font size="2">
<a class="bluebutton" onclick="location.href='/UserManagement/UserList'" href="#"><pencil>戻る</pencil></a>
</font>
</h3>
</body>
</html>