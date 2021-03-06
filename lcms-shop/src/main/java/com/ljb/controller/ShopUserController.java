package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.ShopUser;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.ShopUserService;
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
 * 商城用户管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */

@RestController
@RequestMapping("shopuser/shopuser")
@MenuDescription(group="商城用户",name="商城用户", action ="shopuser/shopuser.html")
public class ShopUserController extends BaseController {
    @Autowired
    private ShopUserService shopUserService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="商城用户管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( shopUserService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看商城用户管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        ShopUser shopUser  = shopUserService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(shopUser));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="商城用户管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> shopUser  = shopUserService.selectMapById(id);
        return R.ok().put( shopUser);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加商城用户管理")
    public R save(@Validated(AddGroup.class) @RequestBody ShopUser shopUser) {
        shopUserService.save(shopUser);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改商城用户管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody ShopUser shopUser) {
        shopUserService.update(shopUser);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除商城用户管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        shopUserService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="商城用户管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        shopUserService.export(jsonObject,response);
    }
}
