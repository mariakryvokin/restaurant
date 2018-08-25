<%@ page  contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : 'en'}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="text" var="resource"/>
<html>
<body>
<div>
    <form method="get">
        <select id="language" name="language" onchange="submit()">
            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
            <option value="ua"${language == 'ua' ? 'selected' : ''}>Укр</option>
        </select>
    </form>
    <a href="/login"><fmt:message key="login.form.text" bundle="${resource}"/></a>
<%--    <a href="${pageContext.request.contextPath}/register"><fmt:message key="register.from.text"/></a>--%>
    <a href="/register.jsp"><fmt:message key="register.from.text" bundle="${resource}"/></a>

</div>
</body>
</html>
