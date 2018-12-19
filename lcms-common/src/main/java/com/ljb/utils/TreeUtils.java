package com.ljb.utils;

import com.ljb.model.Tree;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 名称：TreeUtils <br>
 * 描述：<br>
 *
 * @author 李鹏军
 * @version 1.0
 * @since 1.0.0
 */
public class TreeUtils {

    private static Logger logger = LoggerFactory.getLogger(TreeUtils.class);

    /**
     * 包装成树形结构 (全部属性)
     * 必须要有id parentId children
     *
     * @param
     * @return
     * @throws Exception
     */
    public static List<Tree> buildTree(List<Tree> source) {
        if (source == null) {
            return null;
        }
        List<Tree> roots=buildDeep(source,getChildrenNodes(source,0L));
        return roots;
    }

    public static List<Map<String,Object>> buildMapTree(List<Map<String,Object>> source,String... fields) {
        if (source == null) {
            return null;
        }
        String[] defaultdisplay=new String[]{"name","id","parentId"};
        String[] props=ArrayUtils.addAll(defaultdisplay,fields);
        List<Map<String,Object>> target=new ArrayList<>();
        for(Map<String,Object> map:source){
            Map<String,Object> temp=new HashMap<>();
            for(String str:props){
                    temp.put(str, map.getOrDefault(str,false));
            }
            target.add(temp);
        }
        return buildMapDeep(source,getChildrenMapNodes(target,0L));
    }

    public static List<Tree> buildDeep(List<Tree> source, List<Tree> nodes){
        List<Tree> target=new ArrayList<>();
        for(Iterator iterator=nodes.iterator();iterator.hasNext();){
            Tree tree=(Tree)iterator.next();
            List<Tree> childNodes=getChildrenNodes(source,tree.getId());
            if(childNodes!=null){
                tree.setChildren(buildDeep(source, childNodes));
            }
            target.add(tree);
        }
        return target;
    }

    public static List<Tree> getChildrenNodes(List<Tree> source,Long parentId){
        return source.stream().filter(tree->tree.getParentId().equals(parentId)).collect(Collectors.toList());
    }


    public static List<Map<String,Object>> buildMapTree(List<Map<String,Object>> source) {
        if (source == null) {
            return null;
        }
        List<Map<String,Object>> roots=buildMapDeep(source,getChildrenMapNodes(source,0L));
        return roots;
    }



    public static List<Map<String,Object>> buildMapDeep(List<Map<String,Object>> source, List<Map<String,Object>> nodes){
        List<Map<String,Object>> target=new ArrayList<>();
        for(Iterator iterator=nodes.iterator();iterator.hasNext();){
            Map<String,Object> map=(Map<String,Object>)iterator.next();
            List<Map<String,Object>> childNodes=getChildrenMapNodes(source,map.get("id"));
            if(childNodes!=null){
                List<Map<String,Object>> result=buildMapDeep(source, childNodes);
                if(result.size()>0) {
                    map.put("children", buildMapDeep(source, childNodes));
                }
            }
            target.add(map);
        }
        return target;
    }



    public static List<Map<String,Object>> getChildrenMapNodes(List<Map<String,Object>> source,Object parentId){
        return source.stream().filter(map->map.get("parentId").equals(parentId)).collect(Collectors.toList());
    }



}
