package com.ljb.entity;

import java.util.Date;
import java.util.List;

/**
 * 表数据
 *
 * @author lipengjun
 * @email 939961241@qq.com
 * @date 2016年12月20日 上午12:02:55
 */
public class Table {
    //实体名称
    private String entityName;
    private String pathName;
    private String variableName;
    private Date createTime;
    private Date updateTime;
    private String tableCollation;
    //表的备注
    private String comment;
    //表的主键
    private Column pk;
    //表的列名(不包含主键)
    private List<Column> columns;
    //类名(第一个字母大写)，如：sys_user => SysUser
    private String originalTableName;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Column getPk() {
        return pk;
    }

    public void setPk(Column pk) {
        this.pk = pk;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }

    public String getOriginalTableName() {
        return originalTableName;
    }

    public void setOriginalTableName(String originalTableName) {
        this.originalTableName = originalTableName;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getPathName() {
        return pathName;
    }

    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getVariableName() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTableCollation() {
        return tableCollation;
    }

    public void setTableCollation(String tableCollation) {
        this.tableCollation = tableCollation;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
