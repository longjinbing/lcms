package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.ApiAd;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.ApiAdService;
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
 * 促销广告Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apiad")
public class ApiAdController extends BaseController {
    @Autowired
    private ApiAdService apiAdService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiAdService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看促销广告")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiAd apiAd  = apiAdService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiAd));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="促销广告详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiAd  = apiAdService.selectMapById(id);
        return R.ok().put( apiAd);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加促销广告")
    public R save(@Validated(AddGroup.class) @RequestBody ApiAd apiAd) {
        apiAdService.save(apiAd);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改促销广告")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiAd apiAd) {
        apiAdService.update(apiAd);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除促销广告")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiAdService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="促销广告导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiAdService.export(jsonObject,response);
    }
}
