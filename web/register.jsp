<%-- 
    Document   : register
    Created on : Jan 21, 2021, 5:39:01 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link href="https://use.fontawesome.com/releases/v5.0.4/css/all.css" rel="stylesheet">    
    </head>
    <style>
        body{
            background-image: linear-gradient(white, aqua);
        }
        .waring{
            color:red !important;
        }
        .register{
            margin-left: 25%;
        }

    </style>
    <body>
        <c:import url="navbar.jsp"/>
        <div class="container ">
            <div class='register container-fluid'>
                <h2 class='card-title '>Register</h2>
                <form action='register' method='POST'>
                    <div class="form-group col-3">
                        <label for='username'>FullName:</label>
                        <input type="text" class="form-control-file" name='txtFullName' value="${param.txtFullName}" id="username">
                        <p class="waring">    ${requestScope.REGISTER_ERROR.fullNameError}</p> 
                    </div>

                    <div class="form-group col-3">
                        <label for='email'>Email</label>
                        <input type="email" class="form-control-file" name='txtEmail' value="${param.txtEmail}" id="email">
                        <p class="waring">    ${requestScope.REGISTER_ERROR.emailError}</p>
                    </div>
                    <div class="form-group col-3">
                        <label for='password'>Password</label>
                        <input type="password" value="${param.txtPassword}" class="form-control-file" name='txtPassword' id="password">
                        <p class="waring">${requestScope.REGISTER_ERROR.passwordError}</p>
                    </div>
                    <div class="form-group col-3">
                        <label for='password'>Confired-Password</label>
                        <input type="password" class="form-control-file" value='${param.txtRePassword}'name='txtRePassword' id="password">
                        <p class="waring">${requestScope.REGISTER_ERROR.rePasswordError}</p>
                    </div>
                    <div class="form-group col-3">
                        <input type='submit' class='btn btn-primary'  value='Register'/>
                        <a class='btn btn-danger'href='loginpage'>Login</a>
                    </div>
                </form>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
