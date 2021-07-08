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
		let dataArea = document.getElementById("dataArea");
		let dataArea1 = document.getElementById("dataArea1");
		let btnselectone = document.getElementById("btnselectone");


			let xhr = new XMLHttpRequest();

			var url = "<c:url value='/exciseSelectAll.controller' />?";

			xhr.open("GET", url, true);
			xhr.send();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {

					dataArea.innerHTML = showAllMember(xhr.responseText);

				}
			}

	
	
	function showAllMember(text) {
		var members = JSON.parse(text);

		var segment = "<table border='1' width='100%'>";
		segment += "<tr>";
		segment += "<th>會員ID</th>";
		segment += "<th>會員姓名</th>";
		segment += "<th>會員帳號</th>";
		segment += "<th>會員密碼</th>";
		segment += "<th>會員生日</th>";
		segment += "<th>會員電子郵件</th>";
		segment += "<th>會員性別</th>";
		segment += "<th>會員電話</th>";
		segment += "<th>會員地址</th>";
		segment += "<th>會員圖片</th>";
		segment += "<th>編輯</th>";
		segment += "<th>刪除</th>";
		segment += "</tr>";

		for (var i = 0; i < members.length; i++) {
			let member = members[i];

			let urlUpdate = "<c:url value='/exciseNotRestUpdate/'/>"  + member.customerID;
			let urlDelete = "<c:url value='/exciseNotRestDelete/'/>"  + member.customerID;


			segment += "<tr>";
			segment += "<td>" + member.customerID + "</td>";
			segment += "<td>" + member.customerName + "</td>";
			segment += "<td>" + member.account + "</td>";
			segment += "<td>" + member.password + "</td>";
			segment += "<td>" + member.birthday + "</td>";
			segment += "<td>" + member.email + "</td>";
			segment += "<td>" + member.gender + "</td>";
			segment += "<td>" + member.cellphone + "</td>";
			segment += "<td>" + member.address + "</td>";
			segment += "<td><img width='100' height='60' src='" + member.pictureString + "'></td>";
			segment += "<td><a href='" + urlUpdate + "'>修改</a></td>";
			segment += "<td><a href='" + urlDelete + "'>刪除</a></td>";
			
			segment += "</tr>";

		}

		segment += "</table>";

		return segment;

	}

	btnselectone.onclick = function() {
		
		let uid = document.getElementById("selectone");
			let xhr = new XMLHttpRequest();

			var url = "<c:url value='/exciseSelect.controller/"+uid.value+"'/>" ;
			xhr.open("GET", url, true);
			xhr.send();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					
					dataArea1.innerHTML = showSingleMember(xhr.responseText);

				}else{
					dataArea1.innerHTML = "查無資料，請輸入正確會員ID";
				}
			};
		};

	
	function showSingleMember(text) {
		var member = JSON.parse(text);
		var segment = "";
			segment = "<table border='1'>";
			segment += "<tr>";
			segment += "<th>會員ID</th>";
			segment += "<th>會員姓名</th>";
			segment += "<th>會員帳號</th>";
			segment += "<th>會員密碼</th>";
			segment += "<th>會員生日</th>";
			segment += "<th>會員電子郵件</th>";
			segment += "<th>會員性別</th>";
			segment += "<th>會員電話</th>";
			segment += "<th>會員地址</th>";
			segment += "<th>會員圖片</th>";
			segment += "</tr>";

			segment += "<tr>";
			segment += "<td>" + member.customerID + "</td>";
			segment += "<td>" + member.customerName + "</td>";
			segment += "<td>" + member.account + "</td>";
			segment += "<td>" + member.password + "</td>";
			segment += "<td>" + member.birthday + "</td>";
			segment += "<td>" + member.email + "</td>";
			segment += "<td>" + member.gender + "</td>";
			segment += "<td>" + member.cellphone + "</td>";
			segment += "<td>" + member.address + "</td>";
			segment += "<td><img width='100' height='60' src='" + member.pictureString + "'></td>";
			segment += "</tr>";
			segment += "</table>";
	
			
		return segment;

	}
})
</script>
</head>

<body>
	<form>

		<table border="1">
			<thead>
				<tr>
					<td colspan="2" align="center">會員清冊<br />
					</td>
				</tr>
			</thead>
			<tr>

			</tr>


		</table>

		<input type='text' id=selectone>
		<input type="button" id= 'btnselectone' value="查詢單筆會員">
		<!--  <button type="submit">確定新增(submit)</button>-->
		<a href="<c:url value='/exciseInsert'/>"><input type="button" value="新增會員"></a>
		<hr>
		<div id='dataArea1' value="222">&nbsp;</div>
		<hr>
		<div id='dataArea' style='max-height: 300px;overflow:auto;'>&nbsp;</div>


	</form>


</body>
</html>