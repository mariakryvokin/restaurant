<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html lang="${language}">
<body>
<c:forEach items="${categories}" var="categories">
    <p>
    <input type="button" value="${language == 'en' ? categories.getName() : categories.getNameUa()}" onclick="location.href='/USER/menu?category_id=${categories.getId()}'"/>
    </p>
</c:forEach>
<br>
<input  type="${role=='USER' ? 'button' : 'hidden'}" value="main page" onclick="location.href='/USER/main'"/>
<br>
<input  type="${role=='ADMIN' ? 'button' : 'hidden'}" value="main page" onclick="location.href='/ADMIN/main'"/>
<br>
</body>
</html>
