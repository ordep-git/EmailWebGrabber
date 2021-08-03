<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<h2>lista maili</h2>
<a href="/list"> lista email - url</a>
<table>
    <tr>
        <th>id</th>
        <th>email</th>
        <th>url</th>
        <th>X</th>
    </tr>
    <c:forEach items="${emails}" var="email">
        <tr>
            <td>${email.id}</td>
            <td>${email.email}</td>
            <td>${email.printUrls()}</td>
            <td>
                <a href="/delete/${email.id}"> Usuń</a>
            </td>
        </tr>
    </c:forEach>
</table>
<%@ include file="footer.jsp" %>
