<%--
  Created by IntelliJ IDEA.
  User: Sarala Wimalratne
  Date: 08/03/12
  Time: 04:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Resources</title>
</head>
<body>
<table border=1>
    <tr>
        <c:forEach var="select" items="${resources.select}">
            <td>${select}</td>
        </c:forEach>
        </tr>

    <c:forEach var="resource" items="${resources.resources}">
        <tr>
            <c:forEach var="value" items="${resource.value}">
            <td>${value}</td>
            </c:forEach>
        </tr>
    </c:forEach>
</table>
</body>
</html>