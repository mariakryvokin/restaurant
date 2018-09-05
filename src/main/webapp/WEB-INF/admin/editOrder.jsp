<%@ page contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text" var="resource"/>
<html>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<tr>
    <td><fmt:message key="text.name" bundle="${resource}"/> /
        <fmt:message key="text.price" bundle="${resource}"/> /
    </td>
    <td><fmt:message key="amount" bundle="${resource}"/></td>
</tr>
<form method="post" action="/admin/editOrder?orderId=${orderId}">
    <c:forEach items="${editDishSet}" var="element">
        <tr>
            <td>${element.getKey()} /</td>
            <td> ${element.getValue()}</td>
            <td><input type="checkbox" name="item" value="${element.getKey().getId()}"/></td>
            <br/>
        </tr>
    </c:forEach>
    <input type="submit"
           value="<fmt:message key="delete" bundle="${resource}"/>"/>
</form>
<form method="get" action="/admin/editOrder">
    <input type="hidden" name="orderId" value="${orderId}"/>
    <label><fmt:message key="text.name" bundle="${resource}"/></label>
    <select name="dishId">
        <c:forEach items="${dishes}" var="dish">
            <option value="${dish.getId()}">
                    ${language == 'en' ? dish.getName() : dish.getNameUa()}
            </option>
        </c:forEach>
    </select>
    <label><fmt:message key="amount" bundle="${resource}"/></label>
    <input type="number" name="dishAmount" min="1"/>
    <input type="submit" value="<fmt:message key="input.data" bundle="${resource}"/>"/>
</form>
<br/>
<input type="button" value="<fmt:message key="text.back" bundle="${resource}"/>"
       onclick="location.href='${pageContext.request.contextPath}/admin/ordersToConfirm'"/>
</body>
</html>
