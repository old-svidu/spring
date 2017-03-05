
<%@ page import="spring.common.utils.Flags" %><%--
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


<%
    if (session.getAttribute("id") == null) {
        response.sendRedirect("/login");
    }
%>
<div class="topMenu">
    <form action="/logout" method="post">
        <input type="submit" value="Log Out" formmethod="post">
    </form>
    <form action="/home" method="get" >
        <input type="submit" value="<%=Flags.flag?"Off":"On"%>" onclick="Flags.changeFlag()">
    </form>
</div>
<div class="menu">




<table border="1" width="100%">
    <tr>
        <td>id</td>
        <td>login</td>
        <td>deposit</td>
        <td>balance</td>
        <td>note</td>
    </tr>
    <c:forEach items="${monies}" var="money">
        <tr>
            <td><c:out value="${money.mid}"/></td>
            <td><c:out value="${money.login}"/></td>
            <td><c:out value="${money.deposit}"/></td>
            <td><c:out value="${money.balance}"/></td>
            <td><c:out value="${money.note}"/></td>
        </tr>
    </c:forEach>

</table>


<%--<table border="1" width="100%">--%>
    <%--<tr>--%>
        <%--<td>id</td>--%>
        <%--<td>login</td>--%>
        <%--<td>thing</td>--%>
        <%--<td>status</td>--%>

    <%--</tr>--%>
    <%--<c:forEach items="${reports}" var="report">--%>
        <%--<tr>--%>
            <%--<td><c:out value="${report.rid}"/></td>--%>
            <%--<td><c:out value="${report.login}"/></td>--%>
            <%--<td><c:out value="${report.thing}"/></td>--%>
            <%--<td><c:out value="${report.status}"/></td>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
<%--</table>--%>
</div>




<style>

    .topMenu {
        padding:10px;
        color:#fff;
        font-weight:bold;
        position:fixed;
        top:0;
        left:50px; right:50px;
        text-align:right;


        -moz-opacity: 0.50;
        opacity: 0.50;


        -webkit-transition:All 1s ease;
        -moz-transition:All 1s ease;
        -o-transition:All 1s ease;

    }

    .menu {
        position: absolute;
    }
</style>

<%--<script>--%>
    <%--function changeFlag() {--%>
        <%--<% Flags.flag=!Flags.flag; %>--%>
    <%--}--%>
<%--</script>--%>



</body>
</html>
