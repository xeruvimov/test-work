<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Game</title>
</head>
<body>
<p>Game start. Please input guess number</p>
<a href="${pageContext.request.contextPath}/main">Main page</a>
${message}
<form action="${pageContext.request.contextPath}/start" method="post">
    <div><label> You number: <input type="text" name="number" maxlength="4" minlength="4"/> </label></div>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <div><input type="submit" value="Check"/></div>
</form>
<c:forEach items="${tried}" var="entry">
    ${entry.key} - ${entry.value}<br>
</c:forEach>

<form action="${pageContext.request.contextPath}/start" method="get">
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <div><input type="submit" value="New game"/></div>
</form>
</body>
</html>
