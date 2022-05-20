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
</head>
<body>
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
        </div>
    </div>
</body>
</html>