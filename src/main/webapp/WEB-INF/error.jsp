<%@ page isErrorPage="true"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Exception</title>
</head>
    <body>
    Request from ${pageContext.errorData.requestURI} is failed
    <br/>
    Servlet name: ${pageContext.errorData.servletName}
    <br/>
    Status code: ${pageContext.exception.statusCode}
   <%-- <br/>
    Exception: ${pageContext.exception}--%>
    <br/>
    Message from exception: ${pageContext.exception.message}
    </body>
</html>
