<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>부서 등록</h1>
<hr>

<form action="/dept/insert" method="post">

부서 번호 <input type="number" min="0" max="99" name="deptno" /><br>

부서명 <input type="text" name="dname" /><br>

위치 <input type="text" name="loc" /><br><br>

<button>등록</button>

</form>

</body>
</html>