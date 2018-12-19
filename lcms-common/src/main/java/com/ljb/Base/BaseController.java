package com.ljb.Base;

import com.ljb.security.IdentityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/9<br>
 * 描述: <br>
 */
public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected Long getUserId() {
        return IdentityUtils.getUserId();
    }

}
