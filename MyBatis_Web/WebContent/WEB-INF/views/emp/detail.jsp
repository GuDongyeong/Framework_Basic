<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
h1{
	text-align: center;
}

table{
	margin: 0 auto;
}

div{
	text-align: center;
}
</style>
</head>
<body>

<h1>${emp.ename }의 정보</h1>
<hr>

<div>
<table border="1">
<thead>
<tr>
	<th>사원번호</th>
	<th>사원명</th>
	<th>직무</th>
	<th>매니저</th>
	<th>입사일</th>
	<th>급여</th>
	<th>상여금</th>
	<th>직무번호</th>
</tr>
</thead>
<tbody>
<tr>
	<td>${emp.empno }</td>
	<td>${emp.ename }</td>
	<td>${emp.job }</td>
	<td>${emp.mgr }</td>
	<td>
		<fmt:formatDate value="${emp.hiredate }" pattern="yyyyMMdd" /><br>
		<fmt:formatDate value="${emp.hiredate }" pattern="HHmmss" />
	</td>
	<td>${emp.sal }</td>
	<td>${emp.comm }</td>
	<td>${emp.deptno }</td>
</tr>
</tbody>
</table>

<br>
<button type="button" onclick="location.href='/emp/list'">목록</button>

</div>
</body>
</html>