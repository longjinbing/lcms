package com.ljb.security;

import com.ljb.entity.User;
import com.ljb.service.AccountService;
import com.ljb.utils.ApplicationUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/9<br>
 * 描述: <br>
 */

public class UserInfo extends Identity implements UserDetails {

    private Boolean accountNonExpired=true;
    private Boolean accountNonLocked=true;
    private Boolean credentialsNonExpired=true;
    private Set<GrantedAuthority> authorities;
    private Boolean enabled=true;
    private String password;

    public UserInfo(){}

    public UserInfo(User user){
        setId(user.getId());
        setUsername(user.getUsername());
        setPassword(user.getPassword());
        if(user.getStatus()==1){
          AccountService accountService=ApplicationUtils.getBean(AccountService.class);
          setAuthorities(accountService.authorities(user.getId()));
          setRoles(accountService.roles(user.getId()));
          setPermissions(accountService.actions(user.getId()));
        }else if(user.getStatus()==2){
            accountNonLocked=false;
        }else if(user.getStatus()==3){
            accountNonExpired=false;
        }else if(user.getStatus()==4){
            credentialsNonExpired=false;
        }else if(user.getStatus()==5){
            enabled=false;
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
