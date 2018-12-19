package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.GoodsIssue;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.GoodsIssueService;
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
 * 商品承若管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */

@RestController
@RequestMapping("goods/goodsissue")
@MenuDescription(group="商品中心",name="商品承若", action ="goods/goodsissue.html")
public class GoodsIssueController extends BaseController {
    @Autowired
    private GoodsIssueService goodsIssueService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="商品承若管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( goodsIssueService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看商品承若管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        GoodsIssue goodsIssue  = goodsIssueService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(goodsIssue));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="商品承若管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> goodsIssue  = goodsIssueService.selectMapById(id);
        return R.ok().put( goodsIssue);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加商品承若管理")
    public R save(@Validated(AddGroup.class) @RequestBody GoodsIssue goodsIssue) {
        goodsIssueService.save(goodsIssue);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改商品承若管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody GoodsIssue goodsIssue) {
        goodsIssueService.update(goodsIssue);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除商品承若管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        goodsIssueService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="商品承若管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        goodsIssueService.export(jsonObject,response);
    }
}
