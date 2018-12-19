package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.ApiAddressDao;
import com.ljb.entity.ApiAddress;
import com.ljb.model.ExportParams;
import com.ljb.service.ApiAddressService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 用户地址Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiAddressServiceImpl extends BaseServiceImpl implements ApiAddressService {
    @Autowired
    private ApiAddressDao apiAddressDao;
    @Autowired
    private HeadTokenUtils headTokenUtils;

    @Override
    public List<Map<String,Object>> addressList(){
        List<Map<String, Object>> list = apiAddressDao.selectMapList(Condition.build().eq("user_id", headTokenUtils.getUserId()));
        return list;
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiAddressDao.selectMapList(query);
        Long total = apiAddressDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiAddress selectById(Long id) {
        return apiAddressDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiAddressDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiAddressDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiAddress entity) {
        return apiAddressDao.insert(entity);
    }

    @Override
    public int update(ApiAddress entity) {
        return apiAddressDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiAddressDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "用户地址.xlsx", data);
    }
}
