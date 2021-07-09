<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CustomerHomePage</title>
<style>

</style>
</head>
<body bgcolor="#FFD9EC">
<center>
<!--<form action=".\CustomerLoginServlet" method="post">  -->  
<h1>Hello ${name}</h1>
<h2>帳戶設定</h2>

<table  cellspacing="2" cellpadding="2" border="1" width="60%">
<tr bgcolor="#D2E9FF">
    <td>個人資訊: <button onclick="window.location.href='customerInfo.page?id=${id}';" >查看個人資訊</button><!-- input name="checkmyInfo" type="submit" value="管理個人資料"/ --></td>
</tr>
</table>
<br>
<br>
<table  cellspacing="2" cellpadding="2" border="1" width="60%">
<tr bgcolor="#D2E9FF">
    <td>安全: <input name="checkCustomerSafetyPage" type="submit" value="管理帳戶安全"/></td>
</tr>
</table>


<br>
<br>

<br>
<button onclick="window.location.href='./logout';" >登出</button><br>
</center>
<!--</form>  -->
</body>
</html>