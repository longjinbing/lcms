package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.Token;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.TokenService;
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
 * 在线用户管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */

@RestController
@RequestMapping("manage/token")
@MenuDescription(group="用户中心",name="在线用户", action ="manage/token.html")
public class TokenController extends BaseController {
    @Autowired
    private TokenService tokenService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="在线用户管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( tokenService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看在线用户管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        Token token  = tokenService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(token));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="在线用户管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> token  = tokenService.selectMapById(id);
        return R.ok().put( token);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加在线用户管理")
    public R save(@Validated(AddGroup.class) @RequestBody Token token) {
        tokenService.save(token);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改在线用户管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody Token token) {
        tokenService.update(token);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除在线用户管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        tokenService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="在线用户管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        tokenService.export(jsonObject,response);
    }
}
