package com.ljb.Base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/8<br>
 * 描述: <br>
 */
@Mapper
public interface BaseDao<T,K> extends BaseMapper<T> {

    Integer saveBatch(List<T> entitys);

    Integer updateBatch(List<T> entitys);

    Integer updateByIdSelective(T entity);

    Integer deleteBatch(List<K> ids);

    Integer physicsDeleteBatch(List<K> ids);;

    Map<String, Object> selectMapById(K id);

    List<Map<String, Object>> selectMapList(Map<String, Object> map);

    Long selectTotal(Map<String, Object> map);

    int emptyRecyle();

    List<Map<String, Object>> recyleMapList(Map<String, Object> map);

    Long recyleTotal(Map<String, Object> map);

}
