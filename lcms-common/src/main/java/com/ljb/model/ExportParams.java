package com.ljb.model;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ljb.utils.Query;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者: @author longjinbin <br>
 * 时间: 2018/11/24<br>
 * 描述: <br>
 */
public class ExportParams implements Serializable {

    private Map<String, Object> queryParams;
    private List<Prop> selectedFields;

    public ExportParams() {
    }

    public ExportParams(JSONObject jsonObject) {
        selectedFields = new ArrayList<>();
        queryParams =new Query(jsonObject.getJSONObject("queryParams"));
        JSONArray jsonArray = jsonObject.getJSONArray("selectedFields");
        for (int i = 0; i < jsonArray.size(); i++) {
            if (jsonArray.getJSONObject(i) != null) {
                selectedFields.add(new Prop(jsonArray.getJSONObject(i).getString("key"), jsonArray.getJSONObject(i).getString("label")));
            }
        }
    }

    public Map<String, Object> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, Object> queryParams) {
        this.queryParams = queryParams;
    }

    public List<Prop> getSelectedFields() {
        return selectedFields;
    }

    public void setSelectedFields(List<Prop> selectedFields) {
        this.selectedFields = selectedFields;
    }

    public class Prop implements Serializable {
        private String key;
        private String label;

        public Prop(String key, String label) {
            this.key = key;
            this.label = label;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
