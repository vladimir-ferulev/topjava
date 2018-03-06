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

<a href="meals?action=add">Добавить</a>

<table class="table_meals">
    <tr>
        <td width="40">Id</td>
        <td width="200">Дата/Время</td>
        <td width="200">Описание</td>
        <td width="90">Калории</td>
        <td colspan="2">Action</td>
    </tr>
    <c:forEach var="meal" items="${listMeals}">
        <tr class="${meal.exceed ? 'exceed' : 'notexceed'}">
            <td><c:out value="${meal.id}"/></td>
            <td><c:out value="${meal.dateTime.toString().replace('T', ' ')}"/></td>
            <td><c:out value="${meal.description}"/></td>
            <td><c:out value="${meal.calories}"/></td>
            <td width="70"><a href="meals?action=edit&id=${meal.id}">Edit</a></td>
            <td width="70"><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
