<%@ page import="du.spring_test.model.Car" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: DU
  Date: 26.12.2020
  Time: 12:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h4>${user.lastName} ${user.firstName}</h4>
    <p>АВТОПАРК:</p>
    <table border="1px">
        <tr>
            <th bgcolor="#7fffd4">ID</th>
            <th bgcolor="#7fffd4">Марка</th>
        </tr>
        <c:forEach items="${user.cars}" var="car">
            <tr>
                <td>${car.id}
                </td>
                <td>${car.model}
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
