<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="lang.app" />
<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hello</title>
    <style>
        <%@include file='WEB-INF/css/bootstrap.min.css' %>
        <%@include file='WEB-INF/css/signin.css' %>
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
                    <a class="nav-link" href="#">Authorization<span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="register">Register</a>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <form>
                    <select class="form-control" title="language" id="language" name="language" onchange="submit()">
                        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Русский</option>
                        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                    </select>
                </form>
            </ul>
        </div>
    </nav>

    <div class="container">
        <form class="form-signin" name="Login" action="FrontController" method="post">
            <h1 class="form-signin-heading"><fmt:message key="nav.authorization" />Please log in to the system</h1>

            <input type="hidden" name="recognition" value="autorization">

            <label for="inputLogin" class="sr-only"></label>
            <input name="Username" type="text" id="inputLogin" class="form-control" placeholder="Login" required=""
                   autofocus="">

            <label for="inputPassword" class="sr-only"></label>
            <input name="Password" type="password" id="inputPassword" class="form-control" placeholder="Password"
                   required="" autocomplete="no">

            <button class="btn btn-lg btn-primary btn-block" type="submit"><fmt:message key="nav.register" />Log in</button>
            <button class="btn btn-lg btn-primary btn-block" type="reset">Reset</button>
        </form>

    </div>

    <%--<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="WEB-INF/js/bootstrap.min.js"></script>--%>
</body>
</html>