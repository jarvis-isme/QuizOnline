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
public class ExamDetailDTO implements Serializable{
    private int examId;
    private int examDetailDTO;
    private String questioName,rightAnswer,wrongAnswer1,wrongAnswer2,wrongAnswer3,chosenAnswer;

    public ExamDetailDTO() {
    }

    public ExamDetailDTO(int examId, int examDetailDTO, String questioName, String rightAnswer, String wrongAnswer1, String wrongAnswer2, String wrongAnswer3, String chosenAnswer) {
        this.examId = examId;
        this.examDetailDTO = examDetailDTO;
        this.questioName = questioName;
        this.rightAnswer = rightAnswer;
        this.wrongAnswer1 = wrongAnswer1;
        this.wrongAnswer2 = wrongAnswer2;
        this.wrongAnswer3 = wrongAnswer3;
        this.chosenAnswer = chosenAnswer;
    }

    public int getExamId() {
        return examId;
    }

    public void setExamId(int examId) {
        this.examId = examId;
    }

    public int getExamDetailDTO() {
        return examDetailDTO;
    }

    public void setExamDetailDTO(int examDetailDTO) {
        this.examDetailDTO = examDetailDTO;
    }

    public String getQuestioName() {
        return questioName;
    }

    public void setQuestioName(String questioName) {
        this.questioName = questioName;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public String getWrongAnswer1() {
        return wrongAnswer1;
    }

    public void setWrongAnswer1(String wrongAnswer1) {
        this.wrongAnswer1 = wrongAnswer1;
    }

    public String getWrongAnswer2() {
        return wrongAnswer2;
    }

    public void setWrongAnswer2(String wrongAnswer2) {
        this.wrongAnswer2 = wrongAnswer2;
    }

    public String getWrongAnswer3() {
        return wrongAnswer3;
    }

    public void setWrongAnswer3(String wrongAnswer3) {
        this.wrongAnswer3 = wrongAnswer3;
    }

    public String getChosenAnswer() {
        return chosenAnswer;
    }

    public void setChosenAnswer(String chosenAnswer) {
        this.chosenAnswer = chosenAnswer;
    }
    
    
}
