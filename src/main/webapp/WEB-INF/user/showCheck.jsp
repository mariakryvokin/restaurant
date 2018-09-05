<%@ page  contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text" var="resource"/>
<html>
<title>Check</title>
<body>
<c:forEach items="${allChecks}" var="allChecks">
        <form action="/user/payCheck?checkId=${allChecks.getId()}" method="post">
            <p>
            <fmt:formatDate value="${allChecks.getTimestamp()}" type="both" dateStyle="full"/><br/>${allChecks.getSum()}
            </p>
        <input type="submit" value="<fmt:message key="text.pay" bundle="${resource}"/>"/>
</form>
    <br>
</c:forEach>
</body>
</html>
