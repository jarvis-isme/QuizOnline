<%-- 
    Document   : home
    Created on : Jan 31, 2021, 3:43:40 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
    </head>
    <body>
        <c:import url="navbar.jsp"/>
        <h3>List subject you can take exam</h3>
        <c:forEach items="${sessionScope.LIST_EXAM}" var='subject'>
            <div class="row">
                <span> ${subject.subjectName}</span>
                <form action="quiz" method="POST" >
                    <input type="hidden" name="txtSubjectId" value="${subject.subjectId}"/>
                    <c:if test="${sessionScope.USER.roleId eq 'US'}">
                        <input type="submit" name="btnAction" value="Click here to test"/>
                    </c:if>
                        
                </form>
            </div>
        </c:forEach>
    </body>
</html>
