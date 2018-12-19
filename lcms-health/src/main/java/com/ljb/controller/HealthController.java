package com.ljb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/16<br>
 * 描述: <br>
 */
@Controller
@RequestMapping("health")
public class HealthController {
    @RequestMapping("/editattribute/{id}")
    public String editHealthAttribute(@PathVariable Long id, Model model){
        model.addAttribute("healthId", id);
        return "health/attribute";
    }
}
