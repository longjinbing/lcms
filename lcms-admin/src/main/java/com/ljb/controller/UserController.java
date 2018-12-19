package com.ljb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.annotion.SafetyGrade;
import com.ljb.entity.User;
import com.ljb.model.AddGroup;
import com.ljb.model.UpdateGroup;
import com.ljb.service.UserService;
import com.ljb.utils.BeanUtils;
import com.ljb.utils.R;
import com.ljb.utils.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * 系统用户管理Controller
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */

@RestController
@RequestMapping("manage/user")
@MenuDescription(group="用户中心",name="系统用户", action ="manage/user.html")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/resetpass")
    @MenuDescription(name="重置密码")
    public R update(@RequestParam("userId") Long userId,@RequestParam("password") String password,@RequestParam("confirmpassword") String confirmpassword) {
        if(!password.equals(confirmpassword)||password.length()<6){
            return R.error(-1, "密码不符合要求，请重新尝试");
        }
        User user=new User();
        user.setId(userId);
        user.setPassword(SHA256Util.getSHA256Str(password));
        userService.update(user);
        return R.ok("重置成功");
    }
    /**
     * 查看信息
     */
    @RequestMapping("/number")
    @MenuDescription(name="根据学号获取学生ID", safetyGrade = SafetyGrade.COMMON )
    public R number(@RequestParam String number) {
        User user  = userService.findByCode(number);
        if(user==null){
            return R.error();
        }
        return R.ok().put( user.getId());
    }


    /**
     * 查看列表
     */
    @RequestMapping("/list")
    @MenuDescription(name="系统用户管理列表")
    public R list(@RequestParam Map<String, Object> params) {
        //查询列表数据
        return R.ok().put( userService.selectList(params));
    }

    /**
     * 查看信息
     */
    @RequestMapping("/{id}")
    @MenuDescription(name="查看系统用户管理")
    public R info(@NotNull @PathVariable("id") Long id) {
        User user  = userService.selectById(id);
        return R.ok().put( BeanUtils.filteBean(user));
    }

    /**
     * 查看详细信息
     */
    @RequestMapping("/details/{id}")
    @MenuDescription(name="系统用户管理详情")
    public R details(@NotNull @PathVariable("id") Long id) {
        Map<String,Object> user  = userService.selectMapById(id);
        return R.ok().put( user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @MenuDescription(name="添加系统用户管理")
    public R save(@Validated(AddGroup.class) @RequestBody User user) {
        userService.save(user);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @MenuDescription(name="修改系统用户管理")
    public R update(@Validated(UpdateGroup.class) @RequestBody User user) {
        userService.update(user);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @MenuDescription(name="删除系统用户管理")
    public R delete(@NotNull @RequestBody List<Long> ids) {
        userService.deleteBatch(ids);
        return R.ok();
    }

    /**
     * 查看所有列表
     */
    @RequestMapping("/export")
    @MenuDescription(name="系统用户管理导出")
    public void queryAll(@RequestBody JSONObject jsonObject, HttpServletResponse response) {
        userService.export(jsonObject,response);
    }
}
