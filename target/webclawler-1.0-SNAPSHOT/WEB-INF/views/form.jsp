<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Email</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
<h2>szukajka maili</h2>
<form action="/search">
    <label for="url">URL:</label>
    <input id="url" type="text" name="url"/>
<%--    <form:errors path="url" cssClass="error"/>--%>
    <input type="submit"/>
</form>

<%--<form:form action="/search">--%>
<%--<form:label path="url">URL: </form:label>--%>
<%--    <form:input path="url"/>--%>
<%--    <form:errors path="url" cssClass="error"/>--%>
<%--<button type="submit">Wyślij</button>--%>
<%--</form:form>--%>

<hr>

<form action="/searchlinks">
    <label for="url">KEYWORDS (google):</label>
    <input id="keywords" type="text" name="keywords"/>
    <input type="submit"/>
</form>

<hr>

<%--<form action="/webcrawler">--%>
<%--    <label for="url">Webcrawler po keywords:</label>--%>
<%--    <input id="webcrawler" type="text" name="webcrawler"/>--%>
<%--    <input type="submit"/>--%>
<%--</form>--%>
<%@ include file="footer.jsp" %>

