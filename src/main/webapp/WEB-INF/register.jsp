<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="/register" method="post">  <!-- 4.postregister를 찾아감 -->
		아이디 : <input type="text" name="id"><br>
		비밀번호 : <input type="password" name="password"><br>
		이름 : <input type="text" name="name"><br>
		<input type="submit" value="회원가입">
	</form>
</body>
</html>