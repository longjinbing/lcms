package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.Dept;

import java.util.List;
import java.util.Map;

/**
 * 部门管理Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
public interface DeptService extends BaseService<Dept,Long> {
    public List<Map<String,Object>> treeList();
}
