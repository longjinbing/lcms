package com.ljb.controller;


import com.ljb.annotion.MenuDescription;
import com.ljb.entity.GeneratorConfig;
import com.ljb.entity.Table;
import com.ljb.service.SysGeneratorService;
import com.ljb.utils.PageUtils;
import com.ljb.utils.Query;
import com.ljb.utils.R;
import com.ljb.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年1月3日 下午6:35:28
 */
@Controller
@RequestMapping("generator")
@MenuDescription(group="开发工具",name="代码生成",  action ="generator/generator.html")
public class SysGeneratorController {

    @Autowired
    private SysGeneratorService sysGeneratorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="数据库表信息")
    public @ResponseBody
    R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
    	Query query = new Query(params);
        List<Table> list = sysGeneratorService.queryList(query);
        Long total = sysGeneratorService.selectTotal(query);
        PageUtils pageUtil = new PageUtils(list,total ,query.getOffset(),query.getLimit());
        return R.ok().put("data", pageUtil);
    }

    /**
     * 生成代码
     */
    @RequestMapping("/generatrorcode")
    @MenuDescription(name="代码生成")
    public void code(@RequestBody GeneratorConfig generatorConfig, HttpServletRequest request, HttpServletResponse response) throws IOException {
        byte[] data = sysGeneratorService.generatorCode(generatorConfig);
        ResponseUtils.responseFile(response, "AutoCode.zip", data);
    }
}
