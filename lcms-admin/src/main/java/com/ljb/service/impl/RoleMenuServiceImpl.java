package com.ljb.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.MenuDao;
import com.ljb.dao.RoleMenuDao;
import com.ljb.entity.Menu;
import com.ljb.entity.RoleMenu;
import com.ljb.model.ExportParams;
import com.ljb.model.MenuModel;
import com.ljb.model.RoleMenuModel;
import com.ljb.service.RoleMenuService;
import com.ljb.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色菜单管理Service实现类
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
@Service
public class RoleMenuServiceImpl extends BaseServiceImpl implements RoleMenuService {
    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private MenuDao menuDao;
    @Override
    public List<Map<String,Object>> roleHasMenuList(Long roleId) {
        List<MenuModel> result = new ArrayList<>();
        List<Menu> allMenu = new ArrayList<>();
        allMenu = menuDao.findByUserId(getUserId());
        List<Menu> hasMenu = menuDao.findByRoleId(roleId);
        List<Map<String,Object>> list=new ArrayList<>();
        for(Menu menu:allMenu){
            Map<String,Object> map=new HashMap<>();
            map.put("id",menu.getId() );
            map.put("parentId",menu.getParentId());
            map.put("name",menu.getName());
            map.put("checked", hasMenu.contains(menu)?"checked":"");
            list.add(map);
        }
        return TreeUtils.buildMapTree(list);
    }

    @Override
    public Boolean saveRoleMenu(RoleMenuModel roleMenuModel) {
        roleMenuDao.deleteByMap(Condition.build().eq("role_id", roleMenuModel.getRoleId()));
        if(roleMenuModel.getMenuIds().size()==0){
            return true;
        }
        List<RoleMenu> list = new ArrayList<>();
        for (Long menuId : roleMenuModel.getMenuIds()) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setId(IdUtil.service().genId());
            roleMenu.setMenuId(menuId);
            roleMenu.setIsDelete(1);
            roleMenu.setRoleId(roleMenuModel.getRoleId());
            roleMenu.setCreateId(getUserId());
            roleMenu.setUpdateId(getUserId());
            roleMenu.setUpdateTime(DateUtils.getSqlDate());
            roleMenu.setCreateTime(DateUtils.getSqlDate());
            list.add(roleMenu);
        }
        return roleMenuDao.saveBatch(list) > 0;
    }



    @Override
    public PageUtils selectList(Map<String, Object> map) {
        Query query = new Query(map);
        List<Map<String, Object>> list = roleMenuDao.selectMapList(query);
        Long total = roleMenuDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public RoleMenu selectById(Long id) {
        return roleMenuDao.selectById(id);
    }

    @Override
    public Map<String,Object> selectMapById(Long id) {
        return roleMenuDao.selectMapById(id);
    }

    @Override
    public int deleteBatch(List<Long> ids) {
        return roleMenuDao.deleteBatchIds(ids);
    }

    @Override
    public int save(RoleMenu entity) {
        return roleMenuDao.insert(entity);
    }

    @Override
    public int update(RoleMenu entity) {
        return roleMenuDao.updateByIdSelective(entity);
    }

    @Override
    public void export(JSONObject jsonObject, HttpServletResponse response){
        ExportParams exportParams=new ExportParams(jsonObject);
        List<Map<String, Object>> list = roleMenuDao.selectMapList(exportParams.getQueryParams());
        byte[] data=ExcelUtils.exportFile(exportParams.getSelectedFields(), list);
        ResponseUtils.responseFile(response, "角色菜单管理.xlsx", data);
    }
}
