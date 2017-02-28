<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 22.02.17
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello there</h1>

<form action="reg" method="post">
    <div>
        <input type="text" name="login" placeholder="login" required/>
    </div>

    <div>
        <input type="text" name="email" placeholder="email" required/>
    </div>

    <div>
        <input type="password" name="pass" placeholder="password" required/>
    </div>

    <div>
        <input type="submit" value="Регистрация">
    </div>
</form>
</body>
</html>
