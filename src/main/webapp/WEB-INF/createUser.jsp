<%--
  Created by IntelliJ IDEA.
  User: DU
  Date: 26.12.2020
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создание пользователя</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/createUser" method="post">
        <p>
            Login: <input name="login">
        </p>
        <p>
            First name: <input name="firstName">
        </p>
        <p>
            Last name: <input name="lastName">
        </p>
        <p>
            <input type="submit">
        </p>
    </form>
</body>
</html>
