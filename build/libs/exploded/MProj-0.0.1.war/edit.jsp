<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 04.03.17
  Time: 21:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="/edit" method="post">
    <label for="login">Login:</label>
    <input type="text" name="login" id="login" value="<%=request.getParameter("login")%>">
    <label for="password">Password:</label>
    <input type="password" name="password" id="password" value="" placeholder="pass" >
    <label for="email">Password:</label>
    <input type="text" name="email" id="email" value="<%=request.getParameter("email")%>">

    <input type="submit" value="Edit" formmethod="post">
</form>

</body>
</html>
