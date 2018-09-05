<%@ page  contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text" var="resource"/>
<html>
<title>Orders to confirm</title>
<body>
<form method="get" action="/admin/ordersToConfirm">
<label><fmt:message key="text.login" bundle="${resource}"/></label>
    <input type="text" name="login"/>
    <input type="submit" value="<fmt:message key="input.data" bundle="${resource}" />" />
</form>
<form method="get" action="/admin/ordersToConfirm">
    <label><fmt:message key="text.order.id" bundle="${resource}"/></label>
    <input type="number" name="orderId"/>
    <input type="submit" value="<fmt:message key="input.data" bundle="${resource}" />" />
</form>

<table>
    <tr>
        <td>
            <p><fmt:message key="text.order.id"  bundle="${resource}"/></p>
        </td>
        <td>
            <fmt:message key="text.login" bundle="${resource}"/>
        </td>
        <td>
            <fmt:message key="text.date" bundle="${resource}"/>
        </td>
        <td>
            <fmt:message key="text.dishes" bundle="${resource}"/>
        </td>

    </tr>
    <c:forEach items="${ordersToConfirm}" var="ordersToConfirm">
        <tr>
            <td>id ${ordersToConfirm.getId()} |</td>
            <td>${ordersToConfirm.getUser().getLogin()} |</td>
            <td><fmt:formatDate value="${ordersToConfirm.getTimestamp()}" type="both" dateStyle="full"/></td>
            <td>
            <c:forEach var="elem" items="${ordersToConfirm.getDishAmount()}" varStatus="status">
                ${language == 'en' ?elem.getKey().getName() : elem.getKey().getNameUa()} ${ elem.getValue()}
            </c:forEach>
            </td>
            <td>${ordersToConfirm.getSum()}</td>
            <div hidden="${ordersToConfirm.getStatusEnum().toString().equals("CONFIRMED") ? 'false' : 'true'}">
            <form action="/admin/ordersToConfirm?orderId=${ordersToConfirm.getId()}" method="post">
                <td><input type="submit" value="<fmt:message key="text.confirm" bundle="${resource}"/>"/></td>
            </form>
            <form action="/admin/setDeletedStatus?orderId=${ordersToConfirm.getId()}" method="post">
                <td><input type="submit" value="<fmt:message key="delete" bundle="${resource}" />" /></td>
            </form>
                <form action="/admin/editOrder?orderId=${ordersToConfirm.getId()}" method="post">
                    <td><input type="submit" value="<fmt:message key="text.edit" bundle="${resource}"/>" /></td>
                </form>
            </div>
        </tr>
    </c:forEach>
</table>
<br>
<input  type="button" value="<fmt:message key="text.mainPage" bundle="${resource}"/>" onclick="location.href='/admin/main'"/>
</body>
</html>
