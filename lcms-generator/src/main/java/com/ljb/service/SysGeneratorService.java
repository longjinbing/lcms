package com.ljb.service;

import com.ljb.entity.GeneratorConfig;
import com.ljb.entity.OriginalColumn;
import com.ljb.entity.Table;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年12月19日 下午3:33:38
 */
public interface SysGeneratorService {

    List<Table> queryList(Map<String, Object> map);

    Long selectTotal(Map<String, Object> map);

    Table queryTable(String tableName);

    List<OriginalColumn> queryColumns(String tableName);

    byte[] generatorCode(GeneratorConfig generatorConfig);
}
