package com.ljb.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/21<br>
 * 描述: <br>
 */
public class DefaultKaptchaException extends AuthenticationException {

    private KaptchaException kaptchaException;

    public DefaultKaptchaException(KaptchaException e){
        this(e.getMessage(),e);
        kaptchaException=e;
    }

    public DefaultKaptchaException(String msg, Throwable t) {
        super(msg, t);
    }

    public KaptchaException getKaptchaException() {
        return kaptchaException;
    }
}
