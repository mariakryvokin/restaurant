<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="text"/>
<html>
<title>Menu</title>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>

<body class="m-3">
<div class="row col-md-6">
    <table class="table table-striped table-bordered table-sm">
        <tr>
            <th><fmt:message key="text.name"/></th>
            <th><fmt:message key="price"/></th>
            <th><fmt:message key="amount"/></th>
            <th><fmt:message key="text.toOrder"/></th>
        </tr>
        <c:forEach items="${dishes}" var="dishes">
            <tr>
                <form action="/USER/addToOrder" method="post">
                <td>${language == 'en' ? dishes.getName() : dishes.getNameUa()}</td>
                <td>${dishes.getPrice()}</td>
                <td><input type="number" min="1" name="amount" value="1"/></td>
                <td><input type="submit" value="<fmt:message key="text.toOrder" />"/></td>
                    <input type="hidden"  name="dishId" value="${dishes.getId()}"/>
                </form>
            </tr>
        </c:forEach>
    </table>
</div>
<nav aria-label="Navigation for countries">
    <ul class="pagination">
            <c:forEach begin="0" end="${noOfPages-1}" var="i">
            <c:choose >
                <c:when test="${currentPage eq i}">
                    <li class="page-item active"><a class="page-link">${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="/USER/menu?currentPage=${i}&category_id=${dishes.get(0).getCategoryId()}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

    </ul>
</nav>

<input type="button" value="<fmt:message key="text.menu"/>" onclick="location.href='/USER/category'"/>
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

</body>
</html>
