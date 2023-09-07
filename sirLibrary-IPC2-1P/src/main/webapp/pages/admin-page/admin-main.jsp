<%-- 
    Document   : admin-main
    Created on : 30 ago. 2023, 23:49:07
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
        <title>Library Administration</title>
    </head>
    <body>
        <jsp:include page="admin-body.jsp"/>
    </body>
</html>