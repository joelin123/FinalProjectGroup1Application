<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
<style type="text/css">
   span.error {
	color: red;
	display: inline-block;
	font-size: 5pt;
}
</style>
<meta charset="UTF-8">
<link rel='stylesheet' href="<c:url value='/css/style.css' />" type="text/css" />
</head>
<body>
<%-- <p>你的工资为 : <c:out value="${productName_Live}"/></p>   --%>
<c:choose>
	<c:when test="${ not empty typeList }">
		<c:set var="productName_Live" scope="session" value="${typeList}"/>
<%-- 		<c:set var="productPrice_Live" scope="session" value="${productList.productprice}"/> --%>
	</c:when>



<%-- <c:otherwise> --%>
<%-- <c:set var="productName_Live" scope="session" value="訪客"/> --%>
<%-- <c:set var="productPrice_Live" scope="session" value="會員登入"/> --%>
<%-- </c:otherwise> --%>
</c:choose>









<div align="center">
<fieldset style="width:960px;">
	<legend >商品項目</legend> 
<%-- 	    <form:form method="POST" modelAttribute="place" > --%>
	<form:form method="POST" modelAttribute="productinfo" enctype='multipart/form-data'>
	<Table>
	<c:choose>
		<c:when test='${place.placeId == null}'>
		    <tr>
		    	<td>&nbsp;</td>
		    	<td>
	   	  		   &nbsp;
	   	  		</td>
		    </tr>
        </c:when>	   
    	<c:otherwise>
	 	 <tr>
	       <td>編號：<br>&nbsp;</td>
	   	   <td><form:hidden path='placeId'/>
	   	    	${place.placeId}<br>&nbsp;
	   	   </td>
	    </tr>
       </c:otherwise>   
    </c:choose>         
	   <tr>  
<%-- 	      <td><spring:message code='place.form.caption.typeid'  /><br>&nbsp;</td> --%>
	        <td>類型：<br>&nbsp;</td>
	        <td  width='360'>
	      	   <form:select  path="type.typeId" >
	      	      <form:option label="請挑選" value="-1" />
	      	     
	      	      <form:options items="${typeList}"
	      	   	   itemLabel="typeName"  itemValue="typeId"   />
	      	   	</form:select>   
	      	   	   <br>&nbsp;	 
		       <form:errors path='type.typeId' cssClass="error"/>

<!-- 		  </td> -->
<%-- 	      <td><spring:message code='place.form.caption.restaurant.name'  /><br>&nbsp;</td> --%>
<%-- 		  <td  width='360'><form:input path='productname' /><br>&nbsp;	 --%>
<%-- 		      <form:errors path='productname' cssClass="error"/> --%>
<!-- 		  </td> -->
		  
	   </tr>
	    <tr>

	    	<td>商品：<br>&nbsp;</td>
	   		<td>
	   		<form:select  path="type.typeId" >
	      	      <form:option label="請挑選" value="-1" />
	      	     
	      	      <form:options items="${productList}"
	      	   	   itemLabel="productname"  itemValue="productid"   />  
 	      	   	</form:select>     
	      	   	   <br>&nbsp;
 	      	</td>	  
	   </tr>  
	  
<!-- 	   <tr> -->
<!-- 	      <td>電話：<br>&nbsp;</td> -->
<!-- 	      <td  width='360'> -->
<%-- 	      	<form:input path="phone"/><br>&nbsp;	 --%>
<%-- 		      <form:errors path='phone' cssClass="error"/> --%>
<!-- 		  </td> -->
<!-- 		   <td>地址：<br>&nbsp;</td> -->
<!-- 	   	  <td> -->
<%-- 	      	<form:input path="address"/><br>&nbsp;	 --%>
<%-- 		      <form:errors path='address' cssClass="error"/> --%>
<!-- 		  </td> -->
<!-- 	   </tr>	    -->
<!-- 	   <tr> -->
<!-- 	      <td>經度：<br>&nbsp;</td> -->
<!-- 	        <td  width='360'> -->
<%-- 	      	<form:input path="longitude"/><br>&nbsp;	 --%>
<%-- 		      <form:errors path='longitude' cssClass="error"/> --%>
<!-- 		  </td> -->
<!-- 	      <td>緯度：<br>&nbsp;</td> -->
<%-- 		  <td  width='360'><form:input path='latitude' /><br>&nbsp;	 --%>
<%-- 		      <form:errors path='latitude' cssClass="error"/> --%>
<!-- 		  </td> -->
		  
<!-- 	   </tr> -->
	 
<!-- 	   <tr> -->
<!-- 	      <td>網址：<br>&nbsp;</td> -->
<!-- 	   	  <td> -->
<%-- 	   	  	 <form:input path="link"/><br>&nbsp; --%>
<%-- 	   	  	 <form:errors path="link"  cssClass="error" /> --%>
<!-- 	   	  </td> -->
<!-- 	   	  <td>照片：<br>&nbsp;</td> -->
<!-- 	   	  <td> -->
<%-- 	   	  	 <form:input path="placeImage" type='file'/><br>&nbsp; --%>
<%-- 	   	  	 <form:errors path="placeImage"  cssClass="error" /> --%>
<!-- 	   	  </td> -->
<!-- 	   </tr> -->
	   <tr>
	    <td colspan='4' align='center'><br>&nbsp;
	      <input type='submit'>
        </td>
	   </tr>
	</Table>
		 
	</form:form>
	 <p>你購買的商品: <c:out value="${typeList}"/></p>  
</fieldset>
<br>
<fieldset style="width:960px;">
	<legend >購物內容</legend> 
	<table border="1px">
		<tr>
		<td>商品類型</td>
		<td>商品名稱</td>
		<td>數量</td>
		<td>金額</td>
		<td>操作</td>
		</tr>
<%-- 		<c:forEach items="${cart.bookMap}" var="me"> --%>
<!-- 		<tr> -->
<%-- 		<td>${me.key}</td> --%>
<%-- 		<td>${me.value.book.name}</td> --%>
<%-- 		<td>${me.value.quantity}</td> --%>
<%-- 		<td>${me.value.price}</td> --%>
<!-- 		<td><a href="#">刪除</a></td> -->
<!-- 		</tr> -->
<%-- 		</c:forEach> --%>
<!-- 		<tr> -->
<!-- 		<td colspan="2"><a href="#">清空購物車</a></td> -->
<!-- 		<td colspan="2">合計：</td> -->
<%-- 		<td>${cart.price}</td> --%>
		</tr>
	</table>
</fieldset>
<a href="<c:url value='/'/> " >回前頁</a>
</div>
</body>
</html>