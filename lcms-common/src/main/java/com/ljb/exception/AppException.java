package com.ljb.exception;

import org.springframework.http.HttpStatus;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/17<br>
 * 描述: <br>
 */
public class AppException extends RuntimeException {

    private HttpStatus httpStatus;

    public AppException(HttpStatus httpStatus) {
        this.httpStatus=httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
