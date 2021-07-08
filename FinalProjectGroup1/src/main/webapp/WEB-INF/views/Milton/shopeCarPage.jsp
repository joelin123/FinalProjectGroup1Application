<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html>
<html>
<head>

<link  rel='stylesheet' href="<c:url value='/css/style.css'  />" />	 
<meta charset="UTF-8">
<title>Spring Boot</title>
</head>
<body>
<div align='center'>
    <h2>購物車</h2>
    <hr>
<%--     <a href="<c:url value='/hello' />"> Hello World </a><br>  --%>
    <a href="<c:url value='/readfile/excel/adc' />">匯入初始資料</a><br> 
    <a href="<c:url value='/queryRestaurant' />">新增商品資料</a><br> 
    <a href="<c:url value='/insertRestaurant' />">購買商品資料</a><br> 
    <hr>
    <a href="<c:url value='/insertType' />">新增類型資料</a><br> 
    <a href="<c:url value='/queryType' />">查詢類型資料</a><br> 
 
    <hr>
<%-- <img  src='${pageContext.request.contextPath}/images/PDF.png' > --%>

<!-- 注意下一行 -->
<%-- <img  src="<c:url value='/images/PDF.png'  />" > --%>
 </div>   
</body>
</body>
</html>