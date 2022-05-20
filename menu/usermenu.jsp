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
            if(session.getAttribute("role").equals("admin")){
                response.sendRedirect("/bank/menu/adminmenu.jsp");
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
            Welcome <%=session.getAttribute("name")%>
        </div>
        <div class="container-body">
            <form action="/bank/actions/user/viewBalance.jsp" method="get">
                <button type="submit" class="container-btn">
                    View Balance
                </button>
            </form>
            <form action="/bank/actions/user/makeTransaction.jsp" method="get">
                <button type="submit" class="container-btn">
                    Make Transaction
                </button>
            </form>
            <form action="/bank/actions/user/withdraw.jsp" method="get">
                <button type="submit" class="container-btn">
                    Make Withdrawal
                </button>
            </form>
            <form action="/bank/actions/user/viewTransactions.jsp" method="get">
                <button type="submit" class="container-btn">
                    View My Transactions
                </button>
            </form>
        </div>
    </div>
</body>
</html>