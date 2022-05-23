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
            Your Transactions
        </div>
        <div class="container-body">
            <%
                TransactionDao tdao = Singleton.getTransactionDao();
                List<Transaction> transactions = tdao.getTransactionsByAccno((String)session.getAttribute("accno"));
                for(Transaction t:transactions){
            %>
                <div class="transaction-box">
                    <p>Transaction id: <%=t.getId()%></p>
                    <p>Source Account Number: <%=t.getSrc()%></p>
                    <p>Destination Account Number: <%=t.getDest()%></p>
                    <p>Amount: <%=t.getAmount()%></p>
                    <p>Date: <%=t.getDate()%></p>
                    <p>Time: <%=t.getTime()%></p>
                </div>
            <%}%>
           <button class="form-submit-btn" onclick="location.href='/bank/menu/usermenu.jsp'">Home</button>
        </div>
    </div>
</body>
</html>