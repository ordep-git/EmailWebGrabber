<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Email</title>
</head>
<body>
<table>
    <tr>
        <th>id</th>
        <th>email</th>
    </tr>
    <c:forEach items="${emails}" var="email">
        <tr>
            <td>${email.id}</td>
            <td>${email.email}</td>
            <td>
                <a href="/delete/${email.id}">Usuń</a>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="footer.jsp" %>
