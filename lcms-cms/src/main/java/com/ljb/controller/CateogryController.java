package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.Cateogry;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.CateogryService;
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
 * 目录管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-08
 */

@RestController
@RequestMapping("cms/cateogry")
@MenuDescription(group="内容中心",name="内容管理",  action ="cms/cateogry.html")
public class CateogryController extends BaseController {
    @Autowired
    private CateogryService cateogryService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="目录管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", cateogryService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看目录管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        Cateogry cateogry  = cateogryService.selectById(id);
        return R.ok().put("data", BeanUtils.filteBean(cateogry));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="目录管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> cateogry  = cateogryService.selectMapById(id);
        return R.ok().put("data", cateogry);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加目录管理")
    public R save(@Validated(AddGroup.class) @RequestBody Cateogry cateogry) {
        cateogryService.save(cateogry);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改目录管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody Cateogry cateogry) {
        cateogryService.update(cateogry);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除目录管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        cateogryService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="目录管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        cateogryService.export(jsonObject,response);
    }
}
