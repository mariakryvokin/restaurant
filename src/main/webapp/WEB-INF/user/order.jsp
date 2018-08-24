<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html lang="${language}">
<body>
<table>
<tr>
    <th>Name/Price</th>
    <th>amount</th>
</tr>
    <tr>
    <c:forEach var="elem" items="${DishAmountEntrySet}" varStatus="status">
        <td>${ elem.getKey()}</td>
        <td>${ elem.getValue()}</td>
        <td>
    <form action="/USER/deleteItemFromOrder?idToRemove=${elem.getKey().getId()}" method="post" >
        <input type="submit" value="Delete item"/>
    </form>
        </td>
    </tr>
</c:forEach>
</table>
<label>total price : ${totalPrice} </label>
<br>
<form method="post" action="/USER/sendOrder">
<input type="submit" value="Send order"/>
</form>
<input type="button" value="Menu" onclick="location.href='${pageContext.request.contextPath}/USER/category'"/>
</body>
</html>
