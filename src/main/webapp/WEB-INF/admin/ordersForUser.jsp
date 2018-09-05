<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text" var="resource"/>
<html>
<head>
    <title>orders for user</title>
</head>
<body>
<form method="get" action="/admin/ordersForUser">
    <input type="text" name="login"/><br/>
    <c:set var="enumValues" value="${language == 'en' ? statusEn : statusUa}"/>
    <select name="status">
        <c:forEach items="${enumValues}" var="statusVar" varStatus="varStatus">
            <option>${statusVar}</option>
        </c:forEach>
    </select>
    <input type="submit" value="<fmt:message key="input.data" bundle="${resource}" />" />
</form>
<br>
<table>
    <c:forEach items="${ordersForUser}" var="ordersForUser">
        <tr>
            <td>id ${ordersForUser.getId()} |</td>
            <td>${ordersForUser.getUser().getLogin()} |</td>
            <td><fmt:formatDate value="${ordersForUser.getTimestamp()}" type="both" dateStyle="full"/></td>
            <td>
                <c:forEach var="elem" items="${ordersForUser.getDishAmount()}" varStatus="status">
                    ${language == 'en' ?elem.getKey().getName() : elem.getKey().getNameUa()} ${ elem.getValue()}
                </c:forEach>
            </td>
            <td>${ordersForUser.getSum()}</td>
        </tr>
    </c:forEach>
</table>
<input type="button" value="<fmt:message key="text.mainPage" bundle="${resource}" />" onclick="location.href='/admin/main'"/>

</body>
</html>
