<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 24.02.17
  Time: 23:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Authrized!</h1>

<%
    if(session.getAttribute("id")==null){
    response.sendRedirect("/login");
}%>


<table border="1" width="100%">
    <tr>
        <td>id</td>
        <td>name</td>
        <td>lname</td>
        <td>login</td>
        <td>pass</td>
        <td>email</td>
        <td>group</td>
    </tr>
    <c:forEach items="${users}" var="user">
<tr>
    <td><c:out value="${user.uid}"/></td>
    <td><c:out value="${user.name}"/></td>
    <td><c:out value="${user.lname}"/></td>
    <td><c:out value="${user.login}"/></td>
    <td><c:out value="${user.pass}"/></td>
    <td><c:out value="${user.email}"/></td>
    <td><c:out value="${user.group}"/></td>
</tr>
    </c:forEach>
</table >

<table border="1" width="100%">
    <tr>
        <td>id</td>
        <td>login</td>
        <td>thing</td>
        <td>price</td>
        <td>date</td>
        <td>prioritet</td>
    </tr>
    <c:forEach items="${things}" var="thing">
        <tr>
            <td><c:out value="${thing.tid}"/></td>
            <td><c:out value="${thing.login}"/></td>
            <td><c:out value="${thing.thing}"/></td>
            <td><c:out value="${thing.price}"/></td>
            <td><c:out value="${thing.tdate}"/></td>
            <td><c:out value="${thing.prior}"/></td>
        </tr>
    </c:forEach>
</table>

<table border="1" width="100%">
    <tr>
        <td>id</td>
        <td>login</td>
        <td>date</td>
        <td>deposit</td>
        <td>balance</td>
     </tr>
    <c:forEach items="${monies}" var="money">
        <tr>
        <td><c:out value="${money.mid}"/></td>
        <td><c:out value="${money.login}"/></td>
        <td><c:out value="${money.date}"/></td>
        <td><c:out value="${money.deposit}"/></td>
        <td><c:out value="${money.balance}"/></td>
        </tr>
    </c:forEach>

</table>


<table border="1" width="100%">
    <tr>
        <td>id</td>
        <td>login</td>
        <td>thing</td>
        <td>status</td>
        <td>avg</td>
    </tr>
    <c:forEach items="${reports}" var="report">
    <tr>
        <td><c:out value="${report.rid}"/></td>
        <td><c:out value="${report.login}"/></td>
        <td><c:out value="${report.thing}"/></td>
        <td><c:out value="${report.status}"/></td>
        <td><c:out value="${report.avg}"/></td>
    </tr>
    </c:forEach>
</table>

<form action="/logout" method="post">
    <input type="submit" value="Log Out" formmethod="post">
</form>
</body>
</html>
