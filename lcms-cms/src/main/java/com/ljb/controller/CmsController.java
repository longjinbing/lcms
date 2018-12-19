package com.ljb.controller;

import com.ljb.Base.BaseController;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/9<br>
 * 描述: <br>
 */
@Controller
@RequestMapping("/cms")
public class CmsController extends BaseController {

    @RequestMapping("/addarticle/{id}")
    public String addArticle(@Nullable @PathVariable Long id, Model model){
        model.addAttribute("categoryId",id );
        return "cms/editarticle";
    }

    @RequestMapping("/editarticle/{id}")
    public String editArticle(@Nullable @PathVariable Long id, Model model){
        model.addAttribute("articleId",id );
        return "cms/editarticle";
    }

    @RequestMapping("/articledetails/{id}")
    public String articleDetails(@Nullable @PathVariable Long id, Model model){
        model.addAttribute("articleId",id );
        return "cms/articledetails";
    }

    @RequestMapping("/articlelist/{id}")
    public String articleList(@Nullable @PathVariable Long id, Model model){
        model.addAttribute("categoryId",id );
        return "cms/articlelist";
    }




}
