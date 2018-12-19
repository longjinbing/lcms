package com.ljb.controller;

import com.ljb.Base.BaseController;
import com.ljb.entity.QuestionModel;
import com.ljb.service.ApiHealthService;
import com.ljb.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 体质测试问题Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@Api(value = "体质测试模块")
@RestController
@RequestMapping("api/health")
public class ApiHealthController extends BaseController {

    @Autowired
    private ApiHealthService apiHealthService;

    @ApiOperation(value = "获取体质测试问题")
    @GetMapping("/question")
    public R testQuestion() {
        return R.ok().put(apiHealthService.questionList());
    }

    @ApiOperation(value = "体质列表")
    @GetMapping("/constitution/list")
    public R constitutionList() {
        return R.ok().put(apiHealthService.constitutionList());
    }

    @ApiOperation(value = "通过体质ID获取体质详情")
    @ApiImplicitParam(name = "id",paramType = "path",required = true,dataType = "Long")
    @GetMapping("/constitution/{id}")
    public R constitution(@PathVariable Long id) {
        return R.ok().put(apiHealthService.category(id));
    }

    @ApiOperation(value = "接收体质测试问卷并返回测试结果信息")
    @PostMapping("/save")
    public R save(@RequestBody QuestionModel questionModel) {
        return R.ok().put(apiHealthService.save(questionModel));
    }

    @ApiOperation(value = "获取用户最后一次体质测试结果")
    @GetMapping("/last")
    public R last() {
        return R.ok().put(apiHealthService.last());
    }

    @ApiOperation(value = "获取用户所有的体质测试结果")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "起始记录数", name = "offset", required = false, paramType = "query", dataType = "int", defaultValue = "0"),
            @ApiImplicitParam(value = "获取记录条数", name = "limit", required = false, paramType = "query", dataType = "int", defaultValue = "10")
    })
    @GetMapping("/result/list")
    public R last(@RequestParam Map<String, Object> params) {
        return R.ok().put(apiHealthService.resultList(params));
    }

    @ApiOperation(value = "根据测试结果ID获取详细体质测试结果")
    @ApiImplicitParam(value = "体质测试结果ID", name = "id", paramType = "path", dataType = "Long", required = true)
    @GetMapping("/result/{id}")
    public R last(@PathVariable Long id) {
        return R.ok().put(apiHealthService.result(id));
    }

}
