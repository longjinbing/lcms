package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.Log;

/**
 * 系统日志管理Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
public interface LogService extends BaseService<Log,Long> {
    void recordLog(Integer status);
}
