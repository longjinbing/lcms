package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.TestQuestionDao;
import com.ljb.entity.TestQuestion;
import com.ljb.model.ExportParams;
import com.ljb.service.TestQuestionService;
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
 * 体质测试问题Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@Service
public class TestQuestionServiceImpl extends BaseServiceImpl implements TestQuestionService {
    @Autowired
    private TestQuestionDao testQuestionDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = testQuestionDao.selectMapList(query);
        Long total = testQuestionDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public TestQuestion selectById(Long id) {
        return testQuestionDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return testQuestionDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return testQuestionDao.deleteBatchIds(ids);
    }

    @Override
    public int save(TestQuestion entity) {
        return testQuestionDao.insert(entity);
    }

    @Override
    public int update(TestQuestion entity) {
        return testQuestionDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = testQuestionDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "体质测试问题.xlsx", data);
    }
}
