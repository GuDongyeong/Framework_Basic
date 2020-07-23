<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

</style>

</head>
<body>

<h1>사원 전체 조회</h1>
<hr>


<table border="1">
<thead>
<tr>
	<th>사원번호</th>
	<th>사원번호</th>
	<th>직무</th>
	<th>매니저</th>
	<th>입사일</th>
	<th>급여</th>
	<th>상여금</th>
	<th>부서 번호</th>
</tr>
</thead>
<tbody>
<c:forEach items="${list }" var="i">
<tr>
	<td>${i.empno }</td>
	<td><a href="/emp/detail?empno=${i.empno }">${i.ename }</a></td>
	<td>${i.job }</td>
	<td>${i.mgr }</td>
	<td>
		<fmt:formatDate value="${i.hiredate }" pattern="yyyyMMdd" /><br>
		<fmt:formatDate value="${i.hiredate }" pattern="HHmmss" />
	</td>
	<td>${i.sal }</td>
	<td>${i.comm }</td>
	<td>${i.deptno }</td>
</tr>
</c:forEach>
</tbody>
</table>

</body>
</html>