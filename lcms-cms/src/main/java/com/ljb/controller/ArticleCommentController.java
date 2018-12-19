package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.entity.ArticleComment;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.ArticleCommentService;
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
 * 文章评论管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-08
 */

@RestController
@RequestMapping("cms/articlecomment")
@MenuDescription(group="内容中心",name="评论管理", action ="cms/articlecomment.html")
public class ArticleCommentController extends BaseController {
    @Autowired
    private ArticleCommentService articleCommentService;

    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="文章评论管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put("data", articleCommentService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看文章评论管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        ArticleComment articleComment  = articleCommentService.selectById(id);
        return R.ok().put("data", BeanUtils.filteBean(articleComment));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="文章评论管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> articleComment  = articleCommentService.selectMapById(id);
        return R.ok().put("data", articleComment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加文章评论管理")
    public R save(@Validated(AddGroup.class) @RequestBody ArticleComment articleComment) {
        articleCommentService.save(articleComment);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改文章评论管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody ArticleComment articleComment) {
        articleCommentService.update(articleComment);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除文章评论管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        articleCommentService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="文章评论管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        articleCommentService.export(jsonObject,response);
    }
}
