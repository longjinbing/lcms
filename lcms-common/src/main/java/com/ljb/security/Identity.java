package com.ljb.security;

import java.util.List;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/12<br>
 * 描述: <br>
 */
public class Identity {
    private Long id;
    private String username;
    private List<String> roles;
    private List<String> permissions;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public Boolean hasRole(String role) {
        return roles.contains(role);
    }

    public Boolean hasAction(String permission) {
        return permissions.contains(permission);
    }

    public List<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<String> permissions) {
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
