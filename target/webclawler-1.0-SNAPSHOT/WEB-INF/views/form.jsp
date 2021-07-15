<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Email</title>
</head>
<body>
<h2>szukajka maili</h2>
<form:form method="post" >
<%--           modelAttribute="emails">--%>

    <form:label path="url">Url: </form:label>
    <form:input path="url"/>

    <button type="submit">Start</button>
</form:form>
<%@ include file="footer.jsp" %>

