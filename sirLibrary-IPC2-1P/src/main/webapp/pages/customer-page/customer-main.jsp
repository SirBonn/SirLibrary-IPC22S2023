<%-- 
    Document   : customer-main
    Created on : 2 sept. 2023, 4:16:22
    Author     : sirbon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <c:if test="${sessionScope.userLogged == null}">
            <meta http-equiv="refresh" content="0;url=${pageContext.request.contextPath}/login.jsp">        
        </c:if>
        <jsp:include page="../common/common-resources.jsp"/>

        <title>SirLibrary</title>
    </head>
    <body>
        <jsp:include page="customer-body.jsp"/>
    </body>
</html>