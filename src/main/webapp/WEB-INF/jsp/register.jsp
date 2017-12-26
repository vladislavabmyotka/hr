<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <style>
        <%@include file='../css/bootstrap.min.css' %>
        <%@include file='../css/signin.css' %>
    </style>
</head>
<body>
<div class="container">
    <form class="form-signin">
        <h1 class="form-signin-heading">Please sign in</h1>

        <input type="hidden" name="recognition" value="register">

        <label for="inputLogin" class="sr-only"></label>
        <input name="Login" type="text" id="inputLogin" class="form-control" placeholder="Login" required=""
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
</body>
</html>
