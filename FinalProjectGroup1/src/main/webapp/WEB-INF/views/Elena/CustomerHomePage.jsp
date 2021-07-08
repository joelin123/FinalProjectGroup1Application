<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CustomerHomePage</title>
<style>
li {
	font-size: 20px;
}
</style>
</head>
<body bgcolor="#FFD9EC">

	<!--<form action=".\CustomerLoginServlet" method="post">  -->
	<h1>Hello ${CurrentUser.customerName}</h1>
	<h2>帳戶設定</h2>
	<ul>
		<li><a href="<c:url value='/elena/gotoCustomerInfo' />">查看個人資訊</a></li>修改個人資訊
		<br>
		<li><a href="<c:url value='/joelin' />">帳號與安全</a></li>更新您的密碼以保障帳戶安全
	</ul>
	<!-- <table  cellspacing="2" cellpadding="2" border="1" width="60%"> -->
	<!-- <tr bgcolor="#D2E9FF"> -->
	<%--     <td>個人資訊: <button onclick="window.location.href='customerInfo.page?id=${id}';" >查看個人資訊</button><!-- input name="checkmyInfo" type="submit" value="管理個人資料"/ --></td> --%>
	<!-- </tr> -->
	<!-- </table> -->
	<!-- <br> -->
	<!-- <br> -->
	<!-- <table  cellspacing="2" cellpadding="2" border="1" width="60%"> -->
	<!-- <tr bgcolor="#D2E9FF"> -->
	<!--     <td>安全: <input name="checkCustomerSafetyPage" type="submit" value="管理帳戶安全"/></td> -->
	<!-- </tr> -->
	<!-- </table> -->


	<!-- <br> -->
	<!-- <br> -->

	<!-- <br> -->

	<button onclick="window.location.href='./logout';">登出</button>
	<br>

	<!--</form>  -->
</body>
</html>