package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.TestResult;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.TestResultService;
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
 * 体质测试结果Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */

@RestController
@RequestMapping("health/testresult")
@MenuDescription(group="健康中心",name="体质测试结果", action ="health/testresult.html")
public class TestResultController extends BaseController {
    @Autowired
    private TestResultService testResultService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="体质测试结果列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( testResultService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看体质测试结果")
    public R info(@NotNull @PathVariable("id") Long id) {
        TestResult testResult  = testResultService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(testResult));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="体质测试结果详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> testResult  = testResultService.selectMapById(id);
        return R.ok().put( testResult);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加体质测试结果")
    public R save(@Validated(AddGroup.class) @RequestBody TestResult testResult) {
        testResultService.save(testResult);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改体质测试结果")
    public R update(@Validated(UpdateGroup.class) @RequestBody TestResult testResult) {
        testResultService.update(testResult);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除体质测试结果")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        testResultService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="体质测试结果导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        testResultService.export(jsonObject,response);
    }
}
