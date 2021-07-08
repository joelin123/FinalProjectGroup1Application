<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <style type="text/css">
        .regDiv {
height: 300px;
width: 700px;

margin-right: auto;
margin-left: auto;
margin-top: 8%;
margin-bottom: 210px;
padding: 35px 50px 20px 50px; 
background-color: white;
border: 1px solid #D0D0D0;
border-radius: 3% 3% 3% 3%;
}

.sendbutton{
height:40px ;
width: 593px;

border: 1px solid #7B7B7B;
border-radius: 3% 3% 3% 3%;

background-color:#BEBEBE;
margin: 15px 0px 15px 0px;


font-weight:bold;
}

.inputLbox
{
height:40px ;
width: 593px;

margin-bottom:50px;

border: 1px solid #7B7B7B;
border-radius: 3% 3% 3% 3%;

}

.autoinputbutton{
height:40px ;
width: 127px;

border: 1px solid #7B7B7B;
border-radius: 3% 3% 3% 3%;

background-color:#FFFFB9;
margin: 20px 0px 25px 0px;
align-items: center;

font-weight:bold;

}
        
 </style>

<script>
function autoadd(){
    $("#email").val("elena841011@gmail.com");
	

}
</script>

</head>
<body>
    <div class="regDiv">
        <center>
        <form method="POST">
        請輸入註冊時所使用的信箱:
        <input type="text" class="inputLbox" id="email" name="email"  value="" />
        
         <input type="submit" class="sendbutton" value="提繳"><br>
        </form>
        
      <button type="button" class="autoinputbutton" onclick="autoadd()">一鍵輸入</button></center> 
    
</div>
</body>
</html>