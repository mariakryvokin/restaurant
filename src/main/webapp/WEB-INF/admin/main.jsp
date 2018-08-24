<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html lang="${language}">
<body>
<form method="post" action="/logout">
<input type="submit" value="<fmt:message key="logout.form.text"/>"/>
</form>
<h1>Hello admin!</h1><h3>Welcome ${login}</h3>

<input type="button" value="List of orders" onclick="location.href='/ADMIN/ordersToConfirm'"/>
<%--<br>
<input  type="button" value="Add dish to menu" onclick="location.href='/addDish'"/>
<br>
<input  type="button" value="Add admin" onclick="location.href='/addAdmin'"/>
<br>
<input  type="button" value="Edit menu" onclick="location.href='/category'"/>--%>
</body>
</html>