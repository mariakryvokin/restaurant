<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text" var="resource"/>
<html lang="${language}">
<title>User main</title>
<body>
<form method="post" action="/logout">
<input type="submit" value="<fmt:message key="logout.form.text" bundle="${resource}"/>"/>
</form>

<h3><fmt:message key="text.hello" bundle="${resource}" /> ${login}</h3>

<input type="button" value="<fmt:message key="text.menu" bundle="${resource}"/>" onclick="location.href='/user/category'"/>
<br>
<input  type="${dishObjMap==null ? 'hidden' : 'button'}" value="<fmt:message key="text.order" bundle="${resource}"/>" onclick="location.href='/user/showOrder'"/>
<br>
<input  type="button" value="<fmt:message key="text.check" bundle="${resource}" /> " onclick="location.href='/user/showCheck'"/>
<br>
<input  type="button" value="<fmt:message key="text.myAllOrders" bundle="${resource}" /> " onclick="location.href='/user/showAllSendedOrders'"/>
</body>
</html>
