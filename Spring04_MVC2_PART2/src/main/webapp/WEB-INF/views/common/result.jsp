<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function init(){
	
	/* jstl은 서블릿과 관련..? jsp 파일을 만들기 전에 el jstl을 읽어서 만든다 */
	/* 따라서 script에 jstl 태그를 쓰면 java 코드가 아예 만들어지냐 안만들어지냐..임 그 이후에 브라우저로 html을 쏨*/
	/* script if문을 쓰면  */
	<c:if test="${alertMsg != null}">
		alert("<c:out value='${alertMsg}'/>");
	</c:if>
		
	<c:if test="${back != null}">
		history.go(-1);	
	</c:if>

	<c:if test="${url != null}">
		location.href="<c:out value='${url}'/>"	
	</c:if>
	
}

init();

</script>
</head>
<body>





</body>
</html>