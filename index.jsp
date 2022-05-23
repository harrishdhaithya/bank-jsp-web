<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Application</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <%
        if(session.getAttribute("name")!=null){
            if(session.getAttribute("role").equals("admin")){
                response.sendRedirect("menu/adminmenu.jsp");
            }else{
                response.sendRedirect("menu/usermenu.jsp");
            }
        }
    %>
    <div class="nav-bar">
        <div class="inner-content">
            <img src="img/logo.jpg" id="nav-img" alt="">
        </div>
        <div class="nav-header">
            Banking System
        </div>
    </div>
    <div class="container">
        <div class="container-header">
            Welcome
        </div>
        <div class="container-body">
            <form action="auth/adminlogin.jsp" method="get">
                <button type="submit" class="container-btn">
                    Admin Login
                </button>
            </form>
            <form action="auth/userlogin.jsp" method="get">
                <button type="submit" class="container-btn">
                    User Login
                </button>
            </form>
            <form action="auth/usersignup.jsp" method="get">
                <button type="submit" class="container-btn">
                    User Signup
                </button>
            </form>
        </div>
    </div>
</body>
</html>