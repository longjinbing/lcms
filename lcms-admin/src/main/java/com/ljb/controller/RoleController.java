package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.Role;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.RoleService;
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
 * 角色管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */

@RestController
@RequestMapping("manage/role")
@MenuDescription(group="用户中心",name="角色管理", action ="manage/role.html")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    @RequestMapping("/rolelist/{id}")
    @MenuDescription(name="角色授权")
    public R roleList(@PathVariable Long id) {
        //查询列表数据
        return R.ok().put( roleService.roleListByuserIdAndAdminId(id));
    }

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="角色管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( roleService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看角色管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        Role role  = roleService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(role));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="角色管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> role  = roleService.selectMapById(id);
        return R.ok().put( role);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加角色管理")
    public R save(@Validated(AddGroup.class) @RequestBody Role role) {
        roleService.save(role);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改角色管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody Role role) {
        roleService.update(role);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除角色管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        roleService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="角色管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        roleService.export(jsonObject,response);
    }
}
