<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>selectKey 태그 테스트</h1>
<hr>

<form action="/mybatis/selectKey" method="post">

<label>아이디 : <input type="text" name="id" id="id" /></label>

<br>

<label>비밀번호 : <input type="text" name="pw" id="pw" /></label>

<br>

<input type="submit" value="로그인" />
</form>

</body>
</html>