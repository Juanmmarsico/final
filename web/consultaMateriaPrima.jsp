<%--
  Created by IntelliJ IDEA.
  User: j.marsicovetere
  Date: 23/11/2017
  Time: 11:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Consulta de materia prima</title>
</head>
<body>
<form method="get" action="/MateriaPrimaServlet">
    <input type="text" id="materiaPrimaNombre" name="materiaPrimaN">
    <button type="submit">consultar</button>
</form>
</body>
</html>
