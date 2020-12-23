<%--
  Created by IntelliJ IDEA.
  User: DU
  Date: 20.12.2020
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello</title>
</head>
<body>
    <h1>${message}</h1>
    <h1>${message2}</h1>
    <form action="/spring_test_war/welcome/hello_post" method="post">
        <input type="submit" value="post" name="post" />
    </form>
</body>
</html>
