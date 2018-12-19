package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.ConfigDao;
import com.ljb.entity.Config;
import com.ljb.model.ExportParams;
import com.ljb.service.ConfigService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 系统配置管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@Service
public class ConfigServiceImpl extends BaseServiceImpl implements ConfigService {
    @Autowired
    private ConfigDao configDao;

    @Override
    public List<Map<String, Object>> findByItem(String item) {
        return configDao.findByItem(item);
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        Integer offset = Integer.valueOf(map.getOrDefault("offset", 0).toString());
        Integer limit = Integer.valueOf(map.getOrDefault("limit", 10).toString());
        List<Map<String, Object>> list = configDao.selectMapList(null);
        list = TreeUtils.buildMapTree(list);
        list = list.stream().skip(offset).limit(limit).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(list, Long.valueOf(list.size()), query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public Config selectById(Long id) {
        return configDao.selectById(id);
    }

    @Override
    public Map<String, Object> selectMapById(Long id) {
        return configDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return configDao.deleteBatchIds(ids);
    }

    @Override
    public int save(Config entity) {
        if (entity.getParentId() != null && entity.getParentId() != 0) {
            Integer value = configDao.findMaxByparentId(entity.getParentId());
            value = value != null ? value : -1;
            entity.setValue(String.valueOf(value + 1));
        }
        return configDao.insert(entity);
    }

    @Override
    public int update(Config entity) {
        return configDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(jsonObject);
        List<Map<String, Object>> list = configDao.selectMapList(exportParams.getQueryParams());
        byte[] data = ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "系统配置管理.xlsx", data);
    }
}
