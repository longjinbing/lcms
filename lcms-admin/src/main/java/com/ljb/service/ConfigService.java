package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.Config;

import java.util.List;
import java.util.Map;

/**
 * 系统配置管理Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
public interface ConfigService extends BaseService<Config,Long> {
    List<Map<String, Object>> findByItem(String item);
}
