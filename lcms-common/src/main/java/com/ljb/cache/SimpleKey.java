package com.ljb.cache;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Method;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/11<br>
 * 描述: <br>
 */
public class SimpleKey implements KeyGenerator {

    /**
     * (非 Javadoc)
     * <p>
     * Title: generate
     * </p>
     *
     * @param target
     * @param method
     * @param params
     * @return
     * @see KeyGenerator#generate(Object,
     *      Method, Object[])
     */
    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringBuilder key = new StringBuilder();
        key.append(target.getClass().getSimpleName()).append(":").append(method.getName()).append(":");
        if (params.length == 0) {
            return key.toString();
        }
        for (int i = 0; i < params.length; i++) {
            Object param = params[i];
            if (param == null) {
                del(key);
            } else if (ClassUtils.isPrimitiveArray(param.getClass())) {
                int length = Array.getLength(param);
                for (int j = 0; j < length; j++) {
                    key.append(Array.get(param, j));
                    key.append(',');
                }
            } else if (ClassUtils.isPrimitiveOrWrapper(param.getClass()) || param instanceof String) {
                key.append(param);
            } else {
                key.append(param.toString());
            }
            key.append('-');
        }
        del(key);
        return key.toString();
    }

    private StringBuilder del(StringBuilder stringBuilder) {
        if (stringBuilder.toString().endsWith("-")) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder;
    }
}
