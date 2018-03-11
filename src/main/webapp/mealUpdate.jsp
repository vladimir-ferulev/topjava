<%--
  Created by IntelliJ IDEA.
  User: vladimir
  Date: 3/6/18
  Time: 10:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Update</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">

</head>
<body>
<form action="meals" method="post">
    <label>Дата/Время: </label>
    <input type="datetime-local" name="datetime" value="${mealedit.dateTime}" required><br/>
    <label>Описание: </label>
    <input type="text" name="description" value="${mealedit.description}" required><br/>
    <label>Калории: </label>
    <input type="number" name="calories" value="${mealedit.calories}" required><br/>
    <button type="submit">Обновить</button>
    <input type="hidden" name="id" value="${mealedit.id}"><br/>
</form>
</body>
</html>
