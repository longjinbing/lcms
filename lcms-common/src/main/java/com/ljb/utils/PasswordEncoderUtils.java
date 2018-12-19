package com.ljb.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/9<br>
 * 描述: <br>
 */
public class PasswordEncoderUtils {

    public static String encode(String password){
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
    public static Boolean matchs(CharSequence rawPassword, String encodedPassword){
        BCryptPasswordEncoder encoder =new BCryptPasswordEncoder();
        return encoder.matches(rawPassword,encodedPassword);
    }
}
