<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Login</title>
</head>
<body>
Login page
<form action="${pageContext.request.contextPath}/login" method="post">
    <div><label> User Name: <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <div><input type="submit" value="Sign In"/></div>
</form>
<a href="${pageContext.request.contextPath}/registration">Add new user</a>
</body>
</html>