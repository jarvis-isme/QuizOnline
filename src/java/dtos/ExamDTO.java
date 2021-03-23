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
public class ExamDTO implements Serializable{
    private int examId;
    private int subjectId;
    private Date testedDate;
    private int totalRight;
    private float mark;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ExamDTO(int examId, int subjectId, Date testedDate, int totalRight, float mark, String email) {
        this.examId = examId;
        this.subjectId = subjectId;
        this.testedDate = testedDate;
        this.totalRight = totalRight;
        this.mark = mark;
        this.email = email;
    }
    public ExamDTO() {
    }

    

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public Date getTestedDate() {
        return testedDate;
    }

    public void setTestedDate(Date testedDate) {
        this.testedDate = testedDate;
    }

    public int getTotalRight() {
        return totalRight;
    }

    public void setTotalRight(int totalRight) {
        this.totalRight = totalRight;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }
    
}
