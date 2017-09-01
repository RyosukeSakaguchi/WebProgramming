<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
 <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>ユーザー新規登録</title>
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
<font size="7"> <pencil>ユーザー新規登録</pencil><br><br>
</font>
<font size="3" color="red"><pencil>${errMsg}</pencil></font><br><br>
<form action="/UserManagement/SignUp" method="post">
<pencil>ログインID&nbsp;:&nbsp;<input type="text" value="${loginId}" name="loginId"></pencil><br><br>
<pencil>パスワード&nbsp;:&nbsp;</pencil><input type="password" name="password"><br><br>
<pencil>パスワード(確認)&nbsp;:&nbsp;</pencil><input type="password" name="passwordConf"><br><br>
<pencil>ユーザー名&nbsp;:&nbsp;<input type="text" value="${name}" name="name"></pencil><br><br>
<pencil>生年月日&nbsp;:&nbsp;<input type="date" style="width:310px" value="${birthDate}" name="birthDate"></pencil><br><br>
<font size="2">
<button class="blackbutton" type="submit" href="#"><pencil>登録</pencil></button>
</font><br><br>
</form>
</Div>
<font size="2">
<a class="bluebutton" onclick="location.href='/UserManagement/UserList'" href="#"><pencil>戻る</pencil></a>
</font>

</h3>
</body>
</html>