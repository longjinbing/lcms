package com.ljb.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/8<br>
 * 描述: <br>
 */
public class GeneratorConfig implements Serializable {
    private String menuName;
    private String module;
    private String packageName;
    private String author;
    private String email;
    private String tablePrefix;
    private String classPrefix;
    private List<String> tables;
    private Boolean query;
    private Boolean joinQuery;
    private Boolean timeQuery;
    private Boolean logicDelete;
    private Boolean status;
    private Boolean pagination;
    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTablePrefix() {
        return tablePrefix;
    }

    public void setTablePrefix(String tablePrefix) {
        this.tablePrefix = tablePrefix;
    }

    public List<String> getTables() {
        return tables;
    }

    public void setTables(List<String> tables) {
        this.tables = tables;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Boolean getJoinQuery() {
        return joinQuery;
    }

    public void setJoinQuery(Boolean joinQuery) {
        this.joinQuery = joinQuery;
    }

    public Boolean getTimeQuery() {
        return timeQuery;
    }

    public void setTimeQuery(Boolean timeQuery) {
        this.timeQuery = timeQuery;
    }

    public Boolean getLogicDelete() {
        return logicDelete;
    }

    public void setLogicDelete(Boolean logicDelete) {
        this.logicDelete = logicDelete;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getPagination() {
        return pagination;
    }

    public void setPagination(Boolean pagination) {
        this.pagination = pagination;
    }

    public Boolean getQuery() {
        return query;
    }

    public void setQuery(Boolean query) {
        this.query = query;
    }

    public String getClassPrefix() {
        return classPrefix;
    }

    public void setClassPrefix(String classPrefix) {
        this.classPrefix = classPrefix;
    }
}
