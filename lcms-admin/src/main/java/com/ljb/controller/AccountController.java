package com.ljb.controller;

import com.ljb.entity.User;
import com.ljb.service.AccountService;
import com.ljb.service.UserService;
import com.ljb.utils.R;
import com.ljb.utils.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/9/12<br>
 * 描述: <br>
 */
@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login")
    public String login(){
        return "account/login";
    }

    @RequestMapping(value = "/register")
    public String register(){
        return "account/register";
    }

    @RequestMapping(value="/register",method=RequestMethod.POST)
    public @ResponseBody R Register(@Validated @RequestBody User user) {
        if(accountService.findByCode(user.getCode())!=null) {
            return R.error("学号已注册");
        }
        user.setStatus(1);
        user.setPassword(SHA256Util.getSHA256Str(user.getPassword()));
        if(userService.save(user)==0)
            return R.error("注册失败");
        return R.ok("注册成功");
    }

    @RequestMapping(value="/number",method=RequestMethod.GET)
    @ResponseBody
    public R numberIsExist(@RequestParam String number) {
        if(accountService.findByCode(number)==null) {
            return R.ok();
        }else{
            return R.error(1, "学号已注册");
        }
    }

    @RequestMapping(value="/unauthorized",method=RequestMethod.GET)
    public String unAuthorized(@RequestParam(required = false,defaultValue = "请登录") String message, Model model) {
        accountService.logout();
        model.addAttribute("message", message);
        return "/account/unauthorized";
    }

}
