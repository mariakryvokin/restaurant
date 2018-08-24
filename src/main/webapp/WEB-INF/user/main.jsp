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

<h1>Hello user!</h1><h3>Welcome ${login}</h3>

<input type="button" value="<fmt:message key="text.menu"/>" onclick="location.href='/USER/category'"/>
<br>
<input  type="${dishObjMap==null ? 'hidden' : 'button'}" value="Order" onclick="location.href='/USER/showOrder'"/>
<br>
<input  type="button" value="Check" onclick="location.href='/USER/showCheck'"/>
</body>
</html>
