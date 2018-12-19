package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.LoginLog;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.LoginLogService;
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
 * 登录日志管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */

@RestController
@RequestMapping("logs/loginlog")
@MenuDescription(group="日志中心",name="登录日志管理", action ="logs/loginlog.html")
public class LoginLogController extends BaseController {
    @Autowired
    private LoginLogService loginLogService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="登录日志管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( loginLogService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看登录日志管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        LoginLog loginLog  = loginLogService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(loginLog));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="登录日志管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> loginLog  = loginLogService.selectMapById(id);
        return R.ok().put( loginLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加登录日志管理")
    public R save(@Validated(AddGroup.class) @RequestBody LoginLog loginLog) {
        loginLogService.save(loginLog);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改登录日志管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody LoginLog loginLog) {
        loginLogService.update(loginLog);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除登录日志管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        loginLogService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="登录日志管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        loginLogService.export(jsonObject,response);
    }
}
