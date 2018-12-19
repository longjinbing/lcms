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
import com.ljb.dao.ApiTopicCategoryDao;
import com.ljb.entity.ApiTopicCategory;
import com.ljb.service.ApiTopicCategoryService;

/**
 * 主题类型Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiTopicCategoryServiceImpl extends BaseServiceImpl implements ApiTopicCategoryService {
    @Autowired
    private ApiTopicCategoryDao apiTopicCategoryDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiTopicCategoryDao.selectMapList(query);
        Long total = apiTopicCategoryDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiTopicCategory selectById(Long id) {
        return apiTopicCategoryDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiTopicCategoryDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiTopicCategoryDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiTopicCategory entity) {
        return apiTopicCategoryDao.insert(entity);
    }

    @Override
    public int update(ApiTopicCategory entity) {
        return apiTopicCategoryDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiTopicCategoryDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "主题类型.xlsx", data);
    }
}
