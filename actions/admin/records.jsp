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
    <script src="../../js/admin/records.js"></script>
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
    <div class="loading-screen hide-box" id="loading">
        <p class="loading-text">Loading...</p>
    </div>
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
            Extract Data
        </div>
        <div class="search-box">
            <form name="searchuser" onsubmit="extractDate(event);">
                <div class="form-name">
                    <div>Filter </div>
                    <select name="filter" id="filter" class="form-text-box" onchange="changeFilter();">
                        <option value="none">None</option>
                        <option value="date">Date</option>
                        <option value="accno">Account Number</option>
                    </select>
                </div>
                <div class="form-name">
                    <div>Format</div>
                    <select name="format" id="format" class="form-text-box">
                        <option value="pdf">PDF</option>
                        <option value="xls">Spread Sheet</option>
                    </select>
                </div>
                <div class="hide-box" id="date-box">
                    <div class="form-name">
                        <div>From</div>
                        <input type="date" name="from" id="from" class="form-text-box">
                    </div>
                    <div class="form-name">
                        <div>To</div>
                        <input type="date" name="to" id="to" class="form-text-box">
                    </div>
                </div>
                <div class="form-name hide-box" id="acc-box">
                    <div>Account Number</div>
                    <input type="text" name="accno" id="accno" class="form-text-box">
                </div>
                <input type="submit" value="Extract" class="form-submit-btn">
            </form>
        </div>
        <div class="container-body hide-box" id="result"></div>
        <button class="form-submit-btn" onclick="location.href='/bank/menu/adminmenu.jsp'">Home</button>
    </div>
</body>
</html>