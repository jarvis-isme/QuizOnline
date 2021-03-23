/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author nguye
 */
public class AnswerDTO implements Serializable{
    private String answerName;
    private int answerId,questionId;
    private boolean isRight;

    public AnswerDTO(String answerName, int answerId, int questionId, boolean isRight) {
        this.answerName = answerName;
        this.answerId = answerId;
        this.questionId = questionId;
        this.isRight = isRight;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

   
    public AnswerDTO() {
    }

    
    public String getAnswerName() {
        return answerName;
    }

    public void setAnswerName(String answerName) {
        this.answerName = answerName;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public boolean isIsRight() {
        return isRight;
    }

    public void setIsRight(boolean isRight) {
        this.isRight = isRight;
    }
    
}
