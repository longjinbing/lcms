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
import com.ljb.entity.ApiUser;
import com.ljb.service.ApiUserService;

/**
 * 商城用户Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apiuser")
@MenuDescription(group="商城API",name="商城用户", action ="api/apiuser.html")
public class ApiUserController extends BaseController {
    @Autowired
    private ApiUserService apiUserService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="商城用户列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiUserService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看商城用户")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiUser apiUser  = apiUserService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiUser));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="商城用户详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiUser  = apiUserService.selectMapById(id);
        return R.ok().put( apiUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加商城用户")
    public R save(@Validated(AddGroup.class) @RequestBody ApiUser apiUser) {
        apiUserService.save(apiUser);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改商城用户")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiUser apiUser) {
        apiUserService.update(apiUser);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除商城用户")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiUserService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="商城用户导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiUserService.export(jsonObject,response);
    }
}
