<%@ include file="header.jsp" %>
<body>
<p>search url</p>
<p>${emails.size()} email add to database</p>
<%--<p>${links.size()} links</p>--%>
<table>
    <tr>
        <th>email</th>
        <th>url</th>
    </tr>
    <c:forEach items="${emails}" var="email">
        <tr>
                    <td>${email}</td>
                    <td>${url}</td>
        </tr>
    </c:forEach>
</table>
<%@ include file="footer.jsp" %>
