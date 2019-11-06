package com.zgkj.api.Auth.exception;


public class ServerErrorException extends RuntimeException {

    private String errorCode;

    public ServerErrorException(String errorCode, String message) {
        super(message);
        this.errorCode=errorCode;

    }

    public ServerErrorException() {
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
