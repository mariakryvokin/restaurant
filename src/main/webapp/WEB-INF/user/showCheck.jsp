<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html lang="${language}">
<body>
<c:forEach items="${allChecks}" var="allChecks">
        <form action="/USER/payCheck?checkId=${allChecks.getId()}" method="post">
            <p>  ${allChecks.toString()}</p>
        <input type="submit" value="Pay"/>
</form>
    <br>
</c:forEach>
</body>
</html>
