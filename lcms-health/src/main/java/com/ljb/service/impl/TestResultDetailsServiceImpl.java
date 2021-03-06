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
import com.ljb.dao.TestResultDetailsDao;
import com.ljb.entity.TestResultDetails;
import com.ljb.service.TestResultDetailsService;

/**
 * 体质测试结果详情Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@Service
public class TestResultDetailsServiceImpl extends BaseServiceImpl implements TestResultDetailsService {
    @Autowired
    private TestResultDetailsDao testResultDetailsDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = testResultDetailsDao.selectMapList(query);
        Long total = testResultDetailsDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public TestResultDetails selectById(Long id) {
        return testResultDetailsDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return testResultDetailsDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return testResultDetailsDao.deleteBatchIds(ids);
    }

    @Override
    public int save(TestResultDetails entity) {
        return testResultDetailsDao.insert(entity);
    }

    @Override
    public int update(TestResultDetails entity) {
        return testResultDetailsDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = testResultDetailsDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "体质测试结果详情.xlsx", data);
    }
}
