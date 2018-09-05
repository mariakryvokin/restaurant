<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html lang="${language}">
<title>Order</title>
<body>
<table>
<tr>
    <th><fmt:message key="text.name"/>/<fmt:message key="text.price"/></th>
    <th><fmt:message key="amount"/></th>
    <th><fmt:message key="total.price"/></th>
</tr>
    <tr>
    <c:forEach var="elem" items="${DishAmountEntrySet}" varStatus="status">
        <td> ${language == 'en' ?elem.getKey().getName() : elem.getKey().getNameUa()} / ${elem.getKey().getPrice()}</td>
        <td>${ elem.getValue()}</td>
        <td>${elem.getValue()*elem.getKey().getPrice()}</td>
        <td>
            <form action="/user/deleteItemFromOrder?idToRemove=${elem.getKey().getId()}" method="post" >
        <input type="submit" value="<fmt:message key="delete"/>"/>
            </form>
        </td>
    </tr>
</c:forEach>
</table>
<label><fmt:message key="total.price"/>: ${totalPrice} </label>
<br>
<c:if test="${ totalPrice > 0 }">
<form method="post" action="/user/sendOrder">
    <input type="submit" value="<fmt:message key="text.sendOrder"/>"/>
</form>
</c:if>
<input type="button" value="<fmt:message key="text.menu"/>" onclick="location.href='${pageContext.request.contextPath}/user/category'"/>
</body>
</html>
