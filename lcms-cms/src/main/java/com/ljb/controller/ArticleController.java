package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.annotion.SafetyGrade;
import com.ljb.entity.Article;
import com.ljb.model.AddGroup;
import com.ljb.model.SortUpdateModel;
import com.ljb.model.UpdateGroup;
import com.ljb.service.ArticleService;
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
 * 文章管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-08
 */

@RestController
@RequestMapping("cms/article")
@MenuDescription(group="内容中心",name="文章管理", action ="cms/article.html",safetyGrade = SafetyGrade.HIDDEN)
public class ArticleController extends BaseController {
    @Autowired
    private ArticleService articleService;

    @RequestMapping("/sorttop")
    @MenuDescription(name="文章置顶")
    public R sortTop(@RequestBody SortUpdateModel sortUpdateModel) {
        // 查询列表数据
        return R.ok().put(articleService.sortTop(sortUpdateModel) );
    }

    @RequestMapping("/sortup")
    @MenuDescription(name="文章上移")
    public R sortU(@RequestBody SortUpdateModel sortUpdateModel) {
        // 查询列表数据
        return R.ok().put(articleService.sortUp(sortUpdateModel) );
    }

    @RequestMapping("/sortdown")
    @MenuDescription(name="文章下移")
    public R sortDown(@RequestBody SortUpdateModel sortUpdateModel) {
        // 查询列表数据
        return R.ok().put(articleService.sortDown(sortUpdateModel) );
    }

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="文章管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", articleService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看文章管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        Article article  = articleService.selectById(id);
        return R.ok().put("data", BeanUtils.filteBean(article));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="文章管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> article  = articleService.selectMapById(id);
        return R.ok().put("data", article);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加文章管理")
    public R save(@Validated(AddGroup.class) @RequestBody Article article) {
        articleService.save(article);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改文章管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody Article article) {
        articleService.update(article);
        return R.ok();
    }

    @RequestMapping("/publish")
    @MenuDescription(name="文章发布")
    public R publish(@NotNull @RequestBody List<Long> ids) {
        return R.judge(articleService.publish(ids));
    }

    @RequestMapping("/stopPublish")
    @MenuDescription(name="暂存文章")
    public R stopPublish(@NotNull @RequestBody List<Long> ids) { ;
        return R.judge(articleService.stopPublish(ids));
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除文章管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        articleService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="文章管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        articleService.export(jsonObject,response);
    }
}
