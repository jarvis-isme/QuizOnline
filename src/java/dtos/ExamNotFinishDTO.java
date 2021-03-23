/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.sql.Date;

/**
 *
 * @author nguye
 */
public class ExamNotFinishDTO {

    private int examNotFinishId, subjectId;
    private String email;
    private Date testedDate;
    private float timeremaining;

    public ExamNotFinishDTO() {
    }

    public ExamNotFinishDTO(int examNotFinishId, int subjectId, String email, Date testedDate, float timeremaining) {
        this.examNotFinishId = examNotFinishId;
        this.subjectId = subjectId;
        this.email = email;
        this.testedDate = testedDate;
        this.timeremaining = timeremaining;
    }

    
    public int getExamNotFinishId() {
        return examNotFinishId;
    }

    public void setExamNotFinishId(int examNotFinishId) {
        this.examNotFinishId = examNotFinishId;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTestedDate() {
        return testedDate;
    }

    public void setTestedDate(Date testedDate) {
        this.testedDate = testedDate;
    }

    public float getTimeremaining() {
        return timeremaining;
    }

    public void setTimeremaining(float timeremaining) {
        this.timeremaining = timeremaining;
    }

}
