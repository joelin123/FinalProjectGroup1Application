<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>使用者登入</title>
<style>

div{
width: 500px; 
height: 250px;
border:3px #FF60AF solid; 
border-radius: 8px; 
border-style:dashed; 
border-width:3px;


}

</style>
</head>
<body bgcolor="#FFD9EC">
<center>
<form action="<c:url value='/elena/login'/>" method="post">

<H1 ALIGN="CENTER">Login or Create Account</H1>

<h4>Please enter your account and password</h4>

<div>
<br>
 Account  :  <INPUT TYPE="TEXT" NAME="userName" required><BR>
  ${errors.username}
  <br>
  <br>
  Password :  <INPUT TYPE="password" NAME="userPwd" required><BR>
  ${errors.userpwd}
  <BR>
<a href="<c:url value='/elena/forgetpassword'/>">忘記密碼</a><br>
  <BR>
<tr><td><input name="login" type="submit" value="登入"/>

<td><input type="reset" value="重置"/><br>
<br>
${Failed}
<BR>
還沒有帳號嗎?<a href="<c:url value='/elena/registration'/>">請點我註冊</a><br>
<a href="<c:url value='/googleLogin' />">google登入</a><br>
<br>
</div>
<!--  <input name="register" type="submit" value="註冊"/><br> -->
<br>

<br>
<br>


</form>
<br>
<!-- 還沒有帳號嗎？，請點擊<a name="submit" href="register.jsp">這裡</a>註冊！
 -->
</body>
</center>
</html>