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
import com.ljb.dao.ApiSmsLogDao;
import com.ljb.entity.ApiSmsLog;
import com.ljb.service.ApiSmsLogService;

/**
 * 短信日志Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiSmsLogServiceImpl extends BaseServiceImpl implements ApiSmsLogService {
    @Autowired
    private ApiSmsLogDao apiSmsLogDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiSmsLogDao.selectMapList(query);
        Long total = apiSmsLogDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiSmsLog selectById(Long id) {
        return apiSmsLogDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiSmsLogDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiSmsLogDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiSmsLog entity) {
        return apiSmsLogDao.insert(entity);
    }

    @Override
    public int update(ApiSmsLog entity) {
        return apiSmsLogDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiSmsLogDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "短信日志.xlsx", data);
    }
}
