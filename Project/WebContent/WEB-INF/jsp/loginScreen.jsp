<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>ログイン画面</title>
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
<br> <br>
<Div Align="center">
<font size="7">
<pencil>ログイン画面</pencil><br><br>
</font>
<pencil>

<font size="3" color="red"><pencil>${errMsg}</pencil></font>

</pencil>
<br><br>
<form action="/UserManagement/LoginScreen" method="post">
<pencil>ログインID&nbsp;:&nbsp;<input type="text" name="loginId"></pencil><br><br>
<pencil>パスワード&nbsp;:&nbsp;</pencil><input type="password" name="password" ><br><br>
<font size="2">
<button class="blackbutton" type="submit" href="#"><pencil>ログイン</pencil></button>
</font>
</form>
</Div>

</h3>
</body>

</html>