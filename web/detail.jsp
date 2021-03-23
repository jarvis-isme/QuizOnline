<%-- 
    Document   : detailjsp
    Created on : Feb 7, 2021, 12:57:43 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail Page</title>
    </head>
    <style>
        .correct{
            color:green;
            font-size: 20px;
        }.false{
            color:red;
        }
    </style>
    <body>
        <c:import url='./navbar.jsp'/>
        <c:forEach items="${sessionScope.DETAIL}" var="detail">
            <div class='card'>
                Question: ${detail.questioName}<br>
                ${detail.rightAnswer}<br>
                ${detail.wrongAnswer1}<br>
                ${detail.wrongAnswer2}<br/>
                ${detail.wrongAnswer3}<br>
                <p>Your chose:${detail.chosenAnswer} </p>
                <p class='correct'> Correct Answer:${detail.rightAnswer}</p>
                <c:if test="${detail.rightAnswer eq detail.chosenAnswer}">
                    True
                </c:if>
                <c:if test="${ !(detail.rightAnswer  eq detail.chosenAnswer)}">
                    <p class='false' >False</p>
                </c:if>
                <br>
            </div>
        </c:forEach>
    </body>
</html>
