<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 <link rel="stylesheet" href="../css/login.css">

</head>
<body>
 <c:set var="contextPath"  value="${pageContext.request.contextPath}"/>    

<form action="${contextPath}/mem/loginPro.do" method="post">
 
  <div class="container">
  	<h1>Login</h1>
  	<br>
    <label for="id"><b>Id</b></label>
    <input type="text" placeholder="Enter Id" name="id" required>
	<br><br>
    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="passwd" required>
	<br><br>
    <button type="submit">Login</button>
	<button style="background-color: orange;" onclick="location.href='${contextPath}/mem/join.do'">Sign up</button>
    
  </div>

  
</form>


</body>
</html>