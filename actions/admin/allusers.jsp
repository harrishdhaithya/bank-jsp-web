<%@ page language="java" contentType="text/html"%>
<%@ page import="com.model.User,com.dao.UserDao,com.Singleton.Singleton,java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Application</title>
    <link rel="stylesheet" href="../../css/style.css">
    
</head>
<body>
    <%
        if(session.getAttribute("name")==null){
            response.sendRedirect("/bank");
        }else{
            if(session.getAttribute("role").equals("user")){
                response.sendRedirect("/bank/menu/usermenu.jsp");
            }
        }
    %>
    <div class="nav-bar">
        <div class="inner-content">
            <img src="../../img/logo.jpg" id="nav-img" alt="">
        </div>
        <div class="nav-header">
            Banking System
        </div>
        <form action="/bank/logout" method="post" class="logout-form">
            <button type="submit" class="logout-btn">logout</button>
        </form>
    </div>
    <div class="container">
        <div class="container-header">
            All Users
        </div>
        <div class="container-body">
           <%
                UserDao udao = Singleton.getUserDao();
                List<User> users = udao.getAllUsers();
                for(User user:users){
           %>
                <div class="user-box">
                    <p>Account Number: <%=user.getAccno()%></p>
                    <p>Name: <%=user.getFname()+" "+user.getLname()%></p>
                    <p>Email: <%=user.getEmail()%></p>
                    <p>Phone Number: <%=user.getPhone()%></p>
                    <p>Balance: <%=user.getBalance()%></p>
                </div>
           <%}%>
        </div>
    </div>
</body>
</html>