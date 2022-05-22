<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Application</title>
    <link rel="stylesheet" href="../css/style.css">
    <script src="../js/auth/otp.js"></script>
</head>
<body>
    <%
        if(session.getAttribute("otp")==null){
            response.sendRedirect("/bank");
        }
        if(session.getAttribute("name")!=null){
            if(session.getAttribute("role").equals("admin")){
                response.sendRedirect("/bank/menu/adminmenu.jsp");
            }else{
                response.sendRedirect("/bank/menu/usermenu.jsp");
            }
        }
    %>
    <div class="nav-bar">
        <div class="inner-content">
            <img src="../img/logo.jpg" id="nav-img" alt="">
        </div>
        <div class="nav-header">
            Banking System
        </div>
    </div>
    <div class="container">
        <div class="container-header">
            OTP Verification
        </div>
        <div class="container-body">
            <form onsubmit="verifyOTP(event)">
                <div class="form-name">
                    <div>Enter the 4 digit OTP code from your mail:</div>
                    <input type="text" name="otp" id="otp" class="form-text-box" required>
                </div>
                <input type="submit" class="form-submit-btn">
            </form>
        </div>
    </div>
</body>
</html>