package com.ljb.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/25<br>
 * 描述: <br>
 */
public class Tree implements Serializable, Comparable<Tree> {

    private Long id;
    private String label;
    private List<Tree> children;
    private Integer orderNum;
    private String data;
    private Boolean disabled;
    private Boolean checked;
    private Long parentId;
    private Boolean expand;

    public Tree(){
        children=new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Tree> getChildren() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public void addChildren(Tree tree) {
        this.children.add(tree);
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Boolean getExpand() {
        return expand;
    }

    public void setExpand(Boolean expand) {
        this.expand = expand;
    }

    @Override
    public int compareTo(Tree o) {
        if(o.getOrderNum()==null){
            return 1;
        }
        return o.getOrderNum()>getOrderNum()?1:-1;
    }
}
