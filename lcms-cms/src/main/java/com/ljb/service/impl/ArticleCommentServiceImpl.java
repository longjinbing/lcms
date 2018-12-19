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
import com.ljb.dao.ArticleCommentDao;
import com.ljb.entity.ArticleComment;
import com.ljb.service.ArticleCommentService;

/**
 * 文章评论管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-08
 */
@Service
public class ArticleCommentServiceImpl extends BaseServiceImpl implements ArticleCommentService {
    @Autowired
    private ArticleCommentDao articleCommentDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = articleCommentDao.selectMapList(query);
        Long total = articleCommentDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ArticleComment selectById(Long id) {
        return articleCommentDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return articleCommentDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return articleCommentDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ArticleComment entity) {
        return articleCommentDao.insert(entity);
    }

    @Override
    public int update(ArticleComment entity) {
        return articleCommentDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = articleCommentDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "文章评论管理.xlsx", data);
    }
}
