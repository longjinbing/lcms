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
import com.ljb.dao.TokenDao;
import com.ljb.entity.Token;
import com.ljb.service.TokenService;

/**
 * 在线用户管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@Service
public class TokenServiceImpl extends BaseServiceImpl implements TokenService {
    @Autowired
    private TokenDao tokenDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = tokenDao.selectMapList(query);
        Long total = tokenDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public Token selectById(Long id) {
        return tokenDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return tokenDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return tokenDao.deleteBatchIds(ids);
    }

    @Override
    public int save(Token entity) {
        return tokenDao.insert(entity);
    }

    @Override
    public int update(Token entity) {
        return tokenDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = tokenDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "在线用户管理.xlsx", data);
    }
}
