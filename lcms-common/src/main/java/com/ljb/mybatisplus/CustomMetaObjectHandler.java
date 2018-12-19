package com.ljb.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ljb.security.IdentityUtils;
import com.ljb.utils.DateUtils;
import org.apache.ibatis.reflection.MetaObject;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/9<br>
 * 描述: <br>
 */
public class CustomMetaObjectHandler implements MetaObjectHandler {
    //新增填充
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("createTime", metaObject);
        Object createId= getFieldValByName("createId", metaObject);
        Object updateTime = getFieldValByName("updateTime", metaObject);
        Object updateId= getFieldValByName("updateId", metaObject);
        //获取当前登录用户
        boolean b=IdentityUtils.isAuthenticated();
        if(IdentityUtils.isAuthenticated()&&createId==null){
            setFieldValByName("updateId",IdentityUtils.getUserId(),metaObject);
            setFieldValByName("createId",IdentityUtils.getUserId(),metaObject);
        }
        if (createTime==null) {
            setFieldValByName("updateTime",DateUtils.getSqlDate(),metaObject);
            setFieldValByName("createTime",DateUtils.getSqlDate(),metaObject);
        }
    }

    //更新填充
    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("updateTime", metaObject);
        Object updateId= getFieldValByName("updateId", metaObject);
        //获取当前登录用户
        if(IdentityUtils.isAuthenticated()&&updateId==null){
            setFieldValByName("updateId",IdentityUtils.getUserId(),metaObject);
        }
        if (updateTime==null) {
            setFieldValByName("updateTime",DateUtils.getSqlDate(),metaObject);
        }
    }
}
