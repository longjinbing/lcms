package com.ljb.service.impl;

import com.ljb.dao.TableDao;
import com.ljb.entity.GeneratorConfig;
import com.ljb.entity.OriginalColumn;
import com.ljb.entity.Table;
import com.ljb.service.SysGeneratorService;
import com.ljb.utils.GenUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

@Service
public class SysGeneratorServiceImpl implements SysGeneratorService {

	@Autowired
    private TableDao tableDao;

    @Override
    public List<Table> queryList(Map<String, Object> map) {
        return tableDao.queryList(map);
    }

    @Override
    public Long selectTotal(Map<String, Object> map) {
        return tableDao.selectTotal(map);
    }

    @Override
    public Table queryTable(String tableName) {
         return tableDao.queryTable(tableName);
    }

    @Override
    public List<OriginalColumn> queryColumns(String tableName) {
        return tableDao.queryColumns(tableName);
    }

    @Override
    public byte[] generatorCode(GeneratorConfig generatorConfig) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        List<String> tableNames = generatorConfig.getTables();
        for (String tableName : tableNames) {
            //查询表信息
            Table table = queryTable(tableName);
            //查询列信息
            List<OriginalColumn> columns = queryColumns(tableName);
            //生成代码
            GenUtils.generatorCode(table, columns, zip,generatorConfig);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

}
