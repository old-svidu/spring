<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 01.03.17
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    if (session.getAttribute("role") == null) {
        response.sendRedirect("/login");
    }
%>



<form action="/logout" method="post">
    <input type="submit" value="Log Out" formmethod="post">
</form>
<h1>Пользователи</h1>
<table border="1" width="100%">
    <div style="height:100px; width:100%;overflow:auto;" >
    <tr>
        <td>id</td>
        <td>login</td>
        <td>pass</td>
        <td>email</td>
        <td>role</td>
        <td>actions</td>
    </tr>
        <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.uid}"/></td>
            <td><c:out value="${user.login}"/></td>
            <td><c:out value="${user.pass}"/></td>
            <td><c:out value="${user.email}"/></td>
            <td><c:out value="${user.role}"/></td>
            <td>
                <a href="/delete?id=${user.uid}" >delete</a>
                <a href="/edit?id=${user.uid}&login=${user.login}&pass=${user.pass}&email=${user.email}" >edit</a>
            </td>
        </tr>
    </c:forEach>
    </div>
</table>

<h1>Покупки</h1>
<table border="1" width="100%">
    <div style="height:100px; width:100%;overflow:auto;" >
    <tr>
        <td>id</td>
        <td>login</td>
        <td>thing</td>
        <td>price</td>
        <td>prioritet</td>
        <td>actions</td>
    </tr>
    <c:forEach items="${things}" var="thing">
        <tr>
            <td><c:out value="${thing.tid}"/></td>
            <td><c:out value="${thing.login}"/></td>
            <td><c:out value="${thing.thing}"/></td>
            <td><c:out value="${thing.price}"/></td>
            <td><c:out value="${thing.prior}"/></td>
            <td>
                <a href="/delthing?id=${thing.tid}" >delete</a>
                <a href="/editthing?id=${thing.tid}&thing=${thing.thing}&price=${thing.price}&prior=${thing.prior}" >edit</a>
            </td>
        </tr>
    </c:forEach>
    </div>
</table>

</body>
</html>
