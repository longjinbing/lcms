package com.ljb.utils;

import com.ljb.entity.Column;
import com.ljb.entity.GeneratorConfig;
import com.ljb.entity.OriginalColumn;
import com.ljb.entity.Table;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器   工具类
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017年1月3日 下午6:35:28
 */
public class GenUtils {

    public static List<String> getTemplates() {
        List<String> templates = new ArrayList<String>();
        templates.add("template/Entity.java.vm");
        templates.add("template/Dao.java.vm");
        templates.add("template/Mapper.xml.vm");
        templates.add("template/Service.java.vm");
        templates.add("template/ServiceImpl.java.vm");
        templates.add("template/Controller.java.vm");
        templates.add("template/List.html.vm");
        templates.add("template/List.js.vm");
        return templates;
    }

    /**
     * 生成代码
     */
    public static void generatorCode(Table table,
                                     List<OriginalColumn> originalColumns, ZipOutputStream zip, GeneratorConfig generatorConfig) {
        //配置信息
        Configuration config = getConfig(generatorConfig);
        //表名转换成Java类名并去除数据库中表前缀，若配置了tablePrefix属性
        String tablePrefix = generatorConfig.getTablePrefix();
        String className = tableToJava(table.getOriginalTableName(), tablePrefix,generatorConfig.getClassPrefix());
        table.setPathName(className.toLowerCase());
        table.setEntityName(className);
        table.setVariableName((new StringBuilder()).append(Character.toLowerCase(className.charAt(0))).append(className.substring(1)).toString());

        //列信息
        List<Column> columsList = new ArrayList<Column>();
        for (OriginalColumn originalColumn : originalColumns) {
            Column column = new Column();
            //列名转换为JAVA列名
            String columnName = columnToJava(originalColumn.getOriginalColumnName());
            column.setJavaName(columnName);
            columnName = (new StringBuilder()).append(Character.toLowerCase(columnName.charAt(0))).append(columnName.substring(1)).toString();
            //列的数据类型，转换成Java类型
            String dataType = config.getString(originalColumn.getOriginalDataType(), "String");
            column.setColumnName(columnName);
            column.setOriginalDataType(originalColumn.getOriginalDataType());
            column.setDataType(dataType);
            column.setComment(originalColumn.getColumnComment());
            column.setOriginalColumnName(originalColumn.getOriginalColumnName());
            column.setAutoIncrement(originalColumn.isAutoIncrement());
            column.setPrimaryKey(originalColumn.isPrimaryKey());
            column.setNullable(originalColumn.isNullable());
            //未判断主键是否自增
            columsList.add(column);
            if (originalColumn.isPrimaryKey())
                table.setPk(column);
        }
        table.setColumns(columsList);

        //若没主键
        if (table.getPk() == null) {
            //设置columnName为id的为主键
            boolean flag = true;
            for (Column column : table.getColumns()) {
                if ("id".equals(column.getColumnName())) {
                    table.setPk(column);
                    flag = false;
                    break;
                }
            }
            //若无id字段则第一个字段为主键
            if (flag) {
                table.setPk(table.getColumns().get(0));
            }
        }

        //实体类字段列表
        String[] entityIgnoreColumnName = new String[]{"createTime", "createId", "updateId", "updateTime", "id", "isDelete"};
        List<Column> entityColumnList = filterColumn(columsList, Arrays.asList(entityIgnoreColumnName));
        //添加显示字段列表
        String[] displayIgnoreColumnName = new String[]{"createId", "updateId", "createTime", "parentId", "isDelete", "id"};
        List<Column> displayColumnList = filterColumn(columsList, Arrays.asList(displayIgnoreColumnName));
        if (generatorConfig.getJoinQuery()) {
            displayColumnList.add(new Column("updateName", "操作人", "updateName"));
        }
        //添加表单字段列表
        String[] addFormIgnoreColumnName = new String[]{"createTime", "createId", "updateId", "updateTime", "id", "isDelete","orderNum"};
        List<Column> addFormColumnList = filterColumn(columsList, Arrays.asList(addFormIgnoreColumnName));
        //修改表单字段列表
        String[] editFormIgnoreColumnName = new String[]{"createTime", "createId", "updateId", "updateTime", "id", "isDelete","orderNum"};
        List<Column> editFormColumnList = filterColumn(columsList, Arrays.asList(editFormIgnoreColumnName));
        //查询条件表单字段列表
        String[] queryConditionIgnoreColumnName = new String[]{"createTime", "createId", "updateId", "updateTime", "id", "isDelete", "status", "parentId"};
        List<Column> queryConditionColumnList = filterColumn(columsList, Arrays.asList(queryConditionIgnoreColumnName));
        //详情字段列表
        String[] detailsColumnName = new String[]{"createId", "updateId", "id", "isDelete","orderNum"};
        List<Column> detailsColumnList = filterColumn(columsList, Arrays.asList(detailsColumnName));

        String comment = table.getComment();

        //设置velocity资源加载器
        Properties prop = new Properties();
        prop.put("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        Velocity.init(prop);
        //封装模板数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("tableName", table.getOriginalTableName());
        map.put("comment", comment);
        map.put("config", generatorConfig);
        map.put("pk", table.getPk());
        map.put("entityName", table.getEntityName());
        map.put("variableName", table.getVariableName());
        map.put("pathName", table.getPathName());
        map.put("columns", table.getColumns());
        map.put("addFormColumns", addFormColumnList);
        map.put("editFormColumns", editFormColumnList);
        map.put("displayColumns", displayColumnList);
        map.put("detailsColumns", detailsColumnList);
        map.put("queryConditionColumns", queryConditionColumnList);
        map.put("entityColumns", entityColumnList);
        map.put("package", config.getString("packageName"));
        map.put("module", config.getString("module"));
        map.put("menuName", config.getString("menuName"));
        map.put("author", config.getString("author"));
        map.put("email", config.getString("email"));
        map.put("datetime", DateUtils.getSqlDate());
        VelocityContext context = new VelocityContext(map);

        //获取模板列表
        List<String> templates = getTemplates();
        for (String template : templates) {
            //渲染模板
            StringWriter sw = new StringWriter();
            Template tpl = Velocity.getTemplate(template, "UTF-8");
            tpl.merge(context, sw);

            try {
                //添加到zip
                zip.putNextEntry(new ZipEntry(getFileName(template, table.getEntityName(), config.getString("package"), config.getString("module"))));
                IOUtils.write(sw.toString(), zip, "UTF-8");
                IOUtils.closeQuietly(sw);
                zip.closeEntry();
            } catch (IOException e) {
                throw new RRException("渲染模板失败，表名：" + table.getEntityName(), e);
            }
        }
    }


    /**
     * 列名转换成Java属性名
     */
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully(columnName, new char[]{'_'}).replace("_", "");
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return columnToJava(tableName);
    }

    /**
     * 表名转换成Java类名
     */
    public static String tableToJava(String tableName, String tablePrefix,String classPrefix) {
        if (StringUtils.isNotBlank(tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        if (StringUtils.isNotBlank(classPrefix)) {
            tableName = classPrefix+tableName;
        }
        return columnToJava(tableName);
    }

    /**
     * 获取配置信息
     */
    public static Configuration getConfig(GeneratorConfig generatorConfig) {
        Configuration configuration;
        try {
            configuration = new PropertiesConfiguration("generator.properties");
        } catch (ConfigurationException e) {
            throw new RRException("获取配置文件失败，", e);
        }
        if (configuration == null) {
            return null;
        }
        Field[] fields = generatorConfig.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                if (field.getType().getTypeName().equalsIgnoreCase("java.lang.string")) {
                    field.setAccessible(true);
                    String value = field.get(generatorConfig) != null ? field.get(generatorConfig).toString() : null;
                    if (value != null) {
                        configuration.setProperty(field.getName(), value);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return configuration;
    }

    /**
     * 获取文件名
     */
    public static String getFileName(String template, String className, String packageName, String module) {
        String packagePath = "main" + File.separator + "java" + File.separator;
        if (StringUtils.isNotBlank(packageName)) {
            packagePath += packageName.replace(".", File.separator) + File.separator;
        }

        if (template.contains("Entity.java.vm")) {
            return packagePath + "entity" + File.separator + className + ".java";
        }

        if (template.contains("Dao.java.vm")) {
            return packagePath + "dao" + File.separator + className + "Dao.java";
        }

        if (template.contains("Mapper.xml.vm")) {
            return packagePath + "mapper" + File.separator + className + "Mapper.xml";
        }

        if (template.contains("Service.java.vm")) {
            return packagePath + "service" + File.separator + className + "Service.java";
        }

        if (template.contains("ServiceImpl.java.vm")) {
            return packagePath + "service" + File.separator + "impl" + File.separator + className + "ServiceImpl.java";
        }

        if (template.contains("Controller.java.vm")) {
            return packagePath + "controller" + File.separator + className + "Controller.java";
        }

        if (template.contains("List.html.vm")) {
            return "main" + File.separator + "templates" + File.separator + "templates"
                    + File.separator + module + File.separator + className.toLowerCase() + ".html";
        }

        if (template.contains("List.js.vm")) {
            return "main" + File.separator + "templates" + File.separator + "static" + File.separator + module + File.separator + className.toLowerCase() + ".js";
        }

        return null;
    }

    public static List<Column> filterColumn(List<Column> source, List<String> ignoreColumn) {
        List<Column> target = new ArrayList<>();
        Iterator iterator = source.iterator();
        while (iterator.hasNext()) {
            Column column = (Column) iterator.next();
            if (!ignoreColumn.contains(column.getColumnName())) {
                target.add(column);
            }
        }
        return target;
    }
}
