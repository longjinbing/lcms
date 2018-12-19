package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.CategoryDao;
import com.ljb.entity.Category;
import com.ljb.model.ExportParams;
import com.ljb.service.CategoryService;
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
 * 体质分类Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
@Service
public class CategoryServiceImpl extends BaseServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = categoryDao.selectMapList(query);
        Long total = categoryDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public List<Map<String,Object>> categoryList(){
        return categoryDao.categoryList();
    }

    @Override
    public Category selectById(Long id) {
        return categoryDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return categoryDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return categoryDao.deleteBatchIds(ids);
    }

    @Override
    public int save(Category entity) {
        return categoryDao.insert(entity);
    }

    @Override
    public int update(Category entity) {
        return categoryDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = categoryDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "体质分类.xlsx", data);
    }
}
