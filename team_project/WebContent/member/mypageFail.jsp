<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta charset="utf-8">
 <meta http-equiv="X-UA-Compatible" content="IE=edge">
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
 <c:set var="contextPath"  value="${pageContext.request.contextPath}"/>    
 
<title>Insert title here</title>
</head>

<body >
<script>
window.alert("비밀번호가 틀립니다.");
location.href = "${contextPath}/mem/myCheck.do";
</script>
</body>
</html>