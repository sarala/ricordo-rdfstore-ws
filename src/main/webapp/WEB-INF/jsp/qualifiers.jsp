<%--
  Created by IntelliJ IDEA.
  User: sarala
  Date: 28/06/12
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Qualifiers</title>
</head>
<body>
<table border=1>
    <thead><tr>
        <th>BioQualifier NS</th>
        <th>BioQualifiers</th>
    </tr></thead>
    <c:forEach var="qualifier" items="${qualifiers.qualifiers}">
        <tr>
            <td>${qualifier.qualifierURL}</td>
            <td>${qualifier.qualifier}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>