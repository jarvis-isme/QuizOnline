<%-- 
    Document   : updatepage.jsp
    Created on : Feb 1, 2021, 6:00:11 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <style>
        .waring{
            color:red !important;
        }
        .text{
            text-transform: uppercase;
        }
    </style>
    <body>
        <form   action='updatequestion' id='form'method="POST">
            <h2>UPDATE FOOD</h2>
            <div class="form-group col-sm-9">
                <label for='question'>Question</label>
                <input type="text"  class="form-control-file" value="${sessionScope.QUESTION_UPDATE.questionName}" name='txtQuestionName' id="question">
                <input type="hidden"  name="txtQuestionId" value="${sessionScope.QUESTION_UPDATE.questionId}"  />
                <div id='questionError' class='waring'>
                </div>
            </div>
            <c:forEach items="${sessionScope.ANSWER_UPDATE}" var='answer' varStatus="counter" >
                <c:if test="${answer.isRight == true}">
                    <div class="form-group col-sm-9">
                        <label for='rightanswer'>Right Answer</label>
                        <input type="text" id='rightAnswer'class="form-control-file" value="${answer.answerName}" name='txtAnswer'  >
                        <input type="hidden" name="txtAnswerId" value="${answer.answerId}"/>
                        <div id='rightAnswerError' class='waring'>
                        </div>
                    </div>
                </c:if>
                <c:if test="${answer.isRight != true}">
                    <div class="form-group col-sm-9">
                        <label for='wrongAnswer2'>Wrong Answer #${counter.count-1}</label>
                        <input type="text" id='wrongAnswer${counter.count-1}' value="${answer.answerName}" class="form-control-file" name='txtAnswer'  >
                        <input type="hidden" name="txtAnswerId" value="${answer.answerId}"/>
                        <div id='wrongAnswer${counter.count-1}Error' class='waring'>
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            <div class="form-group col-sm-9">
                <label for='subject'>IsDeleted</label>
                <select name="txtIsDeleted" id="subject">
                    <option class='text' value="${sessionScope.QUESTION_UPDATE.isDeleted}">${sessionScope.QUESTION_UPDATE.isDeleted}</option>
                    <option class='text' value="${!sessionScope.QUESTION_UPDATE.isDeleted}">${!sessionScope.QUESTION_UPDATE.isDeleted}</option>
                </select>
            </div>
            <div class="form-group col-sm-9">
                <label for='subject'>Subject</label>
                <select name="txtSubject" id="subject">
                    <c:forEach items="${sessionScope.SUBJECT_LIST_ADMIN}" var="item">
                        <option value="${item.subjectId}">${item.subjectName}</option>
                    </c:forEach>
                </select>
            </div>
            <div class="form-group col-sm-9">
                <input  margin-bottom='10px'type="button" onClick="checkValidation()"  value='Update Question'class="btn btn-primary " name="btnAcrion" />
            </div>
        </form>
        <script>
            function checkValidation() {
                let check = true;
                const question = document.getElementById('question').value;
                if (question.trim().length === 0 || question.length > 80) {
                    document.getElementById('questionError').innerHTML = "Question's lentght:0-80";
                    check = false;
                } else if (question.includes("'")) {
                    document.getElementById('questionError').innerHTML = "Question can not contains '' ";
                    check = false;
                } else {
                    console.log('test');
                    document.getElementById('questionError').innerHTML = "";
                }
                rightAnswer = document.getElementById('rightAnswer').value;
                if (rightAnswer.trim().length == 0 || rightAnswer.length > 80) {
                    document.getElementById('rightAnswerError').innerHTML = "Answer's lentght:0-80";
                    check = false;
                } else {
                    document.getElementById('rightAnswerError').innerHTML = "";
                }

                wrongAnswer1 = document.getElementById('wrongAnswer1').value;
                if (wrongAnswer1.trim().length == 0 || wrongAnswer1.length > 80) {
                    document.getElementById('wrongAnswer1Error').innerHTML = "Answer's lentght:0-80";
                    check = false;
                } else if (rightAnswer.trim() === wrongAnswer1.trim()) {
                    document.getElementById('wrongAnswer1Error').innerHTML = "Wrong Answer can not be similar right answer";
                    check = false;
                } else {
                    document.getElementById('wrongAnswer1Error').innerHTML = "";
                }
                wrongAnswer2 = document.getElementById('wrongAnswer2').value;
                if (wrongAnswer2.trim().length == 0 || wrongAnswer2.length > 80) {
                    document.getElementById('wrongAnswer2Error').innerHTML = "Answer's lentght:0-80";
                    check = false;
                } else if (rightAnswer.trim() === wrongAnswer2.trim()) {
                    document.getElementById('wrongAnswer2Error').innerHTML = "Wrong Answer can not be similar right answer";
                    check = false;
                } else {
                    document.getElementById('wrongAnswer2Error').innerHTML = "";
                }
                wrongAnswer3 = document.getElementById('wrongAnswer3').value;
                if (wrongAnswer3.trim().length === 0 || wrongAnswer3.length > 80) {
                    document.getElementById('wrongAnswer3Error').innerHTML = "Answer's lentght:0-80";
                    check = false;
                } else if (rightAnswer.trim() === wrongAnswer3.trim()) {
                    document.getElementById('wrongAnswer3Error').innerHTML = "Wrong Answer can not be similar right answer";
                    check = false;
                } else {
                    document.getElementById('wrongAnswer3Error').innerHTML = "";
                }
                if (check) {
                    document.getElementById('form').submit();
                } else {
                    return check;
                }

            }
        </script>
    </body>
</html>
