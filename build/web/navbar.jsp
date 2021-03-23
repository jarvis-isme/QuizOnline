<%-- 
    Document   : navbar
    Created on : Jan 21, 2021, 5:16:30 AM
    Author     : nguye

--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Navbar Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <style>
       
    </style>
    <body>
        <nav class="navbar navbar-expand-md navbar-light bg-light sticky-top">
            <div class="container-fluid">
                <a class="navbar-branch" href="#">
                    <img width="50px" src="https://i.pinimg.com/originals/ae/13/62/ae1362fd43d6a531598aa8c659370c58.jpg"/>
                    <button class="navbar-toggler" type="button" data-toggle="collapse" 
                            data-target="#navbarResponsive">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarResponsive">
                        <ul class="navbar-nav ml-auto">
                            <li class="nav-item">
                                <a class="nav-link active" href="homepage">Home</a>
                            </li>
                            <c:if test="${sessionScope.USER.roleId eq'AD'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="searchquestion">Manage Question</a>
                                </li>
                            </c:if>
                            <li class="nav-item">
                                <p class="nav-link">Welcome ${sessionScope.USER.email}</p>
                            </li>  
                            <c:if test="${ sessionScope.USER.roleId eq 'US'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="searchhistory">History Exam</a>
                                </li>
                            </c:if>
                            <li class="nav-item">
                                <a class="nav-link" href="logout">Log Out</a>
                            </li>
                        </ul>
                    </div>
            </div>
        </nav>

        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
