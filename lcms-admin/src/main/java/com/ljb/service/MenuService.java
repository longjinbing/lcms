package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.Menu;
import com.ljb.model.SortUpdateModel;
import com.ljb.model.Tree;

import java.util.List;

/**
 * 菜单管理Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-11
 */
public interface MenuService extends BaseService<Menu,Long> {

    Boolean sortTop(SortUpdateModel sortUpdateModel);

    Boolean sortUp(SortUpdateModel sortUpdateModel);

    Boolean sortDown(SortUpdateModel sortUpdateModel);

    List<Tree> menuList();
}
