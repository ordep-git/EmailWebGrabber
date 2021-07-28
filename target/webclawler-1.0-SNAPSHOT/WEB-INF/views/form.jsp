<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Email</title>
</head>
<body>
<h2>szukajka maili</h2>
<form action="/search">
    <label for="url">URL:</label>
    <input id="url" type="text" name="url"/>
    <input type="submit"/>
</form>

<hr>

<form action="/searchkeywords">
    <label for="url">KEYWORDS:</label>
    <input id="keywords" type="text" name="keywords"/>
    <input type="submit"/>
</form>

<%--<form:form  modelAttribute="url" method="post">--%>
<%--    <form:label path="url">Url: </form:label>--%>
<%--    <form:input path="url"/>--%>
<%--    <button type="submit">Start</button>--%>
<%--</form:form>--%>
<%@ include file="footer.jsp" %>

