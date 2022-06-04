<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Application</title>
    <link rel="stylesheet" href="../css/style.css">
    <script src="../js/auth/adminauth.js"></script>
    <script src="https://accounts.google.com/gsi/client" async defer></script>
</head>
<body>
    <div id="g_id_onload"
         data-client_id="875838647414-pt8dh19mhtm9osjevk8gj0fdukq2s1mt.apps.googleusercontent.com"
         data-callback="handleCredentialResponse">
    </div>
    <%
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
            Admin Login
        </div>
        <div class="container-body">
            <form name="adminform" onsubmit="adminsignin(event);">
                <div class="form-name">
                    <div>Email:</div>
                    <input type="text" name="fname" id="email" class="form-text-box" required>
                </div>
                <div class="form-name">
                    <div>Password:</div>
                    <input type="password" name="password" id="password" class="form-text-box" required>
                </div>
                <input type="submit" class="form-submit-btn" >
            </form>
            <button class="form-submit-btn" onclick="location.href='/bank'">Home</button>
            <div class="g_id_signin google-signin" data-type="standard"></div>
        </div>
    </div>
</body>
</html>