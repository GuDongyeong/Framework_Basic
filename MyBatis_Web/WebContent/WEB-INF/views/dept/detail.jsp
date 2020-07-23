<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>부서 상세 정보</h1>
<hr>

<table>
<thead>
<tr>
	<th>부서번호</th>
	<th>부서명</th>
	<th>위치</th>
</tr>
</thead>

<tbody>
<tr>
	<td>${res.deptno }</td>
	<td>${res.dname }</td>
	<td>${res.loc }</td>
</tr>
</tbody>
</table>

<hr>
<a href="javascript:history.go(-1)">뒤로 가기</a>

</body>
</html>