package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.annotion.SafetyGrade;
import com.ljb.entity.RoleMenu;
import com.ljb.model.AddGroup;
import com.ljb.model.RoleMenuModel;
import com.ljb.model.UpdateGroup;
import com.ljb.service.RoleMenuService;
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
 * 角色菜单管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */

@RestController
@RequestMapping("manage/rolemenu")
@MenuDescription(group="用户中心",name="角色菜单管理", action ="manage/rolemenu.html",safetyGrade = SafetyGrade.HIDDEN)
public class RoleMenuController extends BaseController {
    @Autowired
    private RoleMenuService roleMenuService;

    @RequestMapping("/rolemenusave")
    @MenuDescription(name="添加角色菜单管理")
    public R save(@RequestBody RoleMenuModel roleMenuModel) {
        roleMenuService.saveRoleMenu(roleMenuModel);
        return R.ok();
    }

    @RequestMapping("/menulist/{id}")
    @MenuDescription(name="角色菜单列表")
    public R list(@PathVariable Long id) {
        //查询列表数据
        return R.ok().put( roleMenuService.roleHasMenuList(id));
    }

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="角色菜单管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( roleMenuService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看角色菜单管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        RoleMenu roleMenu  = roleMenuService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(roleMenu));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="角色菜单管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> roleMenu  = roleMenuService.selectMapById(id);
        return R.ok().put( roleMenu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加角色菜单管理")
    public R save(@Validated(AddGroup.class) @RequestBody RoleMenu roleMenu) {
        roleMenuService.save(roleMenu);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改角色菜单管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody RoleMenu roleMenu) {
        roleMenuService.update(roleMenu);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除角色菜单管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        roleMenuService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="角色菜单管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        roleMenuService.export(jsonObject,response);
    }
}
