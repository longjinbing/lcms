package com.ljb.utils;

import com.ljb.annotion.MenuDescription;
import com.ljb.annotion.SafetyGrade;
import com.ljb.model.MenuDescriptionModel;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.lang.reflect.Method;
import java.util.*;

/**
 * @author Lvbey
 */

@Component // 交给spring管理方便其他类获取该类对象
public class MenuDescriptionUtils implements ApplicationContextAware {

	private static Log logger = LogFactory.getLog(MenuDescriptionUtils.class);
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public List<MenuDescriptionModel> getMenuList() {
        List<MenuDescriptionModel> list=new ArrayList<>();
		Map<String, Object> beanMap = this.applicationContext.getBeansWithAnnotation(MenuDescription.class);
		Class<? extends Object> clazz = null;
		List<MenuDescriptionModel> baseList=new ArrayList<>();
		MenuDescriptionModel root=new MenuDescriptionModel();
		Map<String,MenuDescriptionModel> resultMap=new HashMap<>();
		for (Map.Entry<String, Object> entry : beanMap.entrySet()) {
			clazz = entry.getValue().getClass();
			MenuDescription desc= clazz.getAnnotation(MenuDescription.class);
			RequestMapping requestMapping=clazz.getAnnotation(RequestMapping.class);
            root = getMenuDescriptionModel(desc, "");
            root.setName(desc.group());
            root.setAction("#");
            root.setSafetyGrade(desc.safetyGrade());
            MenuDescriptionModel menu = getMenuDescriptionModel(desc, desc.action());
            Method[] methods = clazz.getMethods();
            for (Method c : methods) {
                MenuDescription actionDesc = (MenuDescription) c.getAnnotation(MenuDescription.class);
                RequestMapping action= (RequestMapping) c.getAnnotation(RequestMapping.class);
                if (actionDesc != null) {
                    MenuDescriptionModel button = getMenuDescriptionModel(actionDesc, requestMapping.value()[0] + action.value()[0]);
                    if(actionDesc.safetyGrade()== SafetyGrade.COMMON) {
                        baseList.add(button);
                    }else{
                        menu.addChildren(button);
                    }
                }
            }
            root.addChildren(menu);
            if(resultMap.containsKey(root.getName())){
                MenuDescriptionModel zTree=resultMap.get(root.getName());
                if(zTree.getSafetyGrade()==SafetyGrade.HIDDEN&&root.getSafetyGrade()!=SafetyGrade.HIDDEN){
                    zTree.setSafetyGrade(root.getSafetyGrade());
                }
                zTree.addChildren(menu);
                resultMap.put(root.getName(), zTree);
            }else{
                resultMap.put(root.getName(),root );
            }
		}
        Iterator iterator=resultMap.keySet().iterator();
		while (iterator.hasNext()){
		    String key=iterator.next().toString();
		    list.add(resultMap.get(key));
        }
		list.add(getBaseMenuDescriptionModel(baseList));
		return list;
	}


	public MenuDescriptionModel getMenuDescriptionModel(MenuDescription desc, String url) {
		MenuDescriptionModel zTree = new MenuDescriptionModel();
		zTree.setName(desc.name());
		zTree.setSafetyGrade(desc.safetyGrade());
		zTree.setAction(url.indexOf("/{id}")>-1?url.substring(0, url.indexOf("/{id}")+1):url);
		return zTree;
	}

	public MenuDescriptionModel getBaseMenuDescriptionModel(List<MenuDescriptionModel> list){
		MenuDescriptionModel baseRoot=new MenuDescriptionModel();
		baseRoot.setName("基本权限");
		baseRoot.setAction("#");
		baseRoot.setSafetyGrade(SafetyGrade.COMMON);
		MenuDescriptionModel menuRoot=new MenuDescriptionModel();
		menuRoot .setName("访问权限");
		menuRoot.setAction("#");
		menuRoot.setSafetyGrade(SafetyGrade.COMMON);
		menuRoot.setChildren(list);
		List<MenuDescriptionModel> menuList=new ArrayList<>();
		menuList.add(menuRoot);
		baseRoot.setChildren(menuList);
		return baseRoot;
	}
}
