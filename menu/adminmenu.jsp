<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Application</title>
    <link rel="stylesheet" href="../css/style.css">
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
            <img src="../img/logo.jpg" id="nav-img" alt="">
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
            Welcome
        </div>
        <div class="container-body">
            <form action="/bank/actions/admin/allusers.jsp" method="get">
                <button type="submit" class="container-btn">
                    See All Users
                </button>
            </form>
            <form action="/bank/actions/admin/searchuser.jsp" method="get">
                <button type="submit" class="container-btn">
                    Search User
                </button>
            </form>
            <form action="/bank/actions/admin/alltransactions.jsp" method="get">
                <button type="submit" class="container-btn">
                    View All Transactions
                </button>
            </form>
            <form action="/bank/actions/admin/transactionwithdate.jsp" method="get">
                <button type="submit" class="container-btn">
                    Search Transaction By Date
                </button>
            </form>
            <form action="/bank/actions/admin/transwithaccno.jsp" method="get">
                <button type="submit" class="container-btn">
                    Search Transaction By Account Number
                </button>
            </form>
        </div>
    </div>
</body>
</html>