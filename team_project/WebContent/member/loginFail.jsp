<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <c:set var="contextPath"  value="${pageContext.request.contextPath}"/>    
<!doctype html>
<html lang="en">
<body>

	<script>
		window.alert("로그인에 실패하였습니다.");
		location.href = "${contextPath}/mem/login.do";
	</script>
</body>

</html>