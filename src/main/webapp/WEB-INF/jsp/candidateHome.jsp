<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="lang.app" />
<!DOCTYPE html>
<html lang="${language}">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="candidate.home" /></title>
    <style>
        <%@include file='../css/bootstrap.min.css' %>
        <%@include file='../css/main.css' %>
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
        <a class="navbar-brand" href="#">HR System. ${pageContext.session.getAttribute("role").login}</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault"
                aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarsExampleDefault">

            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#"><fmt:message key="home" />
                        <span class="sr-only">(current)</span></a>
                </li>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="candidate_add">
                    <li class="nav-item">
                        <button type="submit" class="btn btn-link nav-link cursor"><fmt:message key="candidate.add" />
                        </button>
                    </li>
                </form>
                <form action="FrontController" method="post">
                    <input type="hidden" name="command" value="candidate_view_edit">
                    <li class="nav-item">
                        <button type="submit" class="btn btn-link nav-link cursor">
                            <fmt:message key="candidate.view.edit" /></button>
                    </li>
                </form>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown personal-account">
                    <a href="#" data-toggle="dropdown" class="dropdown-toggle">
                        <fmt:message key="general.personal.cabinet" /><b class="caret"></b></a>
                    <ul id="menu1" class="dropdown-menu">
                        <li><a href="editAccount"><fmt:message key="general.personal.cabinet.edit" /></a></li>
                        <li><a href="deleteAccount"><fmt:message key="general.personal.cabinet.delete" /></a></li>
                    </ul>
                </li>
                <li>
                    <form class="margin">
                        <select class="form-control" title="language" id="language" name="language"
                                onchange="location=this.options[this.selectedIndex].value">
                            <option value="http://localhost:8080/hr/candidateHome?language=ru"
                            ${language == 'ru' ? 'selected' : ''}>Русский</option>
                            <option value="http://localhost:8080/hr/candidateHome?language=en"
                            ${language == 'en' ? 'selected' : ''}>English</option>
                        </select>
                    </form>
                </li>
                <li>
                    <form class="form-signin" action="FrontController" method="post">
                        <input type="hidden" name="command" value="">
                        <button class="btn btn-outline-success my-2 my-sm-0" type="submit"><fmt:message key="exit" />
                        </button>
                    </form>
                </li>
            </ul>
        </div>
    </nav>

    <main role="main">

        <!-- Main jumbotron for a primary marketing message or call to action -->
        <div class="jumbotron">
            <div class="container">
                <h1 class="display-3">Hello, ${pageContext.session.getAttribute("role").login}!</h1>
                <p><fmt:message key="home.main.title" /></p><br/>
                <h6 class="form-signin-heading error">${errorMessage}</h6>
            </div>
        </div>

        <div class="container">
            <!-- Example row of columns -->
            <div class="row">
                <div class="col-md-4">
                    <h2><fmt:message key="candidate.home.8second" /></h2>
                    <p><fmt:message key="candidate.home.8second.main" /></p>
                </div>
                <div class="col-md-4">
                    <h2><fmt:message key="candidate.home.want.rich" /></h2>
                    <p><fmt:message key="candidate.home.want.rich.main" /></p>
                </div>
                <div class="col-md-4">
                    <h2><fmt:message key="candidate.home.motivation" /></h2>
                    <p><fmt:message key="candidate.home.motivation.main" /></p>
                </div>
            </div>

            <hr>

        </div>

    </main>
    <c:import url="/WEB-INF/jsp/footer.jsp"/>
    <script>
        <%@include file='../js/bootstrapDropdown.js' %>
    </script>
</body>
</html>
