package com.ljb.controller;

import com.ljb.Base.BaseController;
import com.ljb.annotion.MenuDescription;
import com.ljb.annotion.SafetyGrade;
import com.ljb.entity.User;
import com.ljb.security.IdentityUtils;
import com.ljb.service.UserService;
import com.ljb.utils.R;
import com.ljb.utils.SHA256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户Controller
 *
 * @author longjinbn
 * @email 1106335838@qq.com
 * @date 2018-10-11 21:43:47
 */

@RestController
@RequestMapping("userinfo")
@MenuDescription(group="个人中心",name="用户信息", action ="admin/userinfo.html")
public class UserCenterController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/info")
    @MenuDescription(name="登录信息", safetyGrade = SafetyGrade.COMMON)
    public R info() {
        User user  = userService.selectById(getUserId());
        Map<String,Object> map=new HashMap<>();
        user.setPassword("");
        map.put("user", user);
        map.put("roles", IdentityUtils.getIdentity().getRoles());
        return R.ok().put(map);
    }

    @RequestMapping("/updatepass")
    @MenuDescription(name="修改密码", safetyGrade = SafetyGrade.COMMON)
    public R update(@RequestParam("password") String password,@RequestParam("confirmpassword") String confirmpassword) {
        if(!password.equals(confirmpassword)||password.length()<6){
            return R.error(-1, "密码不符合要求，请重新尝试");
        }
        User user=new User();
        user.setId(IdentityUtils.getUserId());
        user.setPassword(SHA256Util.getSHA256Str(password));
        userService.update(user);
        return R.ok("修改成功");
    }

}
