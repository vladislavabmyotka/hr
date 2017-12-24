<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
</head>
<body>
<form name="Login" action="FrontController" method="post">
    <p align="center">
        Username <input type="text" name="Username" value="" size="20"/>
        <br/>
        <br/>
        Password <input type="password" name="Password" value="" size="20"/>
        <br/>
        <br/>
        <input type="submit" name="Submit" value="Submit"/>
        <input type="reset" name="Reset" value="Reset"/>
    </p>
</form>
</body>
</html>