package com.ljb.service;

import com.ljb.entity.QuestionModel;
import com.ljb.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/16<br>
 * 描述: <br>
 */
public interface ApiHealthService  {
    List<Map<String,Object>> questionList();

    Map<String,Object> category(Long id);

    List<Map<String,Object>> constitutionList();


    Map<String,Object> save(QuestionModel questionModel);

    Map<String,Object> last();

    PageUtils resultList(Map<String,Object> params);

    Map<String,Object> result(Long id);

}
