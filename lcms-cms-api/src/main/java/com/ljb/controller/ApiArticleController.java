package com.ljb.controller;

import com.ljb.Base.BaseController;
import com.ljb.entity.ApiArticle;
import com.ljb.service.ApiArticleService;
import com.ljb.service.ApiCateogryService;
import com.ljb.utils.BeanUtils;
import com.ljb.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * 文章管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */

@Api(value = "内容模块")
@RestController
@RequestMapping("api/article")
public class ApiArticleController extends BaseController {
    @Autowired
    private ApiArticleService apiArticleService;
    @Autowired
    private ApiCateogryService apiCateogryService;


    @ApiOperation(value = "文章分类列表")
    @GetMapping("/category")
    public R categoryList() {
        //查询列表数据
        return R.ok().put(apiCateogryService.selectList() );
    }

    /**
     * 查看列表
     */
    @ApiOperation(value = "根据文章类别获取文章列表")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始记录数", name = "offset", required = false, paramType = "query", dataType = "int", defaultValue = "0"),
            @ApiImplicitParam(value = "获取记录条数", name = "limit", required = false, paramType = "query", dataType = "int", defaultValue = "10"),
            @ApiImplicitParam(value = "文章类型ID", name = "categoryId", required = true, paramType = "query", dataType = "long")
    })
    @GetMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiArticleService.selectList(params));
    }

    @ApiOperation(value = "根据关键词搜索文章")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始记录数", name = "offset", required = false, paramType = "query", dataType = "int", defaultValue = "0"),
            @ApiImplicitParam(value = "获取记录条数", name = "limit", required = false, paramType = "query", dataType = "int", defaultValue = "10"),
            @ApiImplicitParam(name = "search",value = "关键词",paramType = "query",required = true,dataType = "string")
    })
    @GetMapping("/search")
    public R search(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( apiArticleService.selectList(params));
    }

    /**
     * 查看信息
     */

    @ApiOperation(value = "获取文章详细内容")
    @ApiImplicitParam(name = "id",value = "文章ID",paramType = "path",required = true,dataType = "Long")
    @GetMapping("/details/{id}")
    public R info(@NotNull @PathVariable("id") Long id) {
        ApiArticle apiArticle  = apiArticleService.selectById(id);
        apiArticle.setViewCount(apiArticle.getViewCount()!=null?apiArticle.getViewCount()+1:1);
        apiArticleService.update(apiArticle);
        return R.ok().put( BeanUtils.filteBean(apiArticle));
    }

}
