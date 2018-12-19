package com.ljb.service.impl;

import com.ljb.Base.BaseServiceImpl;
import com.ljb.dao.ApiCategoryDao;
import com.ljb.dao.ApiHealthDao;
import com.ljb.dao.ApiTestResultDao;
import com.ljb.dao.ApiTestResultDetailsDao;
import com.ljb.entity.ApiTestResult;
import com.ljb.entity.ApiTestResultDetails;
import com.ljb.entity.QuestionModel;
import com.ljb.service.ApiHealthService;
import com.ljb.utils.DateUtils;
import com.ljb.utils.HeadTokenUtils;
import com.ljb.utils.PageUtils;
import com.ljb.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/12/16<br>
 * 描述: <br>
 */
@Service
public class ApiHealthServiceImpl extends BaseServiceImpl implements ApiHealthService {
    @Autowired
    private ApiHealthDao apihealthDao;
    @Autowired
    private ApiTestResultDao apiTestResultDao;
    @Autowired
    private ApiTestResultDetailsDao apiTestResultDetailsDao;
    @Autowired
    private HeadTokenUtils headTokenUtils;
    @Autowired
    private ApiCategoryDao apiCategoryDao;

    @Override
    public List<Map<String, Object>> questionList() {
        return apihealthDao.questionList();
    }

    @Override
    public Map<String, Object> category(Long id) {
        return apihealthDao.categoryById(id);
    }

    @Override
    public List<Map<String,Object>> constitutionList(){
        return apiCategoryDao.selectMapList(null);
    }

    @Override
    public Map<String, Object> save(QuestionModel questionModel) {
        List<Map<String, Long>> categoryList = apihealthDao.selectList();
        Map<Long, Long> tempMap = new HashMap<>();
        for (Iterator iterator = categoryList.iterator(); iterator.hasNext(); ) {
            Map map = ((Map<String, Object>) iterator.next());
            tempMap.put((Long) map.get("id"), (Long) map.get("healthId"));
        }
        Map<Long, Integer> result = new HashMap<>();
        Map<Long, String> answer = questionModel.getAnswers();
        for (Iterator iterator = answer.keySet().iterator(); iterator.hasNext(); ) {
            Long key = (Long) iterator.next();
            String option = answer.get(key).toString();
            int i = 0, j = 0;
            switch (option) {
                case "A":
                    j = 5;
                    break;
                case "B":
                    j = 5;
                    break;
                case "C":
                    j = 5;
                    break;
                case "D":
                    j = 5;
                    break;
                case "E":
                    j = 5;
                    break;
                default:
                    break;
            }
            if (result.containsKey(tempMap.get(key))) {
                i = result.get(tempMap.get(key));
            }
            result.put(tempMap.get(key), i + j);
        }
        int max = 0;
        long current = 0L;
        for (Iterator iterator =result.keySet().iterator(); iterator.hasNext(); ) {
            Long key = (Long) iterator.next();
            Integer value = result.get(key);
            max = value > max ? value : max;
            current = value == max ? key : current;
        }
        ApiTestResult apiTestResult = new ApiTestResult();
        apiTestResult.setPhone(questionModel.getPhone());
        apiTestResult.setSex(questionModel.getSex());
        apiTestResult.setUsername(questionModel.getName());
        apiTestResult.setHealthId(current);
        if(headTokenUtils.hasToken()){
            apiTestResult.setUserId(headTokenUtils.getUserId());
            apiTestResult.setCreateId(headTokenUtils.getUserId());
            apiTestResult.setUserId(headTokenUtils.getUserId());
        }
        apiTestResult.setCreateTime(DateUtils.getSqlDate());
        apiTestResult.setUpdateTime(DateUtils.getSqlDate());
        apiTestResult.setIsDelete(1);
        apiTestResult.setStatus(1);
        apiTestResultDao.insert(apiTestResult);
        List<ApiTestResultDetails> apiTestResultDetails=new ArrayList<>();
        for (Iterator iterator = result.keySet().iterator(); iterator.hasNext(); ) {
            Long key = (Long) iterator.next();
            Integer value = result.get(key);
            ApiTestResultDetails temp=new ApiTestResultDetails();
            temp.setResultId(apiTestResult.getId());
            temp.setResult(value);
            temp.setConstitutionId(key);
            temp.setStatus(1);
            temp.setIsDelete(1);
            if(headTokenUtils.hasToken()){
                temp.setCreateId(headTokenUtils.getUserId());
                temp.setUpdateId(headTokenUtils.getUserId());
            }
            temp.setCreateTime(DateUtils.getSqlDate());
            temp.setUpdateTime(DateUtils.getSqlDate());
            apiTestResultDetails.add(temp);
        }
        apiTestResultDetailsDao.saveBatch(apiTestResultDetails);
        List<Map<String,Object>> categorys=apihealthDao.categoryList();
        List<Map<String,Object>> resultDetails=new ArrayList<>();
        for(Iterator iterator=categorys.iterator();iterator.hasNext();){
            Map<String,Object> map=(Map)iterator.next();
            Map<String,Object> m=new HashMap<>();
            m.put("name", map.get("name"));
            m.put("value",result.get(map.get("id")));
            resultDetails.add(m);
        }
        Map<String,Object> map=new HashMap<>();
        map.put("name",questionModel.getName());
        map.put("sex",questionModel.getSex() );
        map.put("phone",questionModel.getPhone());
        map.put("result",apihealthDao.categoryById(current));
        map.put("resultDetails", resultDetails);
        return map;
    }

    @Override
    public Map<String, Object> last() {
        Map<String,Object> map=new HashMap<>();
        map.put("offset",0 );
        map.put("limit",1 );
        map.put("sort","create_time" );
        map.put("order","desc" );
        map.put("userId", headTokenUtils.getUserId());
        List<Map<String, Object>> list=apiTestResultDao.selectMapList(map);
        if(apiTestResultDao.selectMapList(map).size()>0){
            Map<String,Object> r=new HashMap<>();
            r.put("result", list.get(0));
            r.put("constitution", apihealthDao.categoryById(Long.valueOf(list.get(0).get("healthId").toString())));
            return r;
        };
        return null;
    }

    @Override
    public PageUtils resultList(Map<String, Object> params) {
        Query query = new Query(params);
        query.put("userId",headTokenUtils.getUserId() );
        List<Map<String, Object>> list = apiTestResultDao.selectMapList(query);
        Long total = apiTestResultDao.selectTotal(query);
        PageUtils pageUtils = new PageUtils(list, total, query.getOffset(), query.getLimit());
        return pageUtils;
    }

    @Override
    public Map<String, Object> result(Long id) {
        Map<String,Object> r=apiTestResultDao.selectMapById(id);
        r.put("constitution", apihealthDao.categoryById(Long.valueOf(r.get("healthId").toString())));
        return r;
    }


}
