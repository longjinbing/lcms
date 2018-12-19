package com.ljb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/9/13<br>
 * 描述: <br>
 */
@Controller
public class AdminController {

    @RequestMapping("/")
    public String home(){
        return "admin/index";
    }

    @RequestMapping("/admin")
    public String index(){
        return "admin/index";
    }
}
