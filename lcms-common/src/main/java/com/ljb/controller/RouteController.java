package com.ljb.controller;

import com.ljb.Base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/9<br>
 * 描述: <br>
 */
@Controller
public class RouteController extends BaseController {

    @RequestMapping("/{module}/{page}.html")
    public String router(@PathVariable String module, @PathVariable String page){
       return module+"/"+page;
    }

}
