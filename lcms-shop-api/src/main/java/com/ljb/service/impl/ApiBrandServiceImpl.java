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
import com.ljb.dao.ApiBrandDao;
import com.ljb.entity.ApiBrand;
import com.ljb.service.ApiBrandService;

/**
 * 品牌管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiBrandServiceImpl extends BaseServiceImpl implements ApiBrandService {
    @Autowired
    private ApiBrandDao apiBrandDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiBrandDao.selectMapList(query);
        Long total = apiBrandDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiBrand selectById(Long id) {
        return apiBrandDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiBrandDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiBrandDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiBrand entity) {
        return apiBrandDao.insert(entity);
    }

    @Override
    public int update(ApiBrand entity) {
        return apiBrandDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiBrandDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "品牌管理.xlsx", data);
    }
}
