package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.LogDao;
import com.ljb.entity.Log;
import com.ljb.entity.LoginLog;
import com.ljb.model.ExportParams;
import com.ljb.security.IdentityUtils;
import com.ljb.service.LogService;
import com.ljb.utils.ExcelUtils;
import com.ljb.utils.PageUtils;
import com.ljb.utils.Query;
import com.ljb.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 系统日志管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@Service
public class LogServiceImpl extends BaseServiceImpl implements LogService {
    @Autowired
    private LogDao logDao;

    @Override
    public void recordLog(Integer status){
        LoginLog loginLog=new LoginLog();
        if(IdentityUtils.isAuthenticated()){
            loginLog.setCreateId(IdentityUtils.getUserId());
        }
        loginLog.setResult("");
        if(status==1){
            loginLog.setResult("登录成功");
            loginLog.setRemark("网站登录");
        }else{
            loginLog.setResult("登录失败");
            loginLog.setRemark("网站登录");
        }
        /*loginLog.setIp(RequestUtils.getIp(request));
        loginLog.setDevice(RequestUtils.getOsAndBrowserInfo(request));
        loginLogDao.insert(loginLog);
        if( tokenDao.selectByMap(Condition.build().eq("create_id", getUserId()))!=null){
            tokenDao.deleteByMap(Condition.build().eq("create_id", getUserId()));
        }
        Token token=new Token();
        token.setId(IdentityUtils.getUserId());
        token.setCreateId(IdentityUtils.getUserId());
        token.setRemark("在线");
        tokenDao.insert(token);*/
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = logDao.selectMapList(query);
        Long total = logDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public Log selectById(Long id) {
        return logDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return logDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return logDao.deleteBatchIds(ids);
    }

    @Override
    public int save(Log entity) {
        return logDao.insert(entity);
    }

    @Override
    public int update(Log entity) {
        return logDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = logDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "系统日志管理.xlsx", data);
    }
}
