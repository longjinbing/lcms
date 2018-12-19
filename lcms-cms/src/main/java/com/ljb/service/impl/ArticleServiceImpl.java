package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.ArticleDao;
import com.ljb.entity.Article;
import com.ljb.model.ExportParams;
import com.ljb.model.SortUpdateModel;
import com.ljb.service.ArticleService;
import com.ljb.utils.*;
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
 * @date 2018-12-08
 */
@Service
public class ArticleServiceImpl extends BaseServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;

    @Override
    public Boolean publish(List<Long> ids) {
        return articleDao.publish(ids) > 0 ? true : false;
    }

    @Override
    public Boolean stopPublish(List<Long> ids) {
        return articleDao.stopPublish(ids) > 0 ? true : false;
    }

    @Override
    public Boolean sortUp(SortUpdateModel sortUpdateModel) {
        Article article = articleDao.selectById(sortUpdateModel.getId());
        Map<String, Object> map = SortUtils.createUpQueryMap(sortUpdateModel);
        List<Map<String, Object>> list = articleDao.selectMapList(map);
        Integer orderNum = SortUtils.getOrderNum(list);
        if (orderNum != article.getOrderNum()) {
            Article temp = articleDao.selectById(SortUtils.getId(list));
            temp.setOrderNum(article.getOrderNum());
            articleDao.updateByIdSelective(temp);
            article.setOrderNum(orderNum);
        } else {
            article.setOrderNum(orderNum + 1);
        }
        return articleDao.updateByIdSelective(article) == 1 ? true : false;
    }

    @Override
    public Boolean sortDown(SortUpdateModel sortUpdateModel) {
        Article article = articleDao.selectById(sortUpdateModel.getId());
        Map<String, Object> map = SortUtils.createDownQueryMap(sortUpdateModel);
        List<Map<String, Object>> list = articleDao.selectMapList(map);
        Integer orderNum = SortUtils.getOrderNum(list);
        if (orderNum != article.getOrderNum()) {
            Article temp = articleDao.selectById(SortUtils.getId(list));
            temp.setOrderNum(article.getOrderNum());
            articleDao.updateByIdSelective(temp);
            article.setOrderNum(orderNum);
        } else {
            article.setOrderNum(orderNum - 1);
        }
        return articleDao.updateByIdSelective(article) == 1 ? true : false;
    }

    @Override
    public Boolean sortTop(SortUpdateModel sortUpdateModel) {
        Article article = articleDao.selectById(sortUpdateModel.getId());
        Map<String, Object> map = SortUtils.createTopQueryMap(sortUpdateModel);
        Integer orderNum = SortUtils.getOrderNum(articleDao.selectMapList(map));
        article.setOrderNum(orderNum + 1);
        return articleDao.updateByIdSelective(article) == 1 ? true : false;
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = articleDao.selectMapList(query);
        Long total = articleDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public Article selectById(Long id) {
        return articleDao.selectById(id);
    }

    @Override
    public Map<String, Object> selectMapById(Long id) {
        return articleDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return articleDao.deleteBatchIds(ids);
    }

    @Override
    public int save(Article entity) {
        entity.setViewCount(0);
        entity.setStatus(0);
        entity.setCommentable(1);
        return articleDao.insert(entity);
    }

    @Override
    public int update(Article entity) {
        return articleDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(jsonObject);
        List<Map<String, Object>> list = articleDao.selectMapList(exportParams.getQueryParams());
        byte[] data = ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "文章管理.xlsx", data);
    }
}
