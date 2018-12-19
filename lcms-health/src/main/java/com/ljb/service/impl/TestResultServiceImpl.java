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
import com.ljb.dao.TestResultDao;
import com.ljb.entity.TestResult;
import com.ljb.service.TestResultService;

/**
 * 体质测试结果Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@Service
public class TestResultServiceImpl extends BaseServiceImpl implements TestResultService {
    @Autowired
    private TestResultDao testResultDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = testResultDao.selectMapList(query);
        Long total = testResultDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public TestResult selectById(Long id) {
        return testResultDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return testResultDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return testResultDao.deleteBatchIds(ids);
    }

    @Override
    public int save(TestResult entity) {
        return testResultDao.insert(entity);
    }

    @Override
    public int update(TestResult entity) {
        return testResultDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = testResultDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "体质测试结果.xlsx", data);
    }
}
