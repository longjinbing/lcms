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
import com.ljb.dao.ApiShippingDao;
import com.ljb.entity.ApiShipping;
import com.ljb.service.ApiShippingService;

/**
 * 快递信息Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiShippingServiceImpl extends BaseServiceImpl implements ApiShippingService {
    @Autowired
    private ApiShippingDao apiShippingDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiShippingDao.selectMapList(query);
        Long total = apiShippingDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiShipping selectById(Long id) {
        return apiShippingDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiShippingDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiShippingDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiShipping entity) {
        return apiShippingDao.insert(entity);
    }

    @Override
    public int update(ApiShipping entity) {
        return apiShippingDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiShippingDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "快递信息.xlsx", data);
    }
}
