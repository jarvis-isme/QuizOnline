<%-- 
    Document   : exam.jsp
    Created on : Feb 6, 2021, 6:04:15 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Exam Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <style>
        .question{
            border: 1px solid #000000;
            max-width: 700px;
            margin:10px auto;
            padding: 10px;
        }
    </style>
    <body>
        Timer  <p id='time'></p>
        <c:forEach items="${sessionScope.LIST_QUESTION_EXAM_USER}" var="question" varStatus="counter">
            <c:if test="${counter.count == sessionScope.INDEX}">
                <div class='question'>
                    
                    <form id='form'action='changequiz' method='POST'>
                        Question ${counter.count}:${question.questionName}
                        <br><input type='hidden' name='clock1' id='clock' value=""/>
                        <input type="hidden" name="txtQuestionId" value="${question.questionId}"/>
                        <c:forEach items="${sessionScope.MAP_ANSWER_EXAM[question.questionId]}" var='answer'>
                            <c:if test="${sessionScope.CHOSEN[answer.answerId] !=null}">
                                <input type="radio"    name='check'checked='true' value="${answer.answerId}"/> 
                            </c:if>
                            <c:if test="${sessionScope.CHOSEN[answer.answerId] ==null}">
                                <input type="radio"   name='check' value="${answer.answerId}"/> 
                            </c:if>
                            <label>${answer.answerName}</label>
                            <br>
                        </c:forEach>
                        <input type="hidden" id='index'value="${sessionScope.INDEX}" name='index'/>
                        <c:if test="${sessionScope.INDEX>1}">

                            <input type='submit' onclick="update(${sessionScope.INDEX-1})"value="Pre" name='Pre'/>
                        </c:if>
                        <c:if test="${sessionScope.INDEX<50}">
                            <input type='submit' onclick="update(${sessionScope.INDEX+1})"value="Next" name='Next'/>
                        </c:if>
                        <input type="submit" class="btn btn-danger" onclick="update(0)" value="Submit All and Finish"/>
                </div>
                <c:forEach begin='1' end='50' var='i'>
                    <input type="submit"  class='btn btn-primary'name="" value="${i}" onclick="update(${i})"/>
                </c:forEach>
            </form>
        </c:if>
    </c:forEach>



    <script>
        function update(index) {
            document.getElementById('index').value = index;
        }
        function startTimer(duration, display) {
            var timer = duration, minutes, seconds;
            setInterval(function () {
                minutes = parseInt(timer / 60, 10);
                seconds = parseInt(timer % 60, 10);
                minutes = minutes < 10 ? "0" + minutes : minutes;
                seconds = seconds < 10 ? "0" + seconds : seconds;
                document.getElementById('clock').value = minutes + ":" + seconds;
                display.textContent = minutes + ":" + seconds;
                console.log(document.getElementById('clock').value);
                if (seconds === '00' && minutes === '00') {
                    document.getElementById('index').value = 0;
                    document.getElementById('form').submit();
                }
                if (--timer < 0) {
                    timer = duration;
                }
            }, 1000);
        }

        window.onload = function () {
            var fiveMinutes =   ${sessionScope.TIME.timeremaining}*60,
                    display = document.querySelector('#time');
            startTimer(fiveMinutes, display);
        };
    </script>

</body>
</html>
