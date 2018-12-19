package com.ljb.utils;

import com.ljb.entity.Dept;
import com.ljb.entity.Menu;
import com.ljb.model.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/11<br>
 * 描述: <br>
 */
public class TransTreeUtils {
    public static List<Tree> transMenuTree(List<Menu> menus){
        List<Tree> trees=new ArrayList<>();
        for (Menu menu:menus){
            Tree tree=new Tree();
            tree.setId(menu.getId());
            tree.setParentId(menu.getParentId());
            tree.setLabel(menu.getName());
            tree.setOrderNum(menu.getOrderNum());
            tree.setData(menu.getUrl());
            trees.add(tree);
        }
        return trees;
    }

    public static List<Tree> transDeptTree(List<Dept> depts){
        List<Tree> trees=new ArrayList<>();
        for (Dept dept:depts){
            Tree tree=new Tree();
            tree.setId(dept.getId());
            tree.setParentId(dept.getParentId());
            tree.setLabel(dept.getName());
            tree.setOrderNum(dept.getOrderNum());
            trees.add(tree);
        }
        return trees;
    }
}
