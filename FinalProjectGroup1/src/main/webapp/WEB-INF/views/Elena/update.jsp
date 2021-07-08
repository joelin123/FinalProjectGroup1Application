<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix='form' uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<script>

window.addEventListener("load", function(){
	
    //預覽圖片
    imgInp.onchange = evt => {
   	  const [file] = imgInp.files
   	  if (file) {
   	    blah.src = URL.createObjectURL(file)
   	  }
   	}

	
})

</script>
<meta charset="UTF-8">
<style>


</style>
<title>使用者資訊更新頁面</title>
</head>
<body>


<div class=regDiv>

<fieldset>


	<legend >變更會員資料</legend>
	<form:form id = "sregform" method="POST" modelAttribute="CustomerInfo" enctype='multipart/form-data'> 
	 

姓名:&nbsp;&nbsp;<form:input class="inputLbox" type = "text" id="idname" path = "customerName"/>
<img id="snameimg"><span id="snamesp"></span>
&nbsp;&nbsp;<p class="rule">(1.不可空白，2.至少兩個字以上，3.必須全部為中文字)</p>

帳號:&nbsp;&nbsp;<form:input class="inputLbox" type = "text" id="idaccount" path = "account"/>
<img id="sphoneimg"><span id="sphonesp"></span>
<p class="rule">(不可空白)</p>

密碼:&nbsp;&nbsp;<form:input class="inputLbox" type = "text" id="idpassword" path = "password"/>
<img id="sphoneimg"><span id="sphonesp"></span>
<p class="rule">(不可空白)</p>
                
Email:&nbsp;&nbsp;<form:input class="inputLbox" type = "text" id="idemail" path = "email"/>
<img id="sphoneimg"><span id="sphonesp"></span>
<p class="rule">(不可空白)</p>

生日:&nbsp;&nbsp;<form:input class="inputLbox" type = "date" id="idbirthday" path = "birthday" value = "${CustomerInfo.birthday}" placeholder="生日"/>
<img id="saddressimg"><span id="saddresssp"></span>
<p class="rule">(不可空白)</p>

<form:label id= "idgender" path="gender">性別:&nbsp;&nbsp;${CustomerInfo.gender}</form:label>
<form:radiobutton path="gender" value="男" label="男"/> <form:radiobutton path="gender" value="女" label="女" /><br>
<br>

電話:&nbsp;&nbsp;<form:input class="inputLbox" type = "text" id="idcellphone" path = "cellphone" value = "${CustomerInfo.cellphone}"/>
<img id="saddressimg"><span id="saddresssp"></span>
<p class="rule">(不可空白)</p>

地址:&nbsp;&nbsp;<form:input class="inputLbox" type = "text" id="idaddress" path = "address" value = "${CustomerInfo.address}"/>
<img id="saddressimg"><span id="saddresssp"></span>
<p class="rule">(不可空白)</p>
<hr>
照片:&nbsp;&nbsp;<img id="blah" width='150' height='200' src= "${CustomerInfo.pictureString} "><br>
<!-- <img id="blah" src="#" alt="your image" width='150' height='200' /> -->
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<form:input id="imgInp" path="customerImage" type='file' />
<img id="saddressimg"><span id="saddresssp"></span>



<input class="sendbutton" type = "submit" value = "提繳"><br>


</form:form>
</fieldset>
</div>
</body>
</html>