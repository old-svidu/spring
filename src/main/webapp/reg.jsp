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

<form action="reg"  name="regForm" method="post">
    <div>
        <input type="text" name="login" id="login" placeholder="login" required/>
    </div>

    <div>
        <input type="text" name="email" id="email" placeholder="email" required/>
    </div>

    <div>
        <input type="password" name="pass" id="pass" placeholder="password" required/>
    </div>

    <div>
        <input type="submit" value="Регистрация">
    </div>
</form>

<%--<script type="text/javascript">--%>
    <%--function validate() {--%>
        <%--valid = true;--%>
        <%--var login = document.regForm.login;--%>
        <%--var pass = document.regForm.login;--%>
        <%--var email = document.regForm.login;--%>
        <%--if (login.value == "" || login.value == " ") {--%>
            <%--alert("Пожалуйста заполните поле 'Login'.");--%>
            <%--valid = false;--%>
        <%--}--%>
        <%--if (pass.value == "" || pass.value == " ") {--%>
            <%--alert("Пожалуйста заполните поле 'Pass'.");--%>
            <%--valid = false;--%>
        <%--}--%>
        <%--if (email.value == "" || email.value == " ") {--%>
            <%--alert("Пожалуйста заполните поле 'Email'.");--%>
            <%--valid = false;--%>
        <%--}--%>
        <%--return valid;--%>
    <%--}--%>
<%--</script>--%>
</body>
</html>
