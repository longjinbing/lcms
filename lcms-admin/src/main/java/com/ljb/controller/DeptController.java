package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.Dept;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.DeptService;
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
 * 部门管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */

@RestController
@RequestMapping("manage/dept")
@MenuDescription(group="用户中心",name="部门管理", action ="manage/dept.html")
public class DeptController extends BaseController {
    @Autowired
    private DeptService deptService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="部门管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( deptService.selectList(params));
    }

    @RequestMapping("/deptlist")
    @MenuDescription(name="部门列表")
    public R zTreelist() {
        //查询列表数据
        return R.ok().put( deptService.treeList());
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看部门管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        Dept dept  = deptService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(dept));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="部门管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> dept  = deptService.selectMapById(id);
        return R.ok().put( dept);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加部门管理")
    public R save(@Validated(AddGroup.class) @RequestBody Dept dept) {
        deptService.save(dept);
        return R.ok().put( dept);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改部门管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody Dept dept) {
        deptService.update(dept);
        return R.ok().put(dept );
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除部门管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        deptService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="部门管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        deptService.export(jsonObject,response);
    }
}
