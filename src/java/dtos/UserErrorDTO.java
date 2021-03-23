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
public class UserErrorDTO implements Serializable{
    private String emailError, fullNameError, passwordError, rePasswordError;

    public UserErrorDTO() {
    }

    public UserErrorDTO(String emailError, String fullNameError, String passwordError, String rePasswordError) {
        this.emailError = emailError;
        this.fullNameError = fullNameError;
        this.passwordError = passwordError;
        this.rePasswordError = rePasswordError;
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getFullNameError() {
        return fullNameError;
    }

    public void setFullNameError(String fullNameError) {
        this.fullNameError = fullNameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getRePasswordError() {
        return rePasswordError;
    }

    public void setRePasswordError(String rePasswordError) {
        this.rePasswordError = rePasswordError;
    }

   

}
