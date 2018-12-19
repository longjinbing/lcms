package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.ApiTopicCategory;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.ApiTopicCategoryService;
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
 * 主题类型Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apitopiccategory")
@MenuDescription(group="商城API",name="主题类型", action ="api/apitopiccategory.html")
public class ApiTopicCategoryController extends BaseController {
    @Autowired
    private ApiTopicCategoryService apiTopicCategoryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="主题类型列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiTopicCategoryService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看主题类型")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiTopicCategory apiTopicCategory  = apiTopicCategoryService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiTopicCategory));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="主题类型详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiTopicCategory  = apiTopicCategoryService.selectMapById(id);
        return R.ok().put( apiTopicCategory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加主题类型")
    public R save(@Validated(AddGroup.class) @RequestBody ApiTopicCategory apiTopicCategory) {
        apiTopicCategoryService.save(apiTopicCategory);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改主题类型")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiTopicCategory apiTopicCategory) {
        apiTopicCategoryService.update(apiTopicCategory);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除主题类型")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiTopicCategoryService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="主题类型导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiTopicCategoryService.export(jsonObject,response);
    }
}
