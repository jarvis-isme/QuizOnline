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
public class ExamNotFinishDetailDTO implements Serializable{

    private int examNotFinishId,examNotFinishDetailId;
    private int questionId,answerId;
    private boolean isChoosen;

    public ExamNotFinishDetailDTO(int examNotFinishId, int examNotFinishDetailId, int questionId, int answerId, boolean isChoosen) {
        this.examNotFinishId = examNotFinishId;
        this.examNotFinishDetailId = examNotFinishDetailId;
        this.questionId = questionId;
        this.answerId = answerId;
        this.isChoosen = isChoosen;
    }

    public int getExamNotFinishId() {
        return examNotFinishId;
    }

    public void setExamNotFinishId(int examNotFinishId) {
        this.examNotFinishId = examNotFinishId;
    }

    public int getExamNotFinishDetailId() {
        return examNotFinishDetailId;
    }

    public void setExamNotFinishDetailId(int examNotFinishDetailId) {
        this.examNotFinishDetailId = examNotFinishDetailId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public boolean isIsChoosen() {
        return isChoosen;
    }

    public void setIsChoosen(boolean isChoosen) {
        this.isChoosen = isChoosen;
    }
    
}
