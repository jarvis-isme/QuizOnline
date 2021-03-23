/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author nguye
 */
public class QuestionDTO implements Serializable{
    private int questionId,subjectId;
    private String questionName;
    private Date createdDate;
    private boolean isDeleted;
    public QuestionDTO() {
    }

    public QuestionDTO(int questionId, int subjectId, String questionName, Date createdDate, boolean isDeleted) {
        this.questionId = questionId;
        this.subjectId = subjectId;
        this.questionName = questionName;
        this.createdDate = createdDate;
        this.isDeleted = isDeleted;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getQuestionName() {
        return questionName;
    }

    public void setQuestionName(String questionName) {
        this.questionName = questionName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

            
    
}
