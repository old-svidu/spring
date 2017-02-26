<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 24.02.17
  Time: 21:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>Login</head>
<body>


<div>
    <a href="/reg">Регистрация</a>
    <form action="/login" method="post">
        <label for="login">Login:</label>
        <input type="text" name="login" id="login" value="" placeholder="логин">
        <label for="password">Password:</label>
        <input type="password" name="password" id="password" value="" placeholder="пароль">

        <input type="submit" value="Log In" formmethod="post">
    </form>
</div>
</body>
</html>
