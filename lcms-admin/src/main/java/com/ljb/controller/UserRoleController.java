package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.annotion.SafetyGrade;
import com.ljb.entity.UserRole;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.model.UserRoleModel;
import com.ljb.service.UserRoleService;
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
 * 用户角色管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */

@RestController
@RequestMapping("manage/userrole")
@MenuDescription(group="用户中心",name="用户角色管理", action ="manage/userrole.html",safetyGrade = SafetyGrade.HIDDEN)
public class UserRoleController extends BaseController {
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="用户角色管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( userRoleService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看用户角色管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        UserRole userRole  = userRoleService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(userRole));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="用户角色管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> userRole  = userRoleService.selectMapById(id);
        return R.ok().put( userRole);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加用户角色管理")
    public R save(@Validated(AddGroup.class) @RequestBody UserRole userRole) {
        userRoleService.save(userRole);
        return R.ok();
    }

    @RequestMapping("/saveuserrole")
    @MenuDescription(name="添加用户角色管理")
    public R saveUserRole(@RequestBody UserRoleModel userRoleModel) {
        userRoleService.saveUserRole(userRoleModel);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改用户角色管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody UserRole userRole) {
        userRoleService.update(userRole);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除用户角色管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        userRoleService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="用户角色管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        userRoleService.export(jsonObject,response);
    }

}
