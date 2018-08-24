<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html lang="${language}">
<body>
    <table>
        <c:forEach items="${ordersToConfirm}" var="ordersToConfirm">
            <tr>
                <form action="/ADMIN/ordersToConfirm?orderId=${ordersToConfirm.getId()}" method="post">
                    <td>id ${ordersToConfirm.getId()} | ${ordersToConfirm.toString()}</td>
                    <td><input type="submit" value="confirm"/></td>
                  <%--  <td><input type="submit" value="delete"/></td>--%>
                </form>
            </tr>
        </c:forEach>
    </table>
    <br>
    <input  type="button" value="main page" onclick="location.href='/ADMIN/main'"/>
</body>
</html>
