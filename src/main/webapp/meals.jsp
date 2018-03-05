<%--
  Created by IntelliJ IDEA.
  User: vladimir
  Date: 3/5/18
  Time: 7:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Meals page</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
<table class="table_meals">
    <tr>
        <td>
            Дата/Время
        </td>
        <td>
            Описание
        </td>
        <td>
            Калории
        </td>
    </tr>
    <c:forEach var="meal" items="${listMeals}">
        <tr class="${meal.exceed ? 'exceed' : 'notexceed'}">
            <td><c:out value="${meal.dateTime.toString().replace('T', ' ')}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
