package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.AttributeDao;
import com.ljb.entity.Attribute;
import com.ljb.entity.Attribute;
import com.ljb.model.ExportParams;
import com.ljb.model.SortUpdateModel;
import com.ljb.service.AttributeService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 体质属性Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@Service
public class AttributeServiceImpl extends BaseServiceImpl implements AttributeService {
    @Autowired
    private AttributeDao attributeDao;

    @Override
    public Boolean sortUp(SortUpdateModel sortUpdateModel) {
        Attribute article = attributeDao.selectById(sortUpdateModel.getId());
        Map<String, Object> map = SortUtils.createUpQueryMap(sortUpdateModel);
        List<Map<String, Object>> list = attributeDao.selectMapList(map);
        Integer orderNum = SortUtils.getOrderNum(list);
        if (orderNum != article.getOrderNum()) {
            Attribute temp = attributeDao.selectById(SortUtils.getId(list));
            temp.setOrderNum(article.getOrderNum());
            attributeDao.updateByIdSelective(temp);
            article.setOrderNum(orderNum);
        } else {
            article.setOrderNum(orderNum + 1);
        }
        return attributeDao.updateByIdSelective(article) == 1 ? true : false;
    }

    @Override
    public Boolean sortDown(SortUpdateModel sortUpdateModel) {
        Attribute article = attributeDao.selectById(sortUpdateModel.getId());
        Map<String, Object> map = SortUtils.createDownQueryMap(sortUpdateModel);
        List<Map<String, Object>> list = attributeDao.selectMapList(map);
        Integer orderNum = SortUtils.getOrderNum(list);
        if (orderNum != article.getOrderNum()) {
            Attribute temp = attributeDao.selectById(SortUtils.getId(list));
            temp.setOrderNum(article.getOrderNum());
            attributeDao.updateByIdSelective(temp);
            article.setOrderNum(orderNum);
        } else {
            article.setOrderNum(orderNum - 1);
        }
        return attributeDao.updateByIdSelective(article) == 1 ? true : false;
    }

    @Override
    public Boolean sortTop(SortUpdateModel sortUpdateModel) {
        Attribute article = attributeDao.selectById(sortUpdateModel.getId());
        Map<String, Object> map = SortUtils.createTopQueryMap(sortUpdateModel);
        Integer orderNum = SortUtils.getOrderNum(attributeDao.selectMapList(map));
        article.setOrderNum(orderNum + 1);
        return attributeDao.updateByIdSelective(article) == 1 ? true : false;
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = attributeDao.selectMapList(query);
        Long total = attributeDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public Attribute selectById(Long id) {
        return attributeDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return attributeDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return attributeDao.deleteBatchIds(ids);
    }

    @Override
    public int save(Attribute entity) {
        return attributeDao.insert(entity);
    }

    @Override
    public int update(Attribute entity) {
        return attributeDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = attributeDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "体质属性.xlsx", data);
    }
}
