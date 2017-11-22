<%@ page import="main.supervisor.controllers.Manager" %><%--
  Created by IntelliJ IDEA.
  User: juanmariamarsicovetere
  Date: 22/11/2017
  Time: 18:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Materia prima</title>
</head>
<body>
<h1>hola</h1>
<% Manager manager = Manager.getInstanced();

%>

<p>Current time is: <%=  new java.util.Date() + manager.getConsultado()%></p>
</body>
</html>
