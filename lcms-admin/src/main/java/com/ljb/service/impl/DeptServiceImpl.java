package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.DeptDao;
import com.ljb.entity.Dept;
import com.ljb.model.ExportParams;
import com.ljb.service.DeptService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 部门管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@Service
public class DeptServiceImpl extends BaseServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;


    @Override
    public List<Map<String,Object>> treeList() {
        return TreeUtils.buildMapTree(deptDao.selectMapList(null),"checked");
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        Integer offset=Integer.valueOf(map.getOrDefault("offset",0).toString());
        Integer limit=Integer.valueOf(map.getOrDefault("limit",10).toString());
        List<Map<String, Object>> list = deptDao.selectMapList(query);
        list=TreeUtils.buildMapTree(list);
        list=list.stream().skip(offset).limit(limit).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(list,Long.valueOf(list.size()), query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public Dept selectById(Long id) {
        return deptDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return deptDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return deptDao.deleteBatchIds(ids);
    }

    @Override
    public int save(Dept entity) {
        return deptDao.insert(entity);
    }

    @Override
    public int update(Dept entity) {
        return deptDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = deptDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "部门管理.xlsx", data);
    }
}
