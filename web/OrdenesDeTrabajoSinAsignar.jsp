<%--
  Created by IntelliJ IDEA.
  User: j.marsicovetere
  Date: 23/11/2017
  Time: 11:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ordenes de trabajo sin asignar</title>
</head>
<body>

<%
for (int i=0;i<10;i++){
    %>
orden de trabajo<%=request.getParameter("OrdenDeTrabajo"+i) %>
<%
}

%>

</body>
</html>
