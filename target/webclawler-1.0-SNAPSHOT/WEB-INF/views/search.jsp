<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
<p>Znaleziono: </p>
<%--${emails}--%>
<table>
    <tr>
        <th>email</th>
    </tr>
    <c:forEach items="${emails}" var="email">
        <tr>
            <td>${email}</td>
        </tr>
    </c:forEach>
</table>


<form:form modelAttribute="emails">
    <form:input path="email"/>
<input type="submit" value="dodaj do bazy"/>
</form:form>

<%--<a href='<c:url value="/add?param=emails"/>'>Dodaj do bazy</a>--%>
<%@ include file="footer.jsp" %>