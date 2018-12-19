package com.ljb.controller;

import java.util.*;
import com.ljb.annotion.*;
import com.ljb.model.*;
import com.ljb.utils.*;
import com.ljb.Base.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import javax.servlet.http.HttpServletResponse;
import com.alibaba.fastjson.JSONObject;
import com.ljb.entity.ApiCollect;
import com.ljb.service.ApiCollectService;

/**
 * 商品收藏Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apicollect")
@MenuDescription(group="系统管理",name="商品收藏", action ="api/apicollect.html")
public class ApiCollectController extends BaseController {
    @Autowired
    private ApiCollectService apiCollectService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="商品收藏列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiCollectService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看商品收藏")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiCollect apiCollect  = apiCollectService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiCollect));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="商品收藏详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiCollect  = apiCollectService.selectMapById(id);
        return R.ok().put( apiCollect);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加商品收藏")
    public R save(@Validated(AddGroup.class) @RequestBody ApiCollect apiCollect) {
        apiCollectService.save(apiCollect);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改商品收藏")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiCollect apiCollect) {
        apiCollectService.update(apiCollect);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除商品收藏")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiCollectService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="商品收藏导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiCollectService.export(jsonObject,response);
    }
}
