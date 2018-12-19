package com.ljb.entity;

/**
 * 列的属性
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年12月20日 上午12:01:45
 */
public class Column {

    public Column(){

    }

    public Column(String columnName,String comment){
        this.columnName=columnName;
        this.comment=comment;
    }

    public Column(String columnName,String comment,String originalColumnName){
        this.columnName=columnName;
        this.comment=comment;
        this.originalColumnName=originalColumnName;
    }

    //java列名
    private String columnName;
    //java列名
    private String javaName;
    //列名类型
    private String dataType;
    //列名备注
    private String comment;
    //数据库列名
    private String originalColumnName;
    //数据库列名类型
    private String originalDataType;
    //auto_increment
    private Boolean isAutoIncrement;

    private Boolean isPrimaryKey;

    private Boolean nullable;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comments) {
        this.comment = comments;
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

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public Boolean getPrimaryKey() {
        return isPrimaryKey;
    }

    public void setPrimaryKey(Boolean primaryKey) {
        isPrimaryKey = primaryKey;
    }

    public Boolean getAutoIncrement() {
        return isAutoIncrement;
    }

    public void setAutoIncrement(Boolean autoIncrement) {
        isAutoIncrement = autoIncrement;
    }

    public Boolean getNullable() {
        return nullable;
    }

    public void setNullable(Boolean nullable) {
        this.nullable = nullable;
    }
}
