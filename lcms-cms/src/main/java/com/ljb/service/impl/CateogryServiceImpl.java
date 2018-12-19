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
import com.ljb.dao.CateogryDao;
import com.ljb.entity.Cateogry;
import com.ljb.service.CateogryService;

/**
 * 目录管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-08
 */
@Service
public class CateogryServiceImpl extends BaseServiceImpl implements CateogryService {
    @Autowired
    private CateogryDao cateogryDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = cateogryDao.selectMapList(query);
        Long total = cateogryDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public Cateogry selectById(Long id) {
        return cateogryDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return cateogryDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return cateogryDao.deleteBatchIds(ids);
    }

    @Override
    public int save(Cateogry entity) {
        return cateogryDao.insert(entity);
    }

    @Override
    public int update(Cateogry entity) {
        return cateogryDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = cateogryDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "目录管理.xlsx", data);
    }
}
