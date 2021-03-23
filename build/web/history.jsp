<%-- 
    Document   : history
    Created on : Feb 6, 2021, 4:06:51 PM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <title>History Page</title>
    </head>
    <body>
        <c:import url='./navbar.jsp'/>
        <form action='searchhistory' method='POST'>
            <div class="form-group col-sm-9">
                <label for='subject'>Subject</label>
                <select name="txtSubjectId" id="subject">
                    <c:forEach items="${sessionScope.USER_SEARCH}" var="item">
                        <option value="${item.subjectId}">${item.subjectName}</option>
                    </c:forEach>
                </select>
                <input type="submit"  class='btn btn-danger'name="x" value="Search"/>
            </div>
        </form>
        <hr>
        <c:if test="${sessionScope.HIS==null}">
            Not Found
        </c:if>
        <c:if test="${sessionScope.HIS!=null}">
            <h2>History Exam</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th >examId</th>
                        <th  >testedDate</th>
                        <th  >totalRight</th>
                        <th  >mark</th>
                        <th>subjectId</th>
                        <th>Preview</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${sessionScope.HIS}" var="his">
                        <tr>
                            <td>${his.examId}</td>
                            <td>${his.testedDate}</td>
                            <td>${his.totalRight}/50</td>
                            <td>${his.mark}</td>
                            <td>${his.subjectId}</td>
                            <td>
                                <form action="preview" method='POST'>
                                    <input type="hidden" name="txtExamId" value="${his.examId}"/>
                                    <input type="submit"  class='btn btn-primary'name="x" value="Preview"/>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
            <c:if test="${sessionScope.HIS_PAGE>1}">
            <c:forEach begin="1" end="${sessionScope.HIS_PAGE}" var='i'>
                <form  action='searchhistory' method='POST'>
                    <input type='hidden' name='txtSubjectId' value="${param.txtSubjectId}"/>
                    <input type='hidden' name='his_page' value="${i}"/>
                    <input type="submit" name='s' value='${i}'/>
                </form>
            </c:forEach>
        </c:if>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
