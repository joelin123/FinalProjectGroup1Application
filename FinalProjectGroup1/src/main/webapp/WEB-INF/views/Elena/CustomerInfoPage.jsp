<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>CustomerInfoPage</title>
</head>
<body bgcolor="#FFD9EC">

<script type="text/javascript">
	function reconfirm(){
		ProceedDelete = confirm("確定要刪除嗎?");
		if (ProceedDelete) {
			window.location.href='./deleteAccount.do'; 
		}
// 		return false;
	}
</script>
<%-- <jsp:useBean id="customerBean" class="login.model.CustomerBean" scope="session" /> --%>
<center>
<h2>
Hello! ${name}
會員資料頁面
</h2>
</center>
<!-- <form action="/selectCustomerInfo.controller" method="post"> -->
<center>
<table  cellspacing="2" cellpadding="1" border="1" width="80%">
<tr bgcolor="#FFFFE1">
    <td>Customer ID:</td>
    <td>${id}
<!--     = request.getParameter("bean").getCustomerID -->
    </td>
</tr>
<tr bgcolor="#FFFFE1">
    <td>姓名:</td>
    <td>${name}
    </td>
</tr>
<tr bgcolor="#FFFFE1">
    <td>帳號:</td>
    <td>${account}
    </td>
</tr>
<tr bgcolor="#FFFFE1">
    <td>密碼:</td>
    <td>${password}
    </td>
</tr>
<tr bgcolor="#F2F4FB">
    <td width="150">E-mail:</td>
    <td>${email}
   </td>
</tr>
<tr bgcolor="#F2F4FB">
    <td width="150">生日:</td>
    <td>${birthday}
   </td>
</tr>
<tr bgcolor="#FFFFE1">
    <td>性別</td>
    <td>${gender}
    </td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>連絡電話:</td>
    <td>${cellphone}
    </td>
</tr>
<tr bgcolor="#F2F4FB">
    <td>連絡地址:</td>
    <td>${address}
    </td>
</tr>
</table>
</center>
<center>
<br>
<button onclick="window.location.href='updateForm.do';" >修改個人資料</button>
<button onclick="reconfirm();" >刪除個人帳號</button>
<button onclick="window.location.href='./logout';" >登出</button>
</center>

</body>
</html>
</body>
</html>