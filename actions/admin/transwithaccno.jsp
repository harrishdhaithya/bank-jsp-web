<%@ page language="java" contentType="text/html"%>
<%@ page import="com.model.Transaction,com.dao.TransactionDao,com.Singleton.Singleton,java.util.List"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Application</title>
    <link rel="stylesheet" href="../../css/style.css">
    <script src="../../js/admin/transactionbyaccno.js"></script>
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
            Search Transactions
        </div>
        <div class="search-box">
            <form name="searchtransaction" onsubmit="transByAccno(event);">
                <div class="form-name">
                    <div>Account Number: </div>
                    <input type="text" name="accno" id="accno" class="form-text-box" required>
                </div>
                <input type="submit" value="Search" class="form-submit-btn">
            </form>
        </div>
        <div class="container-body hide-box" id="result"></div>
    </div>
</body>
</html>