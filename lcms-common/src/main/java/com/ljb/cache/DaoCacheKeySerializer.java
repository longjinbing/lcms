package com.ljb.cache;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/12<br>
 * 描述: <br>
 */
public class DaoCacheKeySerializer implements RedisSerializer<String> {
    @Override
    public byte[] serialize(String key) throws SerializationException {
        StringBuilder stringBuilder=new StringBuilder(key);
        int si=stringBuilder.indexOf("SELECT");
        int wi=stringBuilder.lastIndexOf("WHERE");
        if(wi>-1)
            stringBuilder.replace(si, wi+6, "");
        int li=stringBuilder.lastIndexOf(":");
        String cacheKey= li>-1? stringBuilder.substring(0, li):stringBuilder.toString();
        return cacheKey.trim().getBytes();
    }
    @Override
    public String deserialize(byte[] bytes) throws SerializationException {
        return null;
    }

    public static void main(String[] args){
        String s="dao::1002879784:4826399851:com.ljb.dao.SysUserDao.selectList:0:2147483647:SELECT  id,username,nickname,name,password,email,phone,status,number,remark,create_id,create_time,update_id,update_time,is_delete  FROM sys_user  WHERE is_delete=1:MybatisSqlSessionFactoryBean";
        StringBuilder stringBuilder=new StringBuilder(s);
        int si=stringBuilder.indexOf("SELECT");
        int wi=stringBuilder.lastIndexOf("WHERE");
        if(wi>-1)
            stringBuilder.replace(si, wi+5, "");
        int i=stringBuilder.lastIndexOf(":");
        int j=stringBuilder.length();
        stringBuilder.substring(0, stringBuilder.lastIndexOf(":")).trim();
        System.out.print(stringBuilder);
    }
}
