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
import com.ljb.entity.ApiChannel;
import com.ljb.service.ApiChannelService;

/**
 * 商品频道Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apichannel")
@MenuDescription(group="商城API",name="商品频道", action ="api/apichannel.html")
public class ApiChannelController extends BaseController {
    @Autowired
    private ApiChannelService apiChannelService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="商品频道列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiChannelService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看商品频道")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiChannel apiChannel  = apiChannelService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiChannel));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="商品频道详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiChannel  = apiChannelService.selectMapById(id);
        return R.ok().put( apiChannel);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加商品频道")
    public R save(@Validated(AddGroup.class) @RequestBody ApiChannel apiChannel) {
        apiChannelService.save(apiChannel);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改商品频道")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiChannel apiChannel) {
        apiChannelService.update(apiChannel);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除商品频道")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiChannelService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="商品频道导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiChannelService.export(jsonObject,response);
    }
}
