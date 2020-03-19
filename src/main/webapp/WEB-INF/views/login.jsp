<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width">
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/signin.css">
<title> AJIN ICMS Login Page </title>
</head>
<body class="text-center">
	<div class="container">
		<form class="form-signin"> 
			<img src="/resources/images/ajin.png">
			<h2 class="h2 font-weight-norma">로그인 페이지</h2>
		</form>
		<form class="form-signin" name="loginForm" method="post" action="/login"> 
			<div class="form-group form-signin">
				<input type="text" class="form-control" placeholder="admin" id="userId" name="userId" maxlength="20">
				<input type="password" class="form-control" placeholder="비밀번호" id="userPw" name="userPw" maxlength="20">
			</div>
			<div class="form-group form-signin">
				<input type="submit" class="btn btn-primary form-control" value="로그인">
			</div>
		 </form>	
	</div>
</body>
</html>
