package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.Config;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.ConfigService;
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
 * 系统配置管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */

@RestController
@RequestMapping("config/config")
@MenuDescription(group="配置中心",name="系统配置管理", action ="config/config.html")
public class ConfigController extends BaseController {
    @Autowired
    private ConfigService configService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="系统配置管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( configService.selectList(params));
    }

    @RequestMapping("/list/{item}")
    @MenuDescription(name="查看系统配置管理")
    public R list(@NotNull @PathVariable("item") String item) {
        return R.ok().put(configService.findByItem(item));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看系统配置管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        Config config  = configService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(config));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="系统配置管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> config  = configService.selectMapById(id);
        return R.ok().put( config);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加系统配置管理")
    public R save(@Validated(AddGroup.class) @RequestBody Config config) {
        configService.save(config);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改系统配置管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody Config config) {
        configService.update(config);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除系统配置管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        configService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="系统配置管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        configService.export(jsonObject,response);
    }
}
