
$(function () {
    var lflag1 = false;
    var lflag2 = false;
    var lflag3 = false;
    var lflag4 = false;
    var lflag5 = false;
    var lflag6 = false;

    document.getElementById("idAccount").onblur = checkAcc;
    function checkAcc() {
        let theAccObj = document.getElementById("idAccount");
        let theAccObjVal = theAccObj.value;
        let theAccObjValLen = theAccObjVal.length;
        let span = document.getElementById("AccountResult");
        let flag1 = false, flag2 = false; flag3 = false;
        if (theAccObjVal == "") {
            span.innerHTML = "帳號不可為空白";
            
        }
        else if (theAccObjValLen < 6) {
            span.innerHTML = "至少須六個字以上";
            
        }
        else if (theAccObjVal.charAt(0).toUpperCase() < "A" || theAccObjVal.charAt(0).toUpperCase() > "Z") {
            span.innerHTML = "字首非英文字母";
           
        }
        else {
            for (let i = 1; i < theAccObjValLen; i++) {
                let ch = theAccObjVal.charAt(i).toUpperCase();
                let nm = theAccObjVal.charAt(i);
                let cch = theAccObjVal.charCodeAt(i);

                if (cch >= 0x4E00 && cch <= 0x9FA5) {
                    flag1 = false;
                    break;
                } else {
                    flag1 = true;
                }
                if (nm >= "0" && nm <= "9") {
                    flag2 = true;
                }
                if ((nm >= "0" && nm <= "9") || (ch >= "A" && ch <= "Z")) {
                    flag3 = true;
                } else {
                    flag3 = false;
                    break;
                }
            }

            if (flag1 && flag2 && flag3) {
                span.innerHTML = "正確";
               
                lflag1 = true;

            }
            else if (flag2 == false) {
                span.innerHTML = "正確格式為英數字混合";
              
            }
            else {
                span.innerHTML = "帳號不可包含中文及特殊字元";
               

            }

        }
    }

    document.getElementById("idPassword").onblur = checkPwd;
    function checkPwd() {
        let thePwdObj = document.getElementById("idPassword");
        let thePwdObjVal = thePwdObj.value;
        let thePwdObjValLen = thePwdObjVal.length;
        let span = document.getElementById("PasswordResult");
        let flag1 = false, flag2 = false, flag3 = false;
        if (thePwdObjVal == "") {
            span.innerHTML = "密碼不可為空白";
           
        }
        else if (thePwdObjValLen < 6) {
            span.innerHTML = "至少須六個字以上";
           
        }
        else {
            for (let i = 0; i < thePwdObjValLen; i++) {
                let ch = thePwdObjVal.charAt(i).toUpperCase();
                let nm = thePwdObjVal.charAt(i);
                let sc = thePwdObjVal.charCodeAt(i);

                if (ch >= "A" && ch <= "Z") {
                    flag1 = true;
                }
                if (nm >= "0" && nm <= "9") {
                    flag2 = true;
                }
                if (sc == 33 || sc == 35 || sc == 36 || sc == 37 || sc == 64 || sc == 38 || sc == 94 || sc == 42) {
                    flag3 = true;
                }
                if (flag1 && flag2 && flag3) { break; }
            }
            if (flag1 && flag2 && flag3) {
                span.innerHTML = "正確";
               
                lflag2 = true;
            }
            else {
                span.innerHTML = "密碼需包含至少一個英文字母、數字及特殊字元[!@#$%^&*]";
               
            }
        }
    }

    document.getElementById("idName").onblur = checkName;
    function checkName() {
        let theNameObj = document.getElementById("idName");
        let theNameObjVal = theNameObj.value;
        let span = document.getElementById("NameResult");
        let theNameObjValLen = theNameObjVal.length;
        if (theNameObjVal == "") {
            span.innerHTML = "姓名不可為空白";
           
        }
        else if (theNameObjValLen < 2) {
            span.innerHTML = "姓名至少兩個字以上";
           
        }
        else if (theNameObjValLen >= 2) {
            for (let i = 0; i < theNameObjValLen; i++) {
                let ch = theNameObjVal.charCodeAt(i);
                if (ch < 0x4E00 || ch > 0x9FA5) {
                    span.innerHTML = "請輸入中文本名";
                   
                    break;
                }
                else {
                    lflag3 = true;
                    span.innerHTML = "正確";
                    
                }
            }
        }

    }

    document.getElementById("idPhone").onblur = checkphone;
    function checkphone() {
        const regexphone = /(\d{2,3}-?|\(\d{2,3}\))\d{3,4}-?\d{4}/g;

        if ($("#idPhone").val().match(regexphone) != null) {
          
            $("#PhoneResult").text("正確");
            lflag4 = true;

        } else {
           
            $("#PhoneResult").text("電話格式錯誤");
        }
        if ($("#idPhone").val() == "") {
            
            $("#PhoneResult").text("電話不可為空白");
        }


    }

    document.getElementById("idEmail").onblur = checkemail;
    function checkemail() {

        if ($("#idEmail").val() == "") {
          
            $("#EmailResult").text("Email不可為空白");
        } else {

            for (let i = 0; i < $("#idEmail").val().length; i++) {
                let sc = $("#idEmail").val().charCodeAt(i);
                if (sc == 64) {
                   
                    $("#EmailResult").text("正確");
                    lflag5 = true;
                    break;
                } else {
                  
                    $("#EmailResult").text("請輸入正確格式(包含@)");
                }
            }
        }
    }

    document.getElementById("idAddress").onblur = checkaddress;
    function checkaddress() {

        if ($("#idAddress").val() == "") {
           
            $("#AddressResult").text("地址不可為空白");
        } else {
            
            $("#AddressResult").text("正確");
            lflag6 = true;
        }
    }


    $("#submitform").submit(function (event) {
        if (!(lflag1 && lflag2 && lflag3 && lflag4 && lflag5 && lflag6)) {
            window.alert("請輸入正確資料");
            event.preventDefault();
        }


    });



})
