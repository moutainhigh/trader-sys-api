package com.zgkj.api.Auth.handler;

import com.zgkj.api.Auth.dto.Response;
import com.zgkj.api.Auth.exception.ApiException;
import com.zgkj.api.Auth.exception.ServerErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Object notFountHandler() {
        return new Response(-1, "404", "页面不存在");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object badRequestHandler() {
        return new Response(-1, "400", "请求参数无法解析");
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Object requestMethodErrorHandler() {
        return new Response(-1, "400", "请求方法不支持");
    }

    @ExceptionHandler(ApiException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Object systemErrorHandler(ApiException e) {
        return new Response(-1, e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(ServerErrorException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object ServerErrorHandler(ServerErrorException e) {
        return new Response(-1, e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Object unknownExceptionHandler(Exception e) {
        e.printStackTrace();
        log.info(e.getLocalizedMessage());
        log.error("未捕获异常：", e);
        return new Response(-1, "-1", "系统未知错误");
    }
}
