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
import com.ljb.dao.ApiAdDao;
import com.ljb.entity.ApiAd;
import com.ljb.service.ApiAdService;

/**
 * 促销广告Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiAdServiceImpl extends BaseServiceImpl implements ApiAdService {
    @Autowired
    private ApiAdDao apiAdDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiAdDao.selectMapList(query);
        Long total = apiAdDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiAd selectById(Long id) {
        return apiAdDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiAdDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiAdDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiAd entity) {
        return apiAdDao.insert(entity);
    }

    @Override
    public int update(ApiAd entity) {
        return apiAdDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiAdDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "促销广告.xlsx", data);
    }
}
