package com.zgkj.api.Auth.exception;


public class ApiException extends RuntimeException {

    private String errorCode;

    public ApiException(String errorCode,String message) {
        super(message);
        this.errorCode=errorCode;

    }

    public ApiException() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
