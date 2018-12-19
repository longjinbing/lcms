package com.ljb.Base;

import com.ljb.security.IdentityUtils;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/9<br>
 * 描述: <br>
 */
public abstract class BaseServiceImpl{

    public static Long getUserId(){
        return IdentityUtils.getUserId();
    }

}
