<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/join.css">

</head>
<body>
 <c:set var="contextPath"  value="${pageContext.request.contextPath}"/>    

<form action="${contextPath}/mem/joinPro.do" method="post" style="border:1px solid #ccc">
  <div class="container">
    <h1>Sign Up</h1>
    <p>Please fill in this form to create an account.</p>
    <hr>

    <label for="id"><b>Id</b></label>
    <input type="text" placeholder="Enter Id" name="id" required>

    <label for="psw"><b>Password</b></label>
    <input type="password" placeholder="Enter Password" name="passwd" required>

    <label for="psw-repeat"><b>Repeat Password</b></label>
    <input type="password" placeholder="Repeat Password" name="passwd-repeat" required>

	<label for="name"><b>Name</b></label>
	<input type="text" placeholder="Enter Name" name="name" id="name" required>

	<label for="birth"><b>Birth</b></label>
	<input type="text" placeholder="Enter Birth" name="birth" id="birth" required>
	
	
	<label for="email"><b>Email</b></label>
	<input type="email" name="email" id="email" placeholder="Email" required >
	
	<label for="phone"><b>Phone</b></label>
	<input type="text" name="phone" id="phone" placeholder="Phone" required >
	
	<label for="address"><b>Address</b></label>
	<input type="text" name="address" id="address" placeholder="Address" required >
	


    <label>
      <input type="checkbox" checked="checked" name="remember" style="margin-bottom:15px"> Remember me
    </label>

    <p>By creating an account you agree to our <a href="#" style="color:dodgerblue">Terms & Privacy</a>.</p>

    <div class="clearfix">
      <button type="button" class="cancelbtn">Cancel</button>
      <button type="submit" class="signupbtn">Sign Up</button>
    </div>
    
  </div>
</form>
</body>
</html>