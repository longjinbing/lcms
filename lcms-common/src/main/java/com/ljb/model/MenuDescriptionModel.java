package com.ljb.model;

import com.ljb.annotion.SafetyGrade;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/10<br>
 * 描述: <br>
 */
public class MenuDescriptionModel {
    private String name;
    private String action;
    private SafetyGrade safetyGrade;
    private List<MenuDescriptionModel> children;

    public MenuDescriptionModel(){
        children=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public SafetyGrade getSafetyGrade() {
        return safetyGrade;
    }

    public void setSafetyGrade(SafetyGrade safetyGrade) {
        this.safetyGrade = safetyGrade;
    }

    public List<MenuDescriptionModel> getChildren() {
        return children;
    }

    public void setChildren(List<MenuDescriptionModel> children) {
        this.children = children;
    }

    public void addChildren(MenuDescriptionModel menu) {
        this.children.add(menu);
    }
}
