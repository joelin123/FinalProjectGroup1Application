<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.1.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/javascript/j_frontfiltercu2.js"></script>
<meta charset="UTF-8">
<title>CRUD</title>
<%-- <link rel='stylesheet' href="<c:url value='/css/styles.css' />" --%>
<!-- 	type="text/css" /> -->
<script>



</script>
</head>

<body>

<div align="center">
		<fieldset style="width: 1000px;">
			<legend align="center">新增會員</legend>


				<form:form id="submitform" action="./exciseInsert.co" method="post" modelAttribute="customer"
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
						<td><form:input id="idAccount" path="account" type="text" size="10"
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
								maxlength="20" placeholder="請輸入信箱" />
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
				<form:button value="Send" id="sendData">確定新增</form:button>
			</form:form>
	</fieldset>


<script>
	
	window.addEventListener("load", function(){
		
		var dupflag1 = false;
		var dupflag2 = false;
		var dupflag3 = false;

		//檢查帳號信箱電話重複的controller mapping
		const targetlink1 = '<c:url value="/Ajax/Elena/checkduplicateaccount"/>';
		const targetlink2 = '<c:url value="/Ajax/Elena/checkduplicatecellphone"/>';
		const targetlink3 = '<c:url value="/Ajax/Elena/checkduplicateemail"/>';
		
		
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