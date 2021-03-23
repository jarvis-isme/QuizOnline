<%-- 
    Document   : notify
    Created on : Feb 6, 2021, 3:09:56 PM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notify Page</title>
    </head>
    <body>
    <c:import url="./navbar.jsp"/>
    <h3>${requestScope.NOTIFY}</h3>
    </body>
</html>
