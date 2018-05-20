package com.springboot.restapi.error;

/**
 * Created by Vinit Badrike on 5/18/2018.
 */
public class CustomError {
    private String errorMessage;

    public CustomError(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
