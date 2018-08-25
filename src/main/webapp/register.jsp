<%@ page pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html lang="${language}">
<body>
<h1><fmt:message key="text.regForm" /></h1>
<form method="post"action="${pageContext.request.contextPath}/register">
    <label><fmt:message key="input.login" />:  </label>
        <input type="text" name="login"><br />

    <label><fmt:message key="input.password" />: </label>
        <input type="password" name="password"><br/>

    <button type="submit"><fmt:message key="input.data" /></button>
</form>
</body>
</html>
