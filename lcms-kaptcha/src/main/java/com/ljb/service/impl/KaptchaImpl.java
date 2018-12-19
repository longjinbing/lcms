package com.ljb.service.impl;

import com.ljb.exception.KaptchaIncorrectException;
import com.ljb.exception.KaptchaRenderException;
import com.ljb.exception.KaptchaTimeoutException;
import com.ljb.producer.impl.DefaultKaptcha;
import com.ljb.service.Kaptcha;
import org.apache.commons.lang.CharEncoding;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.types.Expiration;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/29<br>
 * 描述: <br>
 */
public class KaptchaImpl implements Kaptcha {
    private String KAPTCHA_SESSION_KEY="kaptcha-key";
    private String CACHE_NAME="kaptcha";
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    private JedisConnectionFactory jedisConnectionFactory;

    public KaptchaImpl(DefaultKaptcha defaultKaptcha, JedisConnectionFactory jedisConnectionFactory){
        this.defaultKaptcha=defaultKaptcha;
        this.jedisConnectionFactory=jedisConnectionFactory;
    }

    @Override
    public String render() {
        String kaptchaKey=request.getParameter(KAPTCHA_SESSION_KEY);
        if(!kaptchaKey.isEmpty()){
            jedisConnectionFactory.getConnection().del(kaptchaKey.getBytes());
        }
        UUID uuid= UUID.randomUUID();;
        this.response.setDateHeader("Expires", 0L);
        this.response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        this.response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        this.response.setHeader("kaptchaKey", uuid.toString());
        this.response.setHeader("Pragma", "no-cache");
        this.response.setContentType("image/jpeg");
        String text = this.defaultKaptcha.createText();
        try {
            ServletOutputStream out = this.response.getOutputStream();
            Throwable var3 = null;
            String var4;
            try {
                jedisConnectionFactory.getConnection().set(uuid.toString().getBytes(CharEncoding.UTF_8),text.getBytes(CharEncoding.UTF_8),Expiration.from(5L,TimeUnit.MINUTES), RedisStringCommands.SetOption.UPSERT);
                ImageIO.write(this.defaultKaptcha.createImage(text), "jpg", out);
                var4 = text;
            } catch (Throwable var14) {
                var3 = var14;
                throw var14;
            } finally {
                if (out != null) {
                    if (var3 != null) {
                        try {
                            out.close();
                        } catch (Throwable var13) {
                            var3.addSuppressed(var13);
                        }
                    } else {
                        out.close();
                    }
                }

            }
            return var4;
        } catch (IOException var16) {
            throw new KaptchaRenderException(var16);
        }
    }

    @Override
    public boolean validate(String code,String kaptchaKey) {
        return this.validate(code,kaptchaKey, 900L);
    }

    @Override
    public boolean validate(String code,String kaptchaKey, long second) {
        byte[] str =jedisConnectionFactory.getConnection().get(kaptchaKey.getBytes());
        String kaptcha= null;
        try {
            kaptcha = new String(str, CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (!kaptcha.isEmpty()) {
            jedisConnectionFactory.getConnection().del(kaptchaKey.getBytes());
            if (kaptcha.equals(code)) {
                return true;
            } else {
                throw new KaptchaIncorrectException();
            }
        } else {
            throw new KaptchaTimeoutException();
        }
    }

}
