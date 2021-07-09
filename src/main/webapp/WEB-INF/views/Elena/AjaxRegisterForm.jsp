<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register Form</title>
<link rel='stylesheet' href="<c:url value='/css/styles.css' />"
	type="text/css" />

<script>
//window.onload
window.addEventListener("load", function() {
		let dataArea = document.getElementById("dataArea");
		let btn03 = document.getElementById("btn03");

		
		btn03.onclick = function() {
			let xhr = new XMLHttpRequest();

		    var uname = document.getElementById("customerName").value;
		    var uacc = document.getElementById("account").value;
		    var upwd = document.getElementById("password").value;
		    var umail = document.getElementById("email").value;
		    var ubday = document.getElementById("birthday").value;
		    var ugen = document.querySelector('input[name="gender"]:checked').value;
// 		    var ugen = document.getElementsByName("gender")[0].value;
		    var uphone = document.getElementById("phone").value;
		    var uadd = document.getElementById("address").value;
			
			var url = "<c:url value='/elena/ajaxregister.controller' />";
// 			 block, non-block
			//post=新增
			//open=傳到後端的方式，非同步
			xhr.open("POST", url, true);
			//下面那行:使用json傳到後端
			
		   	xhr.setRequestHeader("Content-type", "application/json");
		   	//下面那行=傳到後端
		   	//第一個customerName=JavaBean的屬性
		   	let formData = {"customerName":uname,"account":uacc, "password":upwd, "email":umail, "birthday":ubday, "gender":ugen, "cellphone":uphone, "address":uadd};
// 			xhr.send("name=" + uname + "&account=" + uacc + "&password=" + upwd + "&email=" + umail + "&birthday=" + ubday + "&gender=" + ugen + "&phone=" + uphone + "&address=" + uadd);
		   	//xhr.send(`name=${uac}&pwd=${upwd}&tel=${utel}&mail=${umail}`);
		   	
		   	//下面這行就是把資料送到後端
		   	xhr.send(JSON.stringify(formData));
			xhr.onreadystatechange = function() {
				//done:xhr.readyState == 4 && xhr.status == 200
				if (xhr.readyState == 4 && xhr.status == 200) {

					//accbean = xhr.responseText
					dataArea.innerHTML = showSingleMember(xhr.responseText);
// 					uac.value= "";
// 					upwd.value= "";
// 					utel.value= "";
// 					umail.value= "";
					
				}
			}
		}
})
		function showSingleMember(text) {
			var member = JSON.parse(text);
			var segment = "<table border='6'>";
			segment += "<tr>";
			segment += "<th>會員ID</th>";
			segment += "<th>會員姓名</th>";
			segment += "<th>會員帳號</th>";
			segment += "<th>會員密碼</th>";
			segment += "<th>會員email</th>";
			segment += "<th>會員生日</th>";
			segment += "<th>會員性別</th>";
			segment += "<th>會員電話</th>";
			segment += "<th>會員地址</th>";
			segment += "</tr>";

			//member.customerID -> json檔裡面的key
			//把javaBean包成json
			segment += "<tr>";
			segment += "<td>" + member.customerID + "</td>";
			segment += "<td>" + member.customerName + "</td>";
			segment += "<td>" + member.account + "</td>";
			segment += "<td>" + member.password + "</td>";
			segment += "<td>" + member.email + "</td>";
			segment += "<td>" + member.birthday + "</td>";
			segment += "<td>" + member.gender + "</td>";
			segment += "<td>" + member.cellphone + "</td>";
			segment += "<td>" + member.address + "</td>";
			segment += "</tr>";
			segment += "</table>";

			return segment;
		
}
</script>	
	
	
	
</head>
<body BGCOLOR="#FFD9EC">

<h2 align="center">
註冊資料
</h2>

<!-- <form action="ajaxregister.controller" method="post"> -->
<center>
<table  cellspacing="2" cellpadding="1" border="1" width="60%">
<tr>
    <td>姓名:</td>
    <td><input id="customerName" type="text" name="customerName" size="10" maxlength="10" placeholder="ex.Elena">
    </td>
</tr>
<tr>
    <td>帳號:</td>
    <td><input id="account" type="text" name="account" size="10" maxlength="10" placeholder="請輸入帳號">
    </td>
</tr>
<tr>
    <td>密碼:</td>
    <td><input id="password" type="password" name="password" size="10" maxlength="10" placeholder="請輸入密碼">
    </td>
</tr>
<tr>
    <td>E-mail:</td>
    <td><input id="email" type="text" name="email" size="30" maxlength="20" placeholder="ex.elena841011@hotmail.com">
    </td>
</tr>
<tr>
    <td>生日:</td>
    <td><input id="birthday" type="date" name="birthday" size="20" maxlength="20">
    </td>
</tr>
 <td>性別:</td>
	<td><lable><input class="gender" type="radio" name="gender" value="male">男性</lable>
	<lable><input class="gender" type="radio" name="gender" value="female">女性</lable>
	</td>
<tr>
    <td>聯絡電話:</td>
    <td><input id="phone" type="text" name="phone" size="20" placeholder="請輸入電話">
    </td>
</tr>
<tr>
    <td width="150">聯絡地址:</td>
    <td><input id="address" type="text" name="address" size="50" maxlength="50" placeholder="請輸入地址">
    </td>
</tr>

</table>
</center>
<center>
<br>

<!-- <button onclick="window.location.href='./register.controller';" >送出</button> -->
<input type="button" id="btn03" value="送出(Ajax)")/>
<input type="reset" value="重置"/><br>
<div id='dataArea'>&nbsp;</div><br>
</form>

<%-- <a href="<c:url value='/loginMainPage.controller'/>">返回登入頁面</a><br> --%>
<%-- <button onclick="window.location.href='<c:url value='/loginMainPage.controller'/>'; return false;" >返回登入頁面</button> --%>
<button onclick="window.location.href='./logout'; return false;" >返回登入頁面</button>

</center>
</body>
</html>