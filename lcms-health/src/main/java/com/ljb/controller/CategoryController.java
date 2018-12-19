package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.Category;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.CategoryService;
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
 * 体质分类Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */

@RestController
@RequestMapping("health/category")
@MenuDescription(group="健康中心",name="体质分类", action ="health/category.html")
public class CategoryController extends BaseController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="体质分类列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( categoryService.selectList(params));
    }

    @RequestMapping("/categorylist")
    @MenuDescription(name="体质分类列表")
    public R categoryList() {
        //查询列表数据
        return R.ok().put( categoryService.categoryList());
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看体质分类")
    public R info(@NotNull @PathVariable("id") Long id) {
        Category category  = categoryService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(category));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="体质分类详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> category  = categoryService.selectMapById(id);
        return R.ok().put( category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加体质分类")
    public R save(@Validated(AddGroup.class) @RequestBody Category category) {
        categoryService.save(category);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改体质分类")
    public R update(@Validated(UpdateGroup.class) @RequestBody Category category) {
        categoryService.update(category);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除体质分类")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        categoryService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="体质分类导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        categoryService.export(jsonObject,response);
    }
}
