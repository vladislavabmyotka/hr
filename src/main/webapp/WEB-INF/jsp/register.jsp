<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Register</title>
    <style>
        <%@include file='../css/bootstrap.min.css' %>
        <%@include file='../css/signin.css' %>
    </style>
</head>
<body>

    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="#">HR System</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExampleDefault">

            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Register<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">Authorization</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <form>
                    <select class="form-control">
                        <option>Русский</option>
                        <option>English</option>
                        <option>Беларускi</option>
                    </select>
                </form>
            </ul>
        </div>
    </nav>

    <div class="container">
        <form class="form-signin">
            <h1 class="form-signin-heading">Please sign in</h1>

            <input type="hidden" name="recognition" value="register">

            <label for="inputLogin" class="sr-only"></label>
            <input name="Username" type="text" id="inputLogin" class="form-control" placeholder="Login" required=""
                   autofocus="">

            <label for="inputPassword" class="sr-only"></label>
            <input name="Password" type="password" id="inputPassword" class="form-control" placeholder="Password"
                   required="">

            <div class="radio">
                <label>Please choose your attachment:</label>
                <label>
                    <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
                    Candidate
                </label>
            </div>
            <div class="radio">
                <label>
                    <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                    Employer
                </label>
            </div>

            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
    </div>

    <%--<script src="../js/jquery.com_jquery-3.2.1.slim.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="../js/bootstrap.min.js"></script>--%>
</body>
</html>
