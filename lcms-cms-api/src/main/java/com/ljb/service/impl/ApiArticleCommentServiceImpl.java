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
import com.ljb.dao.ApiArticleCommentDao;
import com.ljb.entity.ApiArticleComment;
import com.ljb.service.ApiArticleCommentService;

/**
 * 文章评论Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiArticleCommentServiceImpl extends BaseServiceImpl implements ApiArticleCommentService {
    @Autowired
    private ApiArticleCommentDao apiArticleCommentDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiArticleCommentDao.selectMapList(query);
        Long total = apiArticleCommentDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiArticleComment selectById(Long id) {
        return apiArticleCommentDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiArticleCommentDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiArticleCommentDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiArticleComment entity) {
        return apiArticleCommentDao.insert(entity);
    }

    @Override
    public int update(ApiArticleComment entity) {
        return apiArticleCommentDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiArticleCommentDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "文章评论.xlsx", data);
    }
}
