package com.ljb.dao;

import com.ljb.entity.OriginalColumn;
import com.ljb.entity.Table;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/10/9<br>
 * 描述: <br>
 */
@Repository
public interface TableDao {

    List<Table> queryList(Map<String, Object> map);

    Long selectTotal(Map<String,Object> map);

    Table queryTable(String tableName);

    List<OriginalColumn> queryColumns(String tableName);
}
