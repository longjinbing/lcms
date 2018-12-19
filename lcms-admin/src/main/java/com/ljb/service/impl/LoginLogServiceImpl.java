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
import com.ljb.dao.LoginLogDao;
import com.ljb.entity.LoginLog;
import com.ljb.service.LoginLogService;

/**
 * 登录日志管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@Service
public class LoginLogServiceImpl extends BaseServiceImpl implements LoginLogService {
    @Autowired
    private LoginLogDao loginLogDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = loginLogDao.selectMapList(query);
        Long total = loginLogDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public LoginLog selectById(Long id) {
        return loginLogDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return loginLogDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return loginLogDao.deleteBatchIds(ids);
    }

    @Override
    public int save(LoginLog entity) {
        return loginLogDao.insert(entity);
    }

    @Override
    public int update(LoginLog entity) {
        return loginLogDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = loginLogDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "登录日志管理.xlsx", data);
    }
}
