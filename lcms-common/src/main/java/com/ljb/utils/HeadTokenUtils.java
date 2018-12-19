package com.ljb.utils;

import com.ljb.exception.AppException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/17<br>
 * 描述: <br>
 */
@Component
public class HeadTokenUtils {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    public Boolean hasToken(){
        Cookie cookie=getCookieByName(request,Constant.COOKIE_NAME);
        if(cookie==null){
            String token=request.getHeader(Constant.COOKIE_NAME);
            if(token==null){
                return false;
            }
            return true;
        }
        return true;
    }

    public String getToken(){
       Cookie cookie=getCookieByName(request,Constant.COOKIE_NAME);
       if(cookie==null){
           String token=request.getHeader(Constant.COOKIE_NAME);
           if(token==null){
               throw new AppException(HttpStatus.UNAUTHORIZED);
           }
           return token;
       }
       return cookie.getValue();
    }

    public Long getUserId(){
        return Long.valueOf(getToken());
    }

    /**
     * 根据名字获取cookie
     *
     * @param request
     * @param name
     *            cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        if (cookieMap.containsKey(name)) {
            Cookie cookie = (Cookie) cookieMap.get(name);
            return cookie;
        } else {
            return null;
        }
    }

    /**
     * 将cookie封装到Map里面
     *
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 保存Cookies
     *
     * @param
     *
     * @param value
     *            保存值
     * @author jxf
     */
    public HttpServletResponse setCookie( String value) {
        Cookie cookie = new Cookie(Constant.COOKIE_NAME, value);
        cookie.setPath("/");
        try {
            URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cookie.setMaxAge(60000);
        response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
        return response;
    };

    public static HttpServletResponse setCookie(HttpServletResponse response, String name, String value,int time) {
        // new一个Cookie对象,键值对为参数
        Cookie cookie = new Cookie(name, value);
        // tomcat下多应用共享
        cookie.setPath("/");
        // 如果cookie的值中含有中文时，需要对cookie进行编码，不然会产生乱码
        try {
            URLEncoder.encode(value, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        cookie.setMaxAge(time);
        // 将Cookie添加到Response中,使之生效
        response.addCookie(cookie); // addCookie后，如果已经存在相同名字的cookie，则最新的覆盖旧的cookie
        return response;
    }
}
