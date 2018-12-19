package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.annotion.SafetyGrade;
import com.ljb.entity.Attribute;
import com.ljb.model.AddGroup;
import com.ljb.model.SortUpdateModel;
import com.ljb.model.UpdateGroup;
import com.ljb.service.AttributeService;
import com.ljb.utils.BeanUtils;
import com.ljb.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 体质属性Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */

@RestController
@RequestMapping("health/attribute")
@MenuDescription(group="健康中心",name="体质属性", action ="health/attribute.html",safetyGrade = SafetyGrade.HIDDEN)
public class AttributeController extends BaseController {
    @Autowired
    private AttributeService attributeService;

    @RequestMapping("/sorttop")
    @MenuDescription(name="属性置顶")
    public R sortTop(@RequestBody SortUpdateModel sortUpdateModel) {
        // 查询列表数据
        return R.ok().put(attributeService.sortTop(sortUpdateModel) );
    }

    @RequestMapping("/sortup")
    @MenuDescription(name="属性上移")
    public R sortU(@RequestBody SortUpdateModel sortUpdateModel) {
        // 查询列表数据
        return R.ok().put(attributeService.sortUp(sortUpdateModel) );
    }

    @RequestMapping("/sortdown")
    @MenuDescription(name="属性下移")
    public R sortDown(@RequestBody SortUpdateModel sortUpdateModel) {
        // 查询列表数据
        return R.ok().put(attributeService.sortDown(sortUpdateModel) );
    }


    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="体质属性列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( attributeService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看体质属性")
    public R info(@NotNull @PathVariable("id") Long id) {
        Attribute attribute  = attributeService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(attribute));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="体质属性详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> attribute  = attributeService.selectMapById(id);
        return R.ok().put( attribute);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加体质属性")
    public R save(@Validated(AddGroup.class) @RequestBody Attribute attribute) {
        attributeService.save(attribute);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改体质属性")
    public R update(@Validated(UpdateGroup.class) @RequestBody Attribute attribute) {
        attributeService.update(attribute);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除体质属性")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        attributeService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="体质属性导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        attributeService.export(jsonObject,response);
    }
}
