package com.ljb.entity;


/**
 * 名称：ResultMap <br>
 * 描述：查询表信息返回的BaseResultMap<br>
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2017-09-17 20:20
 */
public class OriginalColumn {
    /**
     * 数据库字段名
     */
    private String originalColumnName;
    /**
     * 字段类型
     */
    private String originalDataType;
    /**
     * 字段注释
     */
    private String columnComment;
    /**
     * 读取数据库主键
     */
    private String primaryKey;
    /**
     * 供外部判断是否是主键
     */
    private Boolean isPrimaryKey;

    private String extra;

    private Boolean nullable;

    /**
     * 供外部判断是否是主键
     */
    private Boolean isAutoIncrement;


    public String getColumnComment() {
        return columnComment;
    }

    public void setColumnComment(String columnComment) {
        this.columnComment = columnComment;
    }

    public String getOriginalColumnName() {
        return originalColumnName;
    }

    public void setOriginalColumnName(String originalColumnName) {
        this.originalColumnName = originalColumnName;
    }

    public String getOriginalDataType() {
        return originalDataType;
    }

    public void setOriginalDataType(String originalDataType) {
        this.originalDataType = originalDataType;
    }

    public void setExtra(String extra) {
        this.isAutoIncrement = extra.trim().equals("auto_increment");
    }

    public void setPrimaryKey(String primaryKey) {
        this.isPrimaryKey = primaryKey.trim().toUpperCase().equals("PRI");
    }

    public Boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public Boolean isAutoIncrement() {
        return isAutoIncrement;
    }

    public Boolean isNullable() {
        return nullable;
    }

    public void setNullable(String nullable) {
        this.nullable = nullable.equalsIgnoreCase("yes");
    }
}
