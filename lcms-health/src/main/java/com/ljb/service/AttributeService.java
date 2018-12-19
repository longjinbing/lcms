package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.Attribute;
import com.ljb.model.SortUpdateModel;

/**
 * 体质属性Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-16
 */
public interface AttributeService extends BaseService<Attribute,Long> {
    Boolean sortTop(SortUpdateModel sortUpdateModel);

    Boolean sortUp(SortUpdateModel sortUpdateModel);

    Boolean sortDown(SortUpdateModel sortUpdateModel);
}
