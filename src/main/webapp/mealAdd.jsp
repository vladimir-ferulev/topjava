<%--
  Created by IntelliJ IDEA.
  User: vladimir
  Date: 3/6/18
  Time: 9:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Add</title>
    <link rel="stylesheet" type="text/css" href="styles/style.css">
</head>
<body>
<form action="meals" method="post">
    <label>Дата/Время: </label>
    <input type="datetime-local" name="datetime" required><br/>
    <label>Описание: </label>
    <input type="text" name="description" required><br/>
    <label>Калории: </label>
    <input type="number" name="calories" required><br/>
    <button type="submit">Добавить</button>
</form>

</body>
</html>
