<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
<p>${emails.size()} email add to database</p>
<%--<p>${links.size()} links</p>--%>

<table>
    <tr>
        <th>email</th>
        <th>url</th>
    </tr>
    <c:forEach items="${emails}" var="email">
        <tr>
            <td>${email}</td><td>${url}</td>
        </tr>
    </c:forEach>
</table>
<%@ include file="footer.jsp" %>
