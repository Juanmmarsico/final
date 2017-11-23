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
    <script language="JavaScript">
        function resetButton() {
            document.getElementById("materiaPrimaFormId").reset();
        }
    </script>
</head>
<body>
<h1>hola</h1>
<% Manager manager = Manager.getInstanced();

%>

<p><%= manager.getConsultado() %></p>


<form method="post" action="/MateriaPrimaServlet" name="materiaPrimaForm" id="materiaPrimaFormId">
<input type="text">

    <button><a href="index.jsp">cancelar</a></button>
    <input type="button" onclick="resetButton()" value="Reset">
    <input type="submit" value="comprar">
</form>

</body>
</html>
