<%@ page language="java" contentType="text/html"%>
<%@ page import="com.model.User,com.dao.UserDao,com.Singleton.Singleton,java.util.List"%>
<%
    String accno = request.getParameter("accno");
    UserDao udao = Singleton.getUserDao();
    User user = udao.getUserByAccno(accno);
    if(user!=null){
%>
<div class="user-box">
    <p>Account Number: <%=user.getAccno()%></p>
    <p>Name: <%=user.getFname()+" "+user.getLname()%></p>
    <p>Email: <%=user.getEmail()%></p>
    <p>Phone Number: <%=user.getPhone()%></p>
    <p>Balance: <%=user.getBalance()%></p>
</div>
<%
}
else{
%>
<div class="user-box">
    <p>No Users Found</p>
</div>
<%}%>