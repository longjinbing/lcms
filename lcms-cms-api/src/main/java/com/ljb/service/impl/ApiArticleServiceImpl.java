package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.ApiArticleDao;
import com.ljb.entity.ApiArticle;
import com.ljb.model.ExportParams;
import com.ljb.service.ApiArticleService;
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
 * 文章管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiArticleServiceImpl extends BaseServiceImpl implements ApiArticleService {
    @Autowired
    private ApiArticleDao apiArticleDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiArticleDao.selectMapList(query);
        Long total = apiArticleDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiArticle selectById(Long id) {
        return apiArticleDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiArticleDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiArticleDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiArticle entity) {
        return apiArticleDao.insert(entity);
    }

    @Override
    public int update(ApiArticle entity) {
        return apiArticleDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiArticleDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "文章管理.xlsx", data);
    }
}
