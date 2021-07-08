<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CRUD</title>
<%-- <link rel='stylesheet' href="<c:url value='/css/styles.css' />" --%>
<!-- 	type="text/css" /> -->


<script>
	window.addEventListener("load", function() {

		let id = ${id};

		let dataArea = document.getElementById("dataArea");
		let btn04 = document.getElementById("btn04");

// 		let uid = document.getElementById("userid");
//  		let uac = document.getElementById("customerName");
//  		let upwd = document.getElementById("userpassword");
//  		let utp = document.getElementById("usertelephone");
//  		let umail = document.getElementById("useremail");

//若要查詢 要加這串
// 		let xhr = new XMLHttpRequest();
// 		var url = "<c:url value='/customerBean/'/>" + id;
// 		xhr.open("post", url, true);
// 		xhr.send();
// 		xhr.onreadystatechange = function() {
// 			if (xhr.readyState == 4 && xhr.status == 200) {
// 				let response = JSON.parse(xhr.responseText);

// 				uid.value = response.userid;


//  				uac.value = response.customerName;
//  				upwd.value = response.userpassword;
//  				utp.value = response.usertelephone;
//  				umail.value = response.useremail;

// 			}
// 		};
// 到這邊
		btn04.onclick = function() {
			var url = "<c:url value='/customerBean/'/>" + id;
			var result = confirm("確定刪除此筆記錄(帳號:" + id + ")?");
		
			if (result) {
			    var xhr2 = new XMLHttpRequest();
		   		xhr2.open("post", url, true);
		   		xhr2.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
		   		xhr2.send();
		   		xhr2.onreadystatechange = function() {
							// 伺服器請求完成
		   		if (xhr2.readyState == 4 && xhr2.status == 200) {
		      		let json = JSON.parse(xhr2.responseText);
		      		if (json.success === "false") {
		      			alert(json.msg);
			  		} else if(json.success === "true") {
			  			alert(json.msg);
						window.location.href='<c:url value="/exciseSelectAll"/>';
		      		}                                                             
				} 
		      
			  }
		   } 
	 
		}
	})
</script>
</head>

<body>
	<form>

		<table border="1">
<h1>該會員資訊如下</h1>
			<tr>
				<td>id:</td>
				<td><input type="text" value="${CustomerInfo.customerID}" id="userid" disabled/></td>
			</tr>
						<tr>
							<td>name:</td>
							<td><input type="text" value="${CustomerInfo.customerName}" id="customerName" disabled/></td>
						</tr>
						<tr>
							<td>password:</td>
							<td><input type="password" value="${CustomerInfo.password}"
								id="userpassword" disabled/></td>

						</tr>
						<tr>
							<td>telephone:</td>
							<td><input type="tel" value="${CustomerInfo.cellphone}" id="usertelephone" disabled/></td>
						</tr>
						<tr>
							<td>email:</td>
							<td><input type="email" value="${CustomerInfo.email}" id="useremail" disabled/></td>
						</tr>
						<tr>
							<td>picture:</td>
							<td><img width='150' height='200' src= "${CustomerInfo.pictureString} "></td>
						</tr>

		</table>
		<br>
		<input id='btn04' type="button" value="確定刪除(AJAX)">
		<!--  <button type="submit">確定新增(submit)</button>-->

	</form>

</body>
</html>