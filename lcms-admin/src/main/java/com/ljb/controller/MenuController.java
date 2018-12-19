package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.annotion.SafetyGrade;
import com.ljb.entity.Menu;
import com.ljb.model.AddGroup;
import com.ljb.model.SortUpdateModel;
import com.ljb.model.UpdateGroup;
import com.ljb.service.MenuService;
import com.ljb.service.SystemService;
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
 * 菜单管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */

@RestController
@RequestMapping("manage/menu")
@MenuDescription(group="用户中心",name="菜单管理", action ="manage/menu.html")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private SystemService systemService;

    @RequestMapping("/menulist")
    @MenuDescription(name="用户菜单列表", safetyGrade = SafetyGrade.COMMON)
    public R list() {
        // 查询列表数据
        return R.ok().put( menuService.menuList());
    }

    @RequestMapping("/sorttop")
    @MenuDescription(name="菜单置顶")
    public R sortTop(@RequestBody SortUpdateModel sortUpdateModel) {
        // 查询列表数据
        return R.ok().put(menuService.sortTop(sortUpdateModel) );
    }

    @RequestMapping("/sortup")
    @MenuDescription(name="菜单上移")
    public R sortU(@RequestBody SortUpdateModel sortUpdateModel) {
        // 查询列表数据
        return R.ok().put(menuService.sortUp(sortUpdateModel) );
    }

    @RequestMapping("/sortdown")
    @MenuDescription(name="菜单下移")
    public R sortDown(@RequestBody SortUpdateModel sortUpdateModel) {
        // 查询列表数据
        return R.ok().put(menuService.sortDown(sortUpdateModel) );
    }

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="菜单管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( menuService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看菜单管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        Menu menu  = menuService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(menu));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="菜单管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> menu  = menuService.selectMapById(id);
        return R.ok().put(menu);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加菜单管理")
    public R save(@Validated(AddGroup.class) @RequestBody Menu menu) {
        menuService.save(menu);
        return R.ok().put(menu );
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改菜单管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody Menu menu) {
        menuService.update(menu);
        return R.ok().put(menu );
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除菜单管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        menuService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="菜单管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        menuService.export(jsonObject,response);
    }
}
