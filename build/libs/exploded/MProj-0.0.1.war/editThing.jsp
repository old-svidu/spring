<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 05.03.17
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/editthing" method="post">
    <label for="thing">Thing:</label>
    <input type="text" name="thing" id="thing" value="<%=request.getParameter("thing")%>">
    <label for="price">Price:</label>
    <input type="price" name="price" id="price" value="<%=request.getParameter("price")%>" >
    <label for="prior">Prioritet:</label>
    <input type="text" name="prior" id="prior" value="<%=request.getParameter("prior")%>">

    <input type="submit" value="Edit" formmethod="post">
</form>

</body>
</html>
