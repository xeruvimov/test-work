<!DOCTYPE html>
<html>
<head>
    <title>Registration</title>
</head>
<body>
Registration page
${message}
<form action="${pageContext.request.contextPath}/registration" method="post">
    <div><label> User Name: <input type="text" name="username"/> </label></div>
    <div><label> Password: <input type="password" name="password"/> </label></div>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <div><input type="submit" value="Registration"/></div>
</form>
<a href="${pageContext.request.contextPath}/registration">Add new user</a>
</body>
</html>