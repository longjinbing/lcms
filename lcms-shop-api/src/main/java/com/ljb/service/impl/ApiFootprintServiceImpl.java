package com.ljb.service.impl;

import com.ljb.Base.BaseServiceImpl;
import com.ljb.utils.*;
import com.ljb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.http.HttpServletResponse;
import com.ljb.dao.ApiFootprintDao;
import com.ljb.entity.ApiFootprint;
import com.ljb.service.ApiFootprintService;

/**
 * 用户足迹Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiFootprintServiceImpl extends BaseServiceImpl implements ApiFootprintService {
    @Autowired
    private ApiFootprintDao apiFootprintDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiFootprintDao.selectMapList(query);
        Long total = apiFootprintDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiFootprint selectById(Long id) {
        return apiFootprintDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiFootprintDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiFootprintDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiFootprint entity) {
        return apiFootprintDao.insert(entity);
    }

    @Override
    public int update(ApiFootprint entity) {
        return apiFootprintDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiFootprintDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "用户足迹.xlsx", data);
    }
}
