package com.ljb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/13<br>
 * 描述: <br>
 */
@Controller
@RequestMapping("shop")
public class ShopController {

    @RequestMapping("/addgoodscategory/{id}")
    public String addGoodsCategory(@PathVariable Long id, Model model){
        model.addAttribute("parentId", id);
        return "goods/editgoodscategory";
    }
    @RequestMapping("/editgoodscategory/{id}")
    public String editGoodsCategory(@PathVariable Long id, Model model){
        model.addAttribute("categoryId", id);
        return "goods/editgoodscategory";
    }

    @RequestMapping("/detailsgoodscategory/{id}")
    public String detailsGoodsCategory(@PathVariable Long id, Model model){
        model.addAttribute("id", id);
        return "goods/editgoodscategory";
    }

    @RequestMapping("/addgoods")
    public String addGoods(){
        return "goods/editgoods";
    }

    @RequestMapping("/editgoods/{id}")
    public String editGoods(@PathVariable Long id, Model model){
        model.addAttribute("goodsId", id);
        return "goods/editgoods";
    }

    @RequestMapping("/detailsgoods/{id}")
    public String detailsGoods(@PathVariable Long id, Model model){
        model.addAttribute("id", id);
        return "/goods/editgoods";
    }
}
