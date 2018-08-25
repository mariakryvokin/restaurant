<%@ page  contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text" var="resource"/>
<html>
<title>Orders to confirm</title>
<body>
<table>
    <c:forEach items="${ordersToConfirm}" var="ordersToConfirm">
        <tr>
            <td>id ${ordersToConfirm.getId()} |</td>
            <td><fmt:formatDate value="${ordersToConfirm.getTimestamp()}" type="both" dateStyle="full"/></td>
            <td>
            <c:forEach var="elem" items="${ordersToConfirm.getDishAmount()}" varStatus="status">
                ${language == 'en' ?elem.getKey().getName() : elem.getKey().getNameUa()} ${ elem.getValue()}
            </c:forEach>
            </td>
            <td>${ordersToConfirm.getSum()}</td>
            <form action="/ADMIN/ordersToConfirm?orderId=${ordersToConfirm.getId()}" method="post">
                <td><input type="submit" value="<fmt:message key="text.confirm" bundle="${resource}"/>"/></td>
                  <%--  <td><input type="submit" value="delete"/></td>--%>
            </form>
        </tr>
    </c:forEach>
</table>
<br>
<input  type="button" value="<fmt:message key="text.mainPage" bundle="${resource}"/>" onclick="location.href='/ADMIN/main'"/>
</body>
</html>
