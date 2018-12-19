package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.ApiCateogryDao;
import com.ljb.entity.ApiCateogry;
import com.ljb.model.ExportParams;
import com.ljb.service.ApiCateogryService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 目录Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiCateogryServiceImpl extends BaseServiceImpl implements ApiCateogryService {
    @Autowired
    private ApiCateogryDao apiCateogryDao;

    @Override
    public List<Map<String,Object>> selectList(){
        List<Map<String, Object>> list = apiCateogryDao.selectMapList(null);
        return TreeUtils.buildMapTree(list);
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiCateogryDao.selectMapList(query);
        Long total = apiCateogryDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiCateogry selectById(Long id) {
        return apiCateogryDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiCateogryDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiCateogryDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiCateogry entity) {
        return apiCateogryDao.insert(entity);
    }

    @Override
    public int update(ApiCateogry entity) {
        return apiCateogryDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiCateogryDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "目录.xlsx", data);
    }
}
