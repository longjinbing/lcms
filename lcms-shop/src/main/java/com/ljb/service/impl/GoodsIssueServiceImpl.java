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
import com.ljb.dao.GoodsIssueDao;
import com.ljb.entity.GoodsIssue;
import com.ljb.service.GoodsIssueService;

/**
 * 商品承若管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-13
 */
@Service
public class GoodsIssueServiceImpl extends BaseServiceImpl implements GoodsIssueService {
    @Autowired
    private GoodsIssueDao goodsIssueDao;

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = goodsIssueDao.selectMapList(query);
        Long total = goodsIssueDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public GoodsIssue selectById(Long id) {
        return goodsIssueDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return goodsIssueDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return goodsIssueDao.deleteBatchIds(ids);
    }

    @Override
    public int save(GoodsIssue entity) {
        return goodsIssueDao.insert(entity);
    }

    @Override
    public int update(GoodsIssue entity) {
        return goodsIssueDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = goodsIssueDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "商品承若管理.xlsx", data);
    }
}
