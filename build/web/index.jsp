<%-- 
    Document   : index
    Created on : Jan 21, 2021, 5:15:59 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <style>
            body{
                background-image: linear-gradient(white, aqua);
            }
            .login{
                margin:150px auto;
                
            }
            input:focus{
                outline:none;  
            }
        </style>
    </head>
    <body>
        <div class='container '>
            <div class="login ">  
                <form action='login' method='POST'>
                    <h3 >Login</h3>
                    <div class="form-group col-3">
                        <label for='username'>Email</label>
                        <input type="email" class="form-control-file" name='txtEmail' id="username">
                     </div>
                    <p>${requestScope.LOGIN_ERROR}</p>
                    <div class="form-group col-3">
                         <label for='password'>Password</label>
                         <input type="password" class="form-control-file" name='txtPassword' id="password">
                    </div>
                    <input type='submit' name='btnAction' value='Login'/>
                    <a href='registerpage' >If you dont have account , click here to register</a>
                </form>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
