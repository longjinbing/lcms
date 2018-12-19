package com.ljb.security;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/12<br>
 * 描述: <br>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultGrantedAuthority implements GrantedAuthority {
    private static final long serialVersionUID = 510L;
    private String role;

    public DefaultGrantedAuthority(){}

    public DefaultGrantedAuthority(String role) {
        Assert.hasText(role, "A granted authority textual representation is required");
        this.role = role;
    }

    public String getAuthority() {
        return this.role;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else {
            return obj instanceof DefaultGrantedAuthority ? this.role.equals(((DefaultGrantedAuthority)obj).role) : false;
        }
    }

    public int hashCode() {
        return this.role.hashCode();
    }

    public String toString() {
        return this.role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

