<%@ page  contentType="text/html; charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text" var="resource"/>
<html>
<title>Admin main</title>
<body>
<form method="post" action="/logout">
<input type="submit" value="<fmt:message key="logout.form.text" bundle="${resource}"/>"/>
</form>
<h3><fmt:message key="text.hello" bundle="${resource}"/> ${login}</h3>

<input type="button" value="<fmt:message key="text.order" bundle="${resource}" />" onclick="location.href='/admin/ordersToConfirm'"/>
<br>
<input type="button" value="<fmt:message key="text.admin.ordersForUser" bundle="${resource}" />" onclick="location.href='/admin/ordersForUser'"/>

</body>
</html>