package com.ljb.service;

import com.ljb.Base.BaseService;
import com.ljb.entity.Article;
import com.ljb.model.SortUpdateModel;

import java.util.List;

/**
 * 文章管理Service接口
 *
 * @author longjinbin
 * @email 1106335838@qq.com
 * @date 2018-12-08
 */
public interface ArticleService extends BaseService<Article,Long> {
    Boolean sortTop(SortUpdateModel sortUpdateModel);

    Boolean sortUp(SortUpdateModel sortUpdateModel);

    Boolean sortDown(SortUpdateModel sortUpdateModel);

    Boolean publish(List<Long> ids);

    Boolean stopPublish(List<Long> ids);
}
