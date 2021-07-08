<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.1.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/javascript/j_frontfiltercu2.js"></script>
<style>
legend {
	font-weight: bold;
	color: black;
	background-color: white;
	border: 5px solid #cccccc;
	padding: 10px 8px;
}

fieldset legend {
	background: #369;
	color: #FFF;
	font-weight: 800;
	text-align: center;
	white-space: nowrap;
}
</style>
<script>
   function autoadd(){
	    $("#idAccount").val("user1234");
		$("#idEmail").val("elena841011@hotmail.com");	
		$("#idPhone").val("0984023801");
		$("#idPassword").val("user123@");
		$("#idName").val("胡家瑜");
		$("#idAddress").val("新北市蘆洲區長安街47號");
		$("#idBday").val("1995-10-11");
		$("#gender").val("女");
	   
   }


</script>

<meta charset="UTF-8">
<title>Register Form</title>
</head>
<body BGCOLOR="#FFD9EC">

	<div align="center">
		<fieldset style="width: 1000px;">
			<legend align="center">On The Way 會員註冊</legend>

			<form:form id="submitform" method="post" modelAttribute="customer"
				enctype='multipart/form-data'>


				<table cellspacing="2" cellpadding="1" border="1" width="85%">
					<tr>
						<!-- label會自己幫我生出for -->
						<td><form:label path="customerName">姓名:</form:label></td>
						<td><form:input id= "idName" path="customerName" type="text" size="10"
								maxlength="10" placeholder="請輸入姓名" />
								<span id='NameResult'></span>
								</td>
					</tr>
					<tr>

						<td><form:label path="account">帳號:</form:label><br></td>
						<td><form:input id="idAccount" path="account" type="text" size="20"
								maxlength="10" placeholder="請輸入帳號" />
							<button class="inputbutton" type="button" id="accountbutton">查看帳號是否已被註冊</button>
							<span id='AccountResult'></span>
							<br>
							<p class="rule">
								(1.不可空白，2.至少6個字且字首為英文字母，其餘英數字混合，3.不可包含特殊字元[!@#$%^&*]與中文字)</p></td>
					</tr>
					<tr>
						<td><form:label path="password">密碼:</form:label></td>
						<td><form:input id="idPassword" path="password" type="password" size="10"
								maxlength="10" placeholder="請輸入密碼" />
								<span id='PasswordResult'></span>
							<p class="rule">(1.不可空白，2.至少6個字且必須 包含英文字母、數字、特殊字元[!@#$%^&*])</p>
						</td>
					</tr>
					<tr>
						<td><form:label path="email">E-mail:</form:label></td>
						<td><form:input id="idEmail" path="email" type="text" size="30"
								maxlength="40" placeholder="請輸入信箱" />
							<button class="inputbutton" type="button" id="emailbutton">查看信箱是否已被註冊</button>
							<span id='EmailResult'></span>
							<br/>
							<p class="rule">(不可空白)</p></td>
					</tr>
					<tr>
						<td><form:label path="birthday">生日:</form:label></td>
						<td><form:input id="idBday" path="birthday" type="date" size="20"
								maxlength="20" />
								<span id='BdayResult'></span>
							<p class="rule">(不可空白)</p></td>
					</tr>
					<td><form:label id= "gender" path="gender">性別:</form:label></td>
					<td><form:radiobutton path="gender" value="男" label="男"
							checked="true" /> <form:radiobutton path="gender" value="女"
							label="女" /></td>
					<tr>
						<td><form:label path="cellphone">聯絡電話:</form:label></td>
						<td><form:input id="idPhone" path="cellphone" type="text" size="20"
								maxlength="20" placeholder="請輸入手機" />
							<button class="inputbutton" type="button" id="cellphonebutton">查看電話是否已被註冊</button>
							<span id='PhoneResult'></span>
							<br />
							<p class="rule">(不可空白)</p></td>
					</tr>
					<tr>
						<td><form:label path="address" width="150">聯絡地址:</form:label></td>
						<td><form:input id="idAddress" path="address" type="text" size="50"
								maxlength="50" placeholder="請輸入地址" />
								<span id='AddressResult'></span>
								</td>
					</tr>

					<tr>
						<td><form:label path="customerImage">上傳個人圖片:</form:label></td>
						<td>
						<img id="blah" src="#" alt="your image" width='150' height='200' />
						<form:input id="imgInp" path="customerImage"
								type='file' /></td>
						<br>
					</tr>

				</table>

				<br>

				<!-- <button onclick="window.location.href='./register.controller';" >送出</button> -->
				<form:button value="Send" id="sendData">送出</form:button>
				<button type="button" class="autoinputbutton" onclick="autoadd()">一鍵輸入</button>
			</form:form>
		</fieldset>

		<%-- <a href="<c:url value='/loginMainPage.controller'/>">返回登入頁面</a><br> --%>
		<%-- <button onclick="window.location.href='<c:url value='/loginMainPage.controller'/>'; return false;" >返回登入頁面</button> --%>

		<button onclick="window.location.href='loginMainPage.controller'; return false;">返回登入頁面</button>
			
	</div>

	<script>
	
	window.addEventListener("load", function(){
		
		var dupflag1 = false;
		var dupflag2 = false;
		var dupflag3 = false;

		//檢查帳號信箱電話重複的controller mapping
		const targetlink1 = '<c:url value="/Ajax/Elena/checkduplicateaccount"/>';
		const targetlink2 = '<c:url value="/Ajax/Elena/checkduplicatecellphone"/>';
		const targetlink3 = '<c:url value="/Ajax/Elena/checkduplicateemail"/>';
		
		//檢查帳號信箱電話重複的按鈕
// 		let accountbutton = document.getElementById("accountbutton");
// 		let emailbutton = document.getElementById("emailbutton");
// 		let cellphonebutton = document.getElementById("cellphonebutton");
		
// 		//讀取使用者輸入的值
// 		var name = document.getElementById("idName").value;
// 		var account = document.getElementById("idAccount").value;
// 		var password = document.getElementById("idPassword").value;
// 		var email = document.getElementById("idEmail").value;
// 		var bday = document.getElementById("idBday").value;
// 		var gender = document.querySelector('input[name="gender"]:checked').value;
// 		var cellphone = document.getElementById("idPhone").value;
// 		var address = document.getElementById("idAddress").value;
		
// 		var hasError = false; // 設定判斷有無錯誤的旗標
// 		var div0 = document.getElementById('NameResult');
// 		var div1 = document.getElementById('AccountResult');
// 		var div2 = document.getElementById('PasswordResult');
// 		var div3 = document.getElementById('EmailResult');
// 		var div4 = document.getElementById('BdayResult');
// 		var div5 = document.getElementById('PhoneResult');
// 		var div6 = document.getElementById('AddressResult');
		
// 		var sendData = document.getElementById("sendData");
		
		//下方:若使用者沒輸入值的話
// 		sendData.onclick = function() {
			
// 			hasError = false;
// 			if (!name) {
// 				div0.innerHTML = "<font color='red' size='-2'>請輸入名字</font>";
// 				hasError = true;
// 			} else {
// 				div0.innerHTML = "";
// 			}
// 			if (!account) {
// 				div1.innerHTML = "<font color='red' size='-2'>請輸入帳號</font>";
// 				hasError = true;
// 			} else {
// 				div1.innerHTML = "";
// 			}
// 			if (!password) {
// 				div2.innerHTML = "<font color='red' size='-2'>請輸入密碼</font>";
// 				hasError = true;
// 			} else {
// 				div2.innerHTML = "";
// 			}
// 			if (!email) {
// 				div3.innerHTML = "<font color='red' size='-2'>請輸入Email</font>";
// 				hasError = true;
// 			} else {
// 				div3.innerHTML = "";
// 			}
// 			if (!bday) {
// 				div4.innerHTML = "<font color='red' size='-2'>請輸入生日</font>";
// 				hasError = true;
// 			} else {
// 				div4.innerHTML = "";
// 			}
// 			if (!cellphone) {
// 				div5.innerHTML = "<font color='red' size='-2'>請輸入手機</font>";
// 				hasError = true;
// 			} else {
// 				div5.innerHTML = "";
// 			}
// 			if (!address) {
// 				div6.innerHTML = "<font color='red' size='-2'>請輸入地址</font>";
// 				hasError = true;
// 			} else {
// 				div6.innerHTML = "";
// 			}
//  			if (hasError) {
//  				return false;
//  			}
// 		}
		
// 		var xhr1 = new XMLHttpRequest();
// 		xhr1.open("POST", "<c:url value='/registration' />", true);
// 		xhr1.setRequestHeader("Content-Type",
// 				"application/x-www-form-urlencoded");
// 		xhr1.send("typename=" + typename + "&comment=" + comment
// 				+ "&createDate=" + createDate);
		
		
		
// 		accountbutton.onclick = function(){
// 			let xhr = new XMLHttpRequest();
// 			xhr.open("POST", targetlink1, true);
// 		   	xhr.setRequestHeader("Content-Type",
// 					"application/x-www-form-urlencoded");
// // 		   	let accountData = {"account":account};
// 		   xhr.send('{account:' + account +' }')
		   	
// 		   	if (xhr.readyState == 4 && xhr.status == 200){
// 		   		if(xhr.responseText == 0){
// 		   			div1.innerHTML = "此帳號已被註冊，請使用其他帳號";
// 		   			dupflag1 = false;
// 		   		}else{
// 		   			dupflag1 = true;
// 		   			div1.innerHTML = "此帳號可以使用";
// 		   		}
// 		   	}

// 		}
		
		$("#accountbutton").click(function () {

            $.ajax({
                url: targetlink1,    //url位置
                type: 'POST', //請求方式
                data: { account: $("#idAccount").val() },
                success: function (res) {
                    if (res == 0) {
                        alert("此帳號已被註冊，請使用其他帳號")
                        dupflag1 = false;
                    } else {
                        dupflag1 = true;
                        alert("此帳號可以使用")

                    }
                }
            })
        });
		
		
		 $("#cellphonebutton").click(function () {

             $.ajax({
                 url: targetlink2,    //url位置
                 type: 'POST', //請求方式
                 data: { cellphone: $("#idPhone").val() },
                 success: function (res) {
                     if (res == 0) {
                         alert("此電話已被註冊，請使用其他帳號")
                         dupflag2 = false;
                     } else {
                         alert("此電話可以使用")
                         dupflag2 = true;
                     }
                 }
             })
         });

         $("#emailbutton").click(function () {

             $.ajax({
                 url: targetlink3,    //url位置
                 type: 'POST', //請求方式
                 data: { email: $("#idEmail").val() },
                 success: function (res) {
                     if (res == 0) {
                         alert("此信箱已被註冊，請使用其他帳號")
                         dupflag3 = false;
                     } else {
                         alert("此信箱可以使用")
                         dupflag3 = true;
                     }
                 }
             })
         });
         
         
         $("#sendData").submit(function (event) {

             if (!(dupflag1 && dupflag2 && dupflag3)) {
                 window.alert("請檢查相關資料是否重複");
                 event.preventDefault();
             }

         });
         
         //預覽圖片
         imgInp.onchange = evt => {
        	  const [file] = imgInp.files
        	  if (file) {
        	    blah.src = URL.createObjectURL(file)
        	  }
        	}

	
	})

	</script>


</body>
</html>