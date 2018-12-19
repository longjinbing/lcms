package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.SmsLog;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.SmsLogService;
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
 * 短信日志管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */

@RestController
@RequestMapping("logs/smslog")
@MenuDescription(group="日志中心",name="短信日志管理", action ="logs/smslog.html")
public class SmsLogController extends BaseController {
    @Autowired
    private SmsLogService smsLogService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="短信日志管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( smsLogService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看短信日志管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        SmsLog smsLog  = smsLogService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(smsLog));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="短信日志管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> smsLog  = smsLogService.selectMapById(id);
        return R.ok().put( smsLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加短信日志管理")
    public R save(@Validated(AddGroup.class) @RequestBody SmsLog smsLog) {
        smsLogService.save(smsLog);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改短信日志管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody SmsLog smsLog) {
        smsLogService.update(smsLog);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除短信日志管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        smsLogService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="短信日志管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        smsLogService.export(jsonObject,response);
    }
}
