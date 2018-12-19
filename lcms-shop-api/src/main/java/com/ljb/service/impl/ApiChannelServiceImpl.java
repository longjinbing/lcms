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
import com.ljb.dao.ApiChannelDao;
import com.ljb.entity.ApiChannel;
import com.ljb.service.ApiChannelService;

/**
 * 商品频道Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-17
 */
@Service
public class ApiChannelServiceImpl extends BaseServiceImpl implements ApiChannelService {
    @Autowired
    private ApiChannelDao apiChannelDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = apiChannelDao.selectMapList(query);
        Long total = apiChannelDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public ApiChannel selectById(Long id) {
        return apiChannelDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return apiChannelDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return apiChannelDao.deleteBatchIds(ids);
    }

    @Override
    public int save(ApiChannel entity) {
        return apiChannelDao.insert(entity);
    }

    @Override
    public int update(ApiChannel entity) {
        return apiChannelDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = apiChannelDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商品频道.xlsx", data);
    }
}
