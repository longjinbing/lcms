package com.ljb.service.impl;

import com.ljb.annotion.MenuType;
import com.ljb.dao.*;
import com.ljb.entity.*;
import com.ljb.model.MenuDescriptionModel;
import com.ljb.service.SystemService;
import com.ljb.utils.DateUtils;
import com.ljb.utils.MenuDescriptionUtils;
import com.ljb.utils.PasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/15<br>
 * 描述: <br>
 */
@Service
public class SystemServiceImpl implements SystemService {

    @Autowired
    private MenuDao menuDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleMenuDao roleMenuDao;
    @Autowired
    private MenuDescriptionUtils descBean;
    @Autowired
    private MenuDescriptionUtils menuDescriptionUtils;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void clearData() {
        String sql = "select table_name from information_schema.tables where table_schema = (select database())";
        List<String> result = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("table_name");
            }
        });
        String ignoreTable = "cms_";
        for (Iterator iterator = result.iterator(); iterator.hasNext(); ) {
            String name = iterator.next().toString();
            if (!name.startsWith(ignoreTable)) {
                String deleteSql = "delete from " + name;
                jdbcTemplate.execute(deleteSql);
            }
        }
    }

    public void clearRecyleData() {
        String sql = "select table_name from information_schema.tables where table_schema = (select database())";
        List<String> result = jdbcTemplate.query(sql, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("table_name");
            }
        });
        for (Iterator iterator = result.iterator(); iterator.hasNext(); ) {
            String name = iterator.next().toString();
            String deleteSql = "delete from " + name + " where is_delete=0";
            jdbcTemplate.execute(deleteSql);
        }
    }

    @Override
    public void initSystem() {
        menuDao.delete(null);
        roleMenuDao.delete(null);
        userDao.delete(null);
        userRoleDao.delete(null);
        deptDao.delete(null);
        roleDao.delete(null);
        clearRecyleData();
        User user = new User();
        user.setUsername("longjinbin");
        user.setName("龙锦兵");
        user.setCode("201501014027");
        user.setPassword(PasswordEncoderUtils.encode("123456"));
        user.setStatus(1);
        user.setEmail("1106335838@qq.com");
        user.setCreateId(0L);
        user.setPhone("15879598195");
        userDao.insert(user);
        user.setUpdateId(user.getId());
        user.setCreateId(user.getId());
        userDao.updateById(user);

        Dept dept = new Dept();
        dept.setName("行政");
        dept.setParentId(0L);
        dept.setOrderNum(1);
        dept.setCreateId(user.getId());
        deptDao.insert(dept);

        Role role = null;
        String[] roles = new String[]{"注册会员", "超级管理员"};
        for (int i = 0; i < roles.length; i++) {
            role = new Role();
            role.setName(roles[i]);
            role.setDescription("权限");
            role.setCreateId(user.getId());
            role.setIsDelete(1);
            role.setDeptId(dept.getId());
            roleDao.insert(role);
        }
        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(user.getId());
        userRole.setCreateId(user.getId());
        userRoleDao.insert(userRole);

        List<Menu> menus = new ArrayList<>();
        List<MenuDescriptionModel> ztreeList = menuDescriptionUtils.getMenuList();
        for (MenuDescriptionModel root : ztreeList) {
            List<MenuDescriptionModel> menuList = root.getChildren();
            Menu r = new Menu();
            r.setName(root.getName());
            r.setType(MenuType.GROUP.ordinal());
            r.setParentId(0L);
            r.setIsDelete(1);
            r.setStatus(root.getSafetyGrade().ordinal());
            r.setUrl("#");
            menuDao.insert(r);
            menus.add(r);
            if (menuList != null) {
                for (MenuDescriptionModel menu : menuList) {
                    Menu m = new Menu();
                    m.setName(menu.getName());
                    m.setType(MenuType.MENU.ordinal());
                    m.setParentId(r.getId());
                    m.setUrl(menu.getAction());
                    m.setIsDelete(1);
                    m.setStatus(menu.getSafetyGrade().ordinal());
                    menuDao.insert(m);
                    menus.add(m);
                    List<MenuDescriptionModel> buttonList = menu.getChildren();
                    if (buttonList != null) {
                        for (MenuDescriptionModel button : buttonList) {
                            Menu b = new Menu();
                            b.setName(button.getName());
                            b.setType(MenuType.BUTTON.ordinal());
                            b.setParentId(m.getId());
                            b.setUrl(button.getAction());
                            b.setIsDelete(1);
                            b.setStatus(button.getSafetyGrade().ordinal());
                            menuDao.insert(b);
                            menus.add(b);
                        }
                    }
                }
            }
        }
        for (Menu menu : menus) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(role.getId());
            roleMenu.setMenuId(menu.getId());
            roleMenu.setCreateTime(DateUtils.getSqlDate());
            roleMenu.setCreateId(user.getId());
            roleMenu.setUpdateId(user.getId());
            roleMenu.setUpdateTime(DateUtils.getSqlDate());
            roleMenuDao.insert(roleMenu);
        }

    }

}
