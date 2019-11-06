package com.zgkj.api.Auth.dto;

public class Response<T> {
    private int status = 1;
    private String errorCode;
    private String errorMsg;
    private T resultBody;
    public Response() {
    }
    public Response(T resultBody) {
        this.resultBody = resultBody;
    }

    public Response(int status, String errorCode, String errorMsg) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public Response(int status, String errorCode, String errorMsg, T resultBody) {
        this.status = status;
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
        this.resultBody = resultBody;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getResultBody() {
        return resultBody;
    }

    public void setResultBody(T resultBody) {
        this.resultBody = resultBody;
    }
}
