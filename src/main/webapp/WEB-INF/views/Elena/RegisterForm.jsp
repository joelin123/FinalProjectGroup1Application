<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Form</title>
</head>
<body BGCOLOR="#FFD9EC">

<h2 align="center">
註冊資料
</h2>

<form action="register.controller" method="post">
<center>
<table  cellspacing="2" cellpadding="1" border="1" width="60%">
<tr>
<input type="hidden" name="customerID">
    <td>姓名:</td>
    <td><input type="text" name="customerName" size="10" maxlength="10" placeholder="ex.Elena">
    </td>
</tr>
<tr>
    <td>帳號:</td>
    <td><input type="text" name="account" size="10" maxlength="10" placeholder="請輸入帳號">
    </td>
</tr>
<tr>
    <td>密碼:</td>
    <td><input type="password" name="password" size="10" maxlength="10" placeholder="請輸入密碼">
    </td>
</tr>
<tr>
    <td>E-mail:</td>
    <td><input type="text" name="email" size="30" maxlength="20" placeholder="ex.elena841011@hotmail.com">
    </td>
</tr>
<tr>
    <td>生日:</td>
    <td><input type="date" name="birthday" size="20" maxlength="20">
    </td>
</tr>
 <td>性別:</td>
	<td><lable><input id="male" type="radio" name="gender" value="male">男性</lable>
	<lable><input id="female" type="radio" name="gender" value="female">女性</lable>
	</td>
<tr>
    <td>聯絡電話:</td>
    <td><input type="text" name="phone" size="20" placeholder="請輸入電話">
    </td>
</tr>
<tr>
    <td width="150">聯絡地址:</td>
    <td><input type="text" name="address" size="50" maxlength="50" placeholder="請輸入地址">
    </td>
</tr>

</table>
</center>
<center>
<br>

<!-- <button onclick="window.location.href='./register.controller';" >送出</button> -->
<input type="submit" name="update" value="送出" >
</form>

<%-- <a href="<c:url value='/loginMainPage.controller'/>">返回登入頁面</a><br> --%>
<%-- <button onclick="window.location.href='<c:url value='/loginMainPage.controller'/>'; return false;" >返回登入頁面</button> --%>
<button onclick="window.location.href='loginMainPage.controller'; return false;" >返回登入頁面</button>
<input type="reset" value="重置"/>
</center>
</body>
</html>