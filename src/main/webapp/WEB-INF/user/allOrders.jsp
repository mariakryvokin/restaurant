<%@ page  contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text" var="resource"/>
<html>
<head>
    <title>all user'd orders</title>
</head>
<body>
<table>
<c:forEach items="${allOrders}" var="allorders">
    <tr>
        <td>id ${allorders.getId()} |</td>
        <td><fmt:formatDate value="${allorders.getTimestamp()}" type="both" dateStyle="full"/></td>
        <td>
            <c:forEach var="elem" items="${allorders.getDishAmount()}" varStatus="status">
                ${language == 'en' ? elem.getKey().getName() : elem.getKey().getNameUa()} ${ elem.getValue()}
            </c:forEach>
        </td>
        <td>${allorders.getSum()}</td>
        <td>${language == 'en' ? allorders.getStatusEnum() : allorders.getStatusUaEnum()}</td>
        <td>${language == 'en' ? allorders.getCheck().getStatusEnum() :  allorders.getCheck().getStatusUaEnum()}</td>
    </tr>
</c:forEach>
</table>
</body>
</html>
