<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Welcome, ${user.login}</title>
</head>
<body>
    <h3>Личный кабинет</h3>
    <h4>${user.firstName} ${user.lastName}</h4>
    <p><span>ГАРАЖ: </span></p>
    <table border='1px'>
        <tr>
            <th>ID</th>
            <th>Модель</th>
        </tr>
        <c:forEach var="car" items="${user.cars}">
            <tr>
                <td><c:out value="${car.id}" /></td>
                <td><c:out value="${car.model}" /></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>