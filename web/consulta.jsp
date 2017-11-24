<%@ page import="main.Manager" %><%--
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
String s = manager.getConsultado();
int mpEnBD= manager.getMateriaPrimaEnBD(s);
%>

<p><%= "De la materia prima " + s+ "hay disponible " + mpEnBD + " Si desea registrar una compra, llene el formulario de abajo"%></p>


<form method="post" action="/MateriaPrimaServlet" name="materiaPrimaForm" id="materiaPrimaFormId">
    <h3 name="materiaPrimaNombre" value="<%=s%>"></h3>
    <input name="numeroDeOrden" value="numero de orden" type="number">
    <input type="checkbox" value="fue Compra local" name="localOExterior">
    <select name="lugares" multiple>
        <%
        for (int i =0;i<10;i++){
            %>
        <option name="<%=i %>" value="<%=i %>">lugar <%=i %></option>
        <%
        }
        %>
    </select>

    <button><a href="index.jsp">cancelar</a></button>
    <input type="button" onclick="resetButton()" value="Reset">
    <input type="submit" value="comprar">
</form>

</body>
</html>
