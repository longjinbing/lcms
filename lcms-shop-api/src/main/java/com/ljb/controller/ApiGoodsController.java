package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.ApiGoods;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.ApiGoodsService;
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
 * 商品管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@RestController
@RequestMapping("api/apigoods")
@MenuDescription(group="商城API",name="商品管理", action ="api/apigoods.html")
public class ApiGoodsController extends BaseController {
    @Autowired
    private ApiGoodsService apiGoodsService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="商品管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiGoodsService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看商品管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiGoods apiGoods  = apiGoodsService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(apiGoods));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="商品管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> apiGoods  = apiGoodsService.selectMapById(id);
        return R.ok().put( apiGoods);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加商品管理")
    public R save(@Validated(AddGroup.class) @RequestBody ApiGoods apiGoods) {
        apiGoodsService.save(apiGoods);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改商品管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody ApiGoods apiGoods) {
        apiGoodsService.update(apiGoods);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除商品管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        apiGoodsService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="商品管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        apiGoodsService.export(jsonObject,response);
    }
}
