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

<h1>dynamic Query 목록</h1>
<hr>

<table border="">

<tr>
	<th>번호</th>
	<th>id</th>
	<th>password</th>
</tr>

<c:forEach var="member" items="${list }">
<tr>
	<td>${member.NO }</td>
	<td>${member.ID }</td>
	<td>${member.PW }</td>
</tr>
</c:forEach>

</table>

</body>
</html>