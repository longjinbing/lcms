package com.ljb.security;

import com.ljb.entity.User;
import com.ljb.service.AccountService;
import com.ljb.utils.ApplicationUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/9<br>
 * 描述: <br>
 */

public class UserDetailsServiceImpl implements UserDetailsService {

    private Log logger = LogFactory.getLog(getClass());

    @Override
    public UserDetails loadUserByUsername(String username){
        // TODO 根据用户名，查找到对应的密码，与权限
        AccountService accountService= ApplicationUtils.getBean(AccountService.class);
        User user=accountService.findByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("用户名不存在");
        }
        UserInfo userInfo = new UserInfo(user);
        return userInfo;
    }
}
