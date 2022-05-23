<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bank Application</title>
    <link rel="stylesheet" href="../css/style.css">
    <script src="../js/auth/usersignup.js"></script>
</head>
<body>
    <div class="loading-screen hide-box" id="loading">
        <p class="loading-text">Loading...</p>
    </div>
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
            User Signup
        </div>
        <div class="container-body">
            <form onsubmit="userSignup(event);">
                <div class="form-name">
                    <div>First Name</div>
                    <input type="text" name="fname" id="fname" class="form-text-box" required>
                </div>
                <div class="form-name">
                    <div>Last Name</div>
                    <input type="text" name="lname" id="lname" class="form-text-box" required>
                </div>
                <div class="form-name">
                    <div>Email</div>
                    <input type="email" name="email" id="email" class="form-text-box" required>
                </div>
                <div class="form-name">
                    <div>Phone</div>
                    <input type="text" name="phone" id="phone" class="form-text-box" required>
                </div>
                <div class="form-name">
                    <div>Initial Deposit</div>
                    <input type="text" name="deposit" id="deposit" class="form-text-box" required>
                </div>
                <div class="form-name">
                    <div>Password</div>
                    <input type="password" name="password" id="password" class="form-text-box" required>
                </div>
                <input type="submit" class="form-submit-btn">
            </form>
            <button class="form-submit-btn" onclick="location.href='/bank'">Home</button>
        </div>
    </div>
</body>
</html>