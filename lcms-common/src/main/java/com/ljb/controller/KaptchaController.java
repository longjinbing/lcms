package com.ljb.controller;

import com.ljb.service.Kaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/21<br>
 * 描述: <br>
 */
@RestController
@RequestMapping("/kaptcha")
public class KaptchaController {

    @Autowired
    private Kaptcha kaptcha;

    @GetMapping("/render")
    public void render() {
        kaptcha.render();
    }
}
