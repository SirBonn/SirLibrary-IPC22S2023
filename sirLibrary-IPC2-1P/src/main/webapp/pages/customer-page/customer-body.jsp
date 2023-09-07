<%-- 
    Document   : customer-body
    Created on : 29 ago. 2023, 3:25:34
    Author     : sirbon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div>
            <jsp:include page="customer-sidebar.jsp"/>
        </div>
        <div>
            <jsp:include page="customer-content.jsp"/>
        </div>
    </body>
</html>
