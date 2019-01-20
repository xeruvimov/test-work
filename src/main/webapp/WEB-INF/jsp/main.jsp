<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Main page</title>
</head>
<body>
<div>
    <form action="${pageContext.request.contextPath}/logout" method="post">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <input type="submit" value="Sign Out"/>
    </form>
</div>

<div>
    <form action="${pageContext.request.contextPath}/start" method="get">
        <input type="hidden"
               name="${_csrf.parameterName}"
               value="${_csrf.token}"/>
        <input type="submit" value="Start game"/>
    </form>
</div>

<div>
    <security:authorize access="isAuthenticated()">
        Hello user, <security:authentication property="principal.username"/>!<br/>
        You ratio: <security:authentication property="principal.ratio"/>
    </security:authorize>
</div>

</body>
</html>