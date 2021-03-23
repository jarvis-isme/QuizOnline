<%-- 
    Document   : admin.jsp
    Created on : Jan 31, 2021, 3:42:57 AM
    Author     : nguye
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <style>
        .modal fade{
            min-width:700px!important;
            padding: 10px !important;
        }
        .waring{
            color:red!important;
        }
        .question{
            border: 1px solid #000000;
            max-width: 700px;
            margin:10px auto;
            padding: 10px;
        }
        .right-answer{
            color:greenyellow;
        }
        button{
            margin-left: 10px;
        }
        .row{
            margin-left: 10px;

            padding: 5px;   
        }
    </style>

    <body>
        <c:import url='navbar.jsp'/>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter">
            Add Question
        </button>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModalCenter1">
            Search
        </button>
        <a href="searchquestion"class="btn btn-primary"> Get All Question</a>
        <!--        searchform-->
        <div style='padding:10px !important;'class="modal fade" id="exampleModalCenter1" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="model-header text-center ">
                        <h3 class="card-title">Search Name  </h3>
                    </div>
                    <form action='searchquestion' id='formsearch'method="POST">
                        <div class="form-group col-sm-9">
                            <label for='questionSearch'>Question Name</label>
                            <input type="text"  class="form-control-file" name='txtQuestionName' id="questionSearch">
                        </div>
                        <div class="form-group col-sm-9">
                            <label for='subject'>Subject</label>
                            <select name="txtSubject" id="subjectSearch">
                                <c:forEach items="${sessionScope.SUBJECT_LIST_ADMIN}" var="item">
                                    <option value="${item.subjectId}">${item.subjectName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-sm-9">
                            <label for='hft'>IsDeleted</label>
                            <select name="txtIsActive" id="saechsubject">
                                <option value="True">Deleted</option>
                                <option value="False">Not deleted</option>
                            </select>
                        </div>
                        <div class="form-group col-sm-9">
                            QuestionName <input type="radio" id='check' checked="" name="txtAction" value="Name"/><br>
                            IsActive <input type="radio" name="txtAction" value="IsActive"/><br>
                            Subject <input type="radio" name="txtAction" name='txtAction' value="Subject"/>
                        </div>
                        <br>
                        <div class="form-group col-sm-9">
                            <input class='btn btn-danger' onclick='checkSearch()' value="Search Question" type='submit'/>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Modal -->
        <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="model-header text-center ">
                        <h3 class="card-title   ">Add Question</h3>
                    </div>
                    <form   action='addquestion' id='formadd' method="POST">
                        <div class="form-group col-sm-9">
                            <label for='question'>Question</label>
                            <input type="text"  class="form-control-file" name='txtQuestion' id="question">
                            <div id='questionError' class='waring'>
                            </div>
                        </div>
                        <div class="form-group col-sm-9">
                            <label for='rightanswer'>Right Answer</label>
                            <input type="text" id='rightAnswer'class="form-control-file" name='txtAnswer'  >
                            <div id='rightAnswerError' class='waring'>
                            </div>
                        </div>

                        <div class="form-group col-sm-9 ">
                            <label for='wronganser1'>Wrong Answer #1</label>
                            <input type="text" id='wrongAnswer1'class="form-control-file" name='txtAnswer' >
                            <div id='wrongAnswer1Error' class='waring'>
                            </div>
                            <div  class='waring'>
                            </div>
                        </div>

                        <div class="form-group col-sm-9">
                            <label for='wrongAnswer2'>Wrong Answer #2</label>
                            <input type="text" id='wrongAnswer2' class="form-control-file" name='txtAnswer' >
                            <div id='wrongAnswer2Error' class='waring'>
                            </div>
                        </div>

                        <div class="form-group col-sm-9 ">
                            <label for='wronganser3'>Wrong Answer #3</label>
                            <input type="text" id='wrongAnswer3' class="form-control-file" name='txtAnswer'  >
                            <div id='wrongAnswer3Error' class='waring'>
                            </div>
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
                            <input class='btn btn-primary'onclick="checkValidation()"type="button" name="btnAction" value="Click Here to Delete"/>
                        </div>
                    </form>
                </div>
            </div></div>
        <hr>

        <c:if test="${sessionScope.PAGES >1}">
            <div class="row">
                <c:forEach begin="1" end="${sessionScope.PAGES}" var='index'>
                    <div>
                        <form action="searchquestion" method="POST">
                            <input type="hidden" name="txtAction" value="${param.txtAction}"/>
                            <input type="hidden" name="txtPage" value="${index}"/>
                            <input type="hidden" name="txtSubjectId" value="${param.txtSubjectId}"/>
                            <input type="submit" class="btn btn-danger" value="${index}"/>
                            <input type="hidden" name="txtIsActive" value="${param.isActive}"/>
                            <input type="hidden" name="txtQuestionName" value="${param.questionName}"/>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${sessionScope.MAP_SUBJECT ==null}">
            <h3>Not found</h3>
            <a href='searchquestion'>Get All</a>
        </c:if>
        <c:if test="${sessionScope.MAP_SUBJECT!=null}">
            <c:forEach items="${sessionScope.MAP_SUBJECT}" var="subject">
                <!--                render name subject-->
                <c:forEach items="${sessionScope.SUBJECT_LIST_ADMIN}" var="item">
                    <c:if test="${subject.key eq item.subjectId}">
                        <h3 class="card-title text-center">${item.subjectName}</h3>
                    </c:if>
                </c:forEach>
                <!--                        render list question-->
                <c:forEach items="${subject.value}" var='question'>
                    <div class="question">
                        <h4>Question:   ${question.questionName}</h4>
                        <p>QuestionId:${question.questionId}</p>
                        <p>SubjectId:${question.subjectId}</p>
                        <p>createdDate:   ${question.createdDate}</p>
                        <p>isDeleted: ${question.isDeleted}</p>
                        <c:set var = "listAnswer" scope = "session" value = "${sessionScope.MAP_ANSWER[question.questionId]}"/>
                        <c:forEach items="${listAnswer}" var='answer'>
                            <c:if test="${answer.isRight == true}">
                                <p class="right-answer">${answer.answerName}</p>
                            </c:if>
                            <c:if test="${answer.isRight != true}">
                                <p >${answer.answerName}</p>
                            </c:if>
                        </c:forEach>
                        <div class="row" padding="10px">
                            <form action="updatepage" method="POST">
                                <input type="hidden" name="txtQuestionId" value="${question.questionId}">
                                <input class='btn btn-danger'type="submit" name="btnAction" value="Click Here to Update"/>
                            </form>
                            <c:if test="${question.isDeleted != true}">
                                <form action="deletequestion" method="POST">
                                    <input type="hidden" name="txtQuestionId" value="${question.questionId}">
                                    <input class='btn btn-primary'type="submit" name="btnAction" value="Click Here to Delete"/>
                                </form>
                            </c:if>
                        </div>
                    </div>
                </c:forEach>
            </c:forEach>
        </c:if>
        <c:if test="${sessionScope.PAGES >1}">
            <div class="row">
                <c:forEach begin="1" end="${sessionScope.PAGES}" var='index'>
                    <div>
                        <form action="searchquestion" method="POST">
                            <input type="hidden" name="txtAction" value="${param.txtAction}"/>
                            <input type="hidden" name="txtPage" value="${index}"/>
                            <input type="hidden" name="txtSubjectId" value="${param.txtSubjectId}"/>
                            <input type="submit" class="btn btn-danger" value="${index}"/>
                            <input type="hidden" name="txtIsActive" value="${param.isActive}"/>
                            <input type="hidden" name="txtQuestionName" value="${param.questionName}"/>
                        </form>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <script>
            function checkValidation() {
                var question = document.getElementById('question').value;
                if (question.trim().length === 0 || question.length > 80) {
                    console.log('1');
                    document.getElementById('questionError').innerHTML = "Question's lentght:0-80";
                    return false
                } else if (question.includes("'")) {
                    document.getElementById('questionError').innerHTML = "Question can not contains '' ";
                    return false
                }
                var rightAnswer = document.getElementById('rightAnswer').value;
                if (rightAnswer.trim().length == 0 || rightAnswer.length > 80) {
                    document.getElementById('rightAnswerError').innerHTML = "Answer's lentght:0-80";
                    return false
                    console.log('1');
                }

                var wrongAnswer1 = document.getElementById('wrongAnswer1').value;
                if (wrongAnswer1.trim().length == 0 || wrongAnswer1.length > 80) {
                    document.getElementById('wrongAnswer1Error').innerHTML = "Answer's lentght:0-80";
                    console.log('1');
                    return false
                } else if (rightAnswer.trim() === wrongAnswer1.trim()) {
                    document.getElementById('wrongAnswer1Error').innerHTML = "Wrong Answer can not be similar right answer";
                    console.log('1');
                    return false

                }
                var wrongAnswer2 = document.getElementById('wrongAnswer2').value;
                if (wrongAnswer2.trim().length == 0 || wrongAnswer2.length > 80) {
                    document.getElementById('wrongAnswer2Error').innerHTML = "Answer's lentght:0-80";
                    console.log('1');
                    return false
                } else if (rightAnswer.trim() === wrongAnswer2.trim()) {
                    document.getElementById('wrongAnswer2Error').innerHTML = "Wrong Answer can not be similar right answer";
                    return false
                }
                var wrongAnswer3 = document.getElementById('wrongAnswer3').value;
                if (wrongAnswer3.trim().length === 0 || wrongAnswer3.length > 80) {
                    document.getElementById('wrongAnswer3Error').innerHTML = "Answer's lentght:0-80";
                    return false
                } else if (rightAnswer === wrongAnswer3) {
                    document.getElementById('wrongAnswer3Error').innerHTML = "Wrong Answer can not be similar right answer";

                    console.log('1');
                    return false
                }
                document.getElementById('formadd').submit();
            }
            function   checkSearch() {
                var check = document.getElementById('check');
                question = document.getElementById('question').value;
                if (check.checked === true) {
                    if (question.includes("'")) {
                        alert("Your question name is not valid becasue character '' ");
                        return false;
                    }
                }

                return true;
            }
        </script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
