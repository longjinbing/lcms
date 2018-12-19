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
import com.ljb.dao.SmsLogDao;
import com.ljb.entity.SmsLog;
import com.ljb.service.SmsLogService;

/**
 * 短信日志管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@Service
public class SmsLogServiceImpl extends BaseServiceImpl implements SmsLogService {
    @Autowired
    private SmsLogDao smsLogDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = smsLogDao.selectMapList(query);
        Long total = smsLogDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public SmsLog selectById(Long id) {
        return smsLogDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return smsLogDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return smsLogDao.deleteBatchIds(ids);
    }

    @Override
    public int save(SmsLog entity) {
        return smsLogDao.insert(entity);
    }

    @Override
    public int update(SmsLog entity) {
        return smsLogDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = smsLogDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "短信日志管理.xlsx", data);
    }
}
