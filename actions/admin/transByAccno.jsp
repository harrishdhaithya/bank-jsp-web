<%@ page language="java" contentType="text/html"%>
<%@ page import="com.model.Transaction,com.dao.TransactionDao,com.Singleton.Singleton,java.util.List"%>
<%
    response.setHeader("Access-Control-Allow-Origin", "*"); 
    String accno = request.getParameter("accno");
    TransactionDao tdao = Singleton.getTransactionDao();
    List<Transaction> transactions = tdao.getTransactionsByAccno(accno);
    if(!transactions.isEmpty()){
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
<%
    }
}else{
%>
    <div class="transaction-box">
        No Transactions Found
    </div>
<%}%>