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
import com.ljb.entity.ApiAddress;
import com.ljb.service.ApiAddressService;

/**
 * 用户地址Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apiaddress")
@MenuDescription(group="商城API",name="用户地址", action ="api/apiaddress.html")
public class ApiAddressController extends BaseController {
    @Autowired
    private ApiAddressService apiAddressService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="用户地址列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiAddressService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看用户地址")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiAddress apiAddress  = apiAddressService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiAddress));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="用户地址详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiAddress  = apiAddressService.selectMapById(id);
        return R.ok().put( apiAddress);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加用户地址")
    public R save(@Validated(AddGroup.class) @RequestBody ApiAddress apiAddress) {
        apiAddressService.save(apiAddress);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改用户地址")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiAddress apiAddress) {
        apiAddressService.update(apiAddress);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除用户地址")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiAddressService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="用户地址导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiAddressService.export(jsonObject,response);
    }
}
