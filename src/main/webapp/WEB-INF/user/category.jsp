<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text" var="resource"/>
<html lang="${language}">
<title>Menu</title>
<body>
<c:forEach items="${categories}" var="categories">
    <p>
    <input type="button" value="${language == 'en' ? categories.getName() : categories.getNameUa()}" onclick="location.href='/user/menu?category_id=${categories.getId()}'"/>
    </p>
</c:forEach>
<br>
<input type="button" value="<fmt:message key="text.mainPage" bundle="${resource}"/>" onclick="location.href='/user/main'"/>

</body>
</html>
