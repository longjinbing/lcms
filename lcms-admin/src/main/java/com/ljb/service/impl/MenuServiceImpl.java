package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.MenuDao;
import com.ljb.entity.Menu;
import com.ljb.model.ExportParams;
import com.ljb.model.SortUpdateModel;
import com.ljb.model.Tree;
import com.ljb.service.MenuService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@Service
public class MenuServiceImpl extends BaseServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;
    @Autowired
    private MenuDescriptionUtils menuDescriptionUtils;

    @Override
    public Boolean sortUp(SortUpdateModel sortUpdateModel) {
        Menu menu= menuDao.selectById(sortUpdateModel.getId());
        Map<String,Object> map=SortUtils.createUpQueryMap(sortUpdateModel);
        map.put("type",menu.getType());
        List<Map<String,Object>> list=menuDao.selectMapList(map);
        Integer orderNum=SortUtils.getOrderNum(list);
        if(orderNum!=menu.getOrderNum()){
            Menu temp=menuDao.selectById(SortUtils.getId(list));
            temp.setOrderNum(menu.getOrderNum());
            menuDao.updateByIdSelective(temp);
            menu.setOrderNum(orderNum);
        }else{
            menu.setOrderNum(orderNum+1);
        }
        return menuDao.updateByIdSelective(menu)==1?true:false;
    }

    @Override
    public Boolean sortDown(SortUpdateModel sortUpdateModel) {
        Menu menu= menuDao.selectById(sortUpdateModel.getId());
        Map<String,Object> map=SortUtils.createDownQueryMap(sortUpdateModel);
        map.put("type",menu.getType());
        List<Map<String,Object>> list=menuDao.selectMapList(map);
        Integer orderNum=SortUtils.getOrderNum(list);
        if(orderNum!=menu.getOrderNum()){
            Menu temp=menuDao.selectById(SortUtils.getId(list));
            temp.setOrderNum(menu.getOrderNum());
            menuDao.updateByIdSelective(temp);
            menu.setOrderNum(orderNum);
        }else{
            menu.setOrderNum(orderNum-1);
        }
        return menuDao.updateByIdSelective(menu)==1?true:false;
    }

    @Override
    public Boolean sortTop(SortUpdateModel sortUpdateModel) {
        Menu menu= menuDao.selectById(sortUpdateModel.getId());
        Map<String,Object> map=SortUtils.createTopQueryMap(sortUpdateModel);
        map.put("type",menu.getType());
        Integer orderNum=SortUtils.getOrderNum(menuDao.selectMapList(map));
        menu.setOrderNum(orderNum+1);
        return menuDao.updateByIdSelective(menu)==1?true:false;
    }


    @Override
    public List<Tree> menuList() {
        List<Tree> trees = TransTreeUtils.transMenuTree(menuDao.menuList(getUserId()));
        return TreeUtils.buildTree(trees);
    }

    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        Integer offset = Integer.valueOf(map.getOrDefault("offset", 0).toString());
        Integer limit = Integer.valueOf(map.getOrDefault("limit", 10).toString());
        List<Map<String, Object>> list = menuDao.selectMapList(null);
        list = TreeUtils.buildMapTree(list);
        list = list.stream().skip(offset).limit(limit).collect(Collectors.toList());
        PageUtils pageUtils = new PageUtils(list, Long.valueOf(list.size()), query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public Menu selectById(Long id) {
        return menuDao.selectById(id);
    }

    @Override
    public Map<String, Object> selectMapById(Long id) {
        return menuDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return menuDao.deleteBatchIds(ids);
    }

    @Override
    public int save(Menu entity) {
        return menuDao.insert(entity);
    }

    @Override
    public int update(Menu entity) {
        return menuDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response) {
        ExportParams exportParams = new ExportParams(jsonObject);
        List<Map<String, Object>> list = menuDao.selectMapList(exportParams.getQueryParams());
        byte[] data = ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "菜单管理.xlsx", data);
    }
}
