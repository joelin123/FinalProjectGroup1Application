<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
var currentUser = "${CurrentUser}";
var customerName = "${CurrentUser.customerName}";

window.onload = function(){

if("${loginSuccess}" == "登入成功!"){
// 	alert("${name}" + "," + "歡迎回來!");
	Swal.fire("${name}" + "," + "歡迎回來!");
}


// 有登入:隱藏登入標籤 & 沒登入:隱藏登出標籤，秀登入標籤
var logoutHref = document.getElementById("logoutHref");
var loginHref = document.getElementById("loginHref");
var myAccount = document.getElementById("myAccount");

	if (!customerName) {
			logoutHref.hidden = true;
			myAccount.hidden = true;
			loginHref.style.visibility = "visible";
		}
		if (customerName) {
			logoutHref.style.visibility = "visible";
			myAccount.style.visibility = "visible";
			loginHref.hidden = true;
		}
}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<c:choose>
<c:when test="${not empty CurrentUser}">

<c:set var="currentaccount" scope="session" value="${CurrentUser.account}"/>
<c:set var="currentname" scope="session" value="${CurrentUser.customerName}"/>
<c:set var="loginorout" scope="session" value="登出"/>
</c:when>

<c:otherwise>
<c:set var="currentaccount" scope="session" value="訪客帳號"/>
<c:set var="currentname" scope="session" value="訪客"/>
<c:set var="loginorout" scope="session" value="會員登入"/>
</c:otherwise>
</c:choose>





你好, ${currentname},歡迎來到On The Way
	<nav id="nav">
					<ul>

<%-- 						<li><a href="<c:url value='/elena' />">${loginorout}</a></li> --%>
						<li id="logoutHref" style="visibility:hidden"><a href="<c:url value="/elena/logout"/>">登出</a></li>
						<li id="myAccount" style="visibility:hidden"><a href="<c:url value="/elena/gotoCustomerHomePage"/>">我的帳號</a></li>
						<li id="loginHref"><a href="<c:url value="/elena"/>">登入</a></li>
						<li><a href="<c:url value='/milton' />">購物車</a></li>
						<li><a href="<c:url value='/joelin' />">飯店</a></li>
						<li><a href="<c:url value='/sylvia' />">活動</a></li>
						<li><a href="<c:url value='/wade' />">交通</a></li>
						<li><a href="<c:url value='/bos' />">論壇</a></li>
					</ul>
				</nav>


</body>
</html>