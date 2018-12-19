package com.ljb.service;

import com.ljb.entity.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.Set;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/11<br>
 * 描述: <br>
 */

public interface AccountService{

    User findByUsername(String username);

    User findByCode(String code);

    User findById(Integer id);

    Set<GrantedAuthority> authorities(Long id);

    List<String> roles(Long id);

    List<String> actions(Long id);

    List<String> allActions();

    void logout();
}

