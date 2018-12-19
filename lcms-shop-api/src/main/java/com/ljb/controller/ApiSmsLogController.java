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
import com.ljb.entity.ApiSmsLog;
import com.ljb.service.ApiSmsLogService;

/**
 * 短信日志Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apismslog")
@MenuDescription(group="商城API",name="短信日志", action ="api/apismslog.html")
public class ApiSmsLogController extends BaseController {
    @Autowired
    private ApiSmsLogService apiSmsLogService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="短信日志列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiSmsLogService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看短信日志")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiSmsLog apiSmsLog  = apiSmsLogService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiSmsLog));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="短信日志详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiSmsLog  = apiSmsLogService.selectMapById(id);
        return R.ok().put( apiSmsLog);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加短信日志")
    public R save(@Validated(AddGroup.class) @RequestBody ApiSmsLog apiSmsLog) {
        apiSmsLogService.save(apiSmsLog);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改短信日志")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiSmsLog apiSmsLog) {
        apiSmsLogService.update(apiSmsLog);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除短信日志")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiSmsLogService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="短信日志导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiSmsLogService.export(jsonObject,response);
    }
}
