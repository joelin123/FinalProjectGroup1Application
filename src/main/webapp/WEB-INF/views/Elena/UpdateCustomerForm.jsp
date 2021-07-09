<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Page</title>
</head>
<body bgcolor="#D2E9FF">
<%-- <jsp:useBean id="customerBean" class="login.model.CustomerBean" scope="session" /> --%>
<center>
<h2>
修改會員資料
</h2>
</center>
<form action="update.controllerweb" method="post">
<center>


<script>
// window.onload = function() {

// 	var sendData = document.getElementById("sendData"); //add another update button
// 	sendData.onclick = function() {

// 		//define format
// 		var xhr = new XMLHttpRequest();
// 		xhr.open("POST", "<c:url value='/update.controller' />", true);
// 		xhr.setRequestHeader("Content-Type",
// 				"application/x-www-form-urlencoded");

// 		//assamble data
// 		xhr.send("id=" + id);
// 		var message = "";


// 		xhr.onreadystatechange = function() {
// 			// 伺服器請求完成
// 			if (xhr.readyState == 4 && xhr.status == 200) {
// 				var result = JSON.parse(xhr.responseText);

				
// 			}
// 		}
// 	}

/*	
	var alink = document.getElementById("accountCheck");
	var div = document.getElementById('result0c');
	alink.onclick = function() {
		var id = document.getElementById("id").value;
		if (!id) {
			div.innerHTML = "<font color='blue' size='-1'>請輸入帳號</font>";
			return;
		}
		var xhr = new XMLHttpRequest();
		xhr.open("POST", "<c:url value='/update.controller' />", true);
		xhr.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		xhr.send("id=" + id);
		var message = "";
		xhr.onreadystatechange = function() {
			// 伺服器請求完成
			if (xhr.readyState == 4 && xhr.status == 200) {
				var result = JSON.parse(xhr.responseText);
				
				//id.length==0表示查無此帳號
				if (result.id.length == 0) {
					message = "帳號可用";
				} else if (result.id.startsWith("Error")) {
					message = result.id;
				} else {
					message = "帳號重複，請重新輸入帳號";
				}
				div.innerHTML = "<font color='red' size='-2'>" + message
						+ "</font>";
			}
		}

		*/
// 	}

















        function showBox(inputID) {
            if(inputID =='name'){
                document.getElementById("name").innerHTML = '<INPUT TYPE="TEXT" NAME="customerName">';
            }
            if(inputID =='account'){
                document.getElementById("account").innerHTML = '<INPUT TYPE="text" NAME="account">';
            }
            if(inputID =='password'){
                document.getElementById("password").innerHTML = '<INPUT TYPE="text" NAME="password">';
            }
            if(inputID =='email'){
                document.getElementById("email").innerHTML = '<INPUT TYPE="text" NAME="email">';
            }
            if(inputID =='birthday'){
                document.getElementById("birthday").innerHTML = '<INPUT TYPE="text" NAME="birthday">';
            }
            if(inputID =='cellphone'){
                document.getElementById("cellphone").innerHTML = '<INPUT TYPE="text" NAME="cellphone">';
            }
            if(inputID =='address'){
                document.getElementById("address").innerHTML = '<INPUT TYPE="text" NAME="address">';
            }
            
                
        }
    
    </script>




<table  cellspacing="2" cellpadding="1" border="1" width="80%">
<tr bgcolor="#FFD9EC">
    <td>Customer ID:</td>
    <td>${id}
    </td>
</tr>
<tr bgcolor="#FFFFE1">
    <td>姓名:</td>
    <td>${name}
    <INPUT TYPE="button" style="float:right" value="修改" onclick="showBox('name')">
    <p id="name"></p>
   </td>
</tr>
<tr bgcolor="#FFFFE1">
    <td>帳號:</td>
    <td>${account}
    <INPUT TYPE="button" style="float:right" value="修改" onclick="showBox('account')">
    <p id="account"></p>
    </td>
</tr>
<tr bgcolor="#FFFFE1">
    <td>密碼:</td>
    <td>${password}
    <INPUT TYPE="button" style="float:right" value="修改" onclick="showBox('password')">
    <p id="password"></p>
    </td>
</tr>
<tr bgcolor="#F2F4FB">
    <td width="150">E-mail:</td>
    <td>${email}
    <INPUT TYPE="button" style="float:right" value="修改" onclick="showBox('email')">
    <p id="email"></p>
    </td>
</tr>
<tr bgcolor="#F2F4FB">
    <td width="150">生日:</td>
    <td>${birthday}
    <INPUT TYPE="button" style="float:right" value="修改" onclick="showBox('birthday')">
    <p id="birthday"></p>
    </td>
</tr>
<tr bgcolor="#FFFFE1">
    <td>性別</td>
    <td>${gender}
    <lable><input id="male" type="radio" name="gender" value="male">男性</lable>
	<lable><input id="female" type="radio" name="gender" value="female">女性</lable>
	
    </td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>連絡電話:</td>
    <td>${cellphone}
    <INPUT TYPE="button" style="float:right" value="修改" onclick="showBox('cellphone')">
    <p id="cellphone"></p>
    </td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>連絡地址:</td>
    <td>${address}
    <INPUT TYPE="button" style="float:right" value="修改" onclick="showBox('address')">
    <p id="address"></p>
    </td>
</tr>
</table>
</center>
<center>

<br>
<button onclick="window.location.href='backToCustomerHomePage.do'; return false;" >返回個人資訊頁面</button>
<input type="submit" name="update" value="更新資料" >
<button onclick="window.location.href='./logout'; return false;" >登出</button>
</center>
</form>
</body>
</html>
</body>
</html>