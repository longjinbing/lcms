package com.ljb.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 封装and条件
 *
 * @author xuyang
 * @email 295640759@qq.com
 * @Integer 2018年08月08日 上午10:43:36
 */
public abstract class GeneratedCriteria {
	protected List<Criterion> criteria;

	protected GeneratedCriteria() {
		criteria = new ArrayList<Criterion>();
	}

	public boolean isValid() {
		return criteria.size() > 0;
	}

	public List<Criterion> getAllCriteria() {
		return criteria;
	}

	public List<Criterion> getCriteria() {
		return criteria;
	}

	protected void addCriterion(String condition) {
		if (condition == null) {
			throw new RuntimeException("Value for condition cannot be null");
		}
		criteria.add(new Criterion(condition));
	}

	protected void addCriterion(String condition, Object value, String property) {
		if (value == null) {
			throw new RuntimeException("Value for " + property + " cannot be null");
		}
		criteria.add(new Criterion(condition, value));
	}

	protected void addCriterion(String condition, Object value1, Object value2, String property) {
		if (value1 == null || value2 == null) {
			throw new RuntimeException("Between values for " + property + " cannot be null");
		}
		criteria.add(new Criterion(condition, value1, value2));
	}

	protected void addCriterionForJDBCDate(String condition, Date value, String property) {
		if (value == null) {
			throw new RuntimeException("Value for " + property + " cannot be null");
		}
		addCriterion(condition, new Date(value.getTime()), property);
	}

	protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
		if (values == null || values.size() == 0) {
			throw new RuntimeException("Value list for " + property + " cannot be null or empty");
		}
		List<Date> DateList = new ArrayList<Date>();
		Iterator<Date> iter = values.iterator();
		while (iter.hasNext()) {
			DateList.add(new Date(iter.next().getTime()));
		}
		addCriterion(condition, DateList, property);
	}

	protected void addCriterionForJDBCInteger(String condition, Date value1, Date value2, String property) {
		if (value1 == null || value2 == null) {
			throw new RuntimeException("Between values for " + property + " cannot be null");
		}
		addCriterion(condition, new Date(value1.getTime()), new Date(value2.getTime()), property);
	}

	public GeneratedCriteria andIdIsNull() {
		addCriterion("id is null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdIsNotNull() {
		addCriterion("id is not null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdEqualTo(Integer value) {
		addCriterion("id =", value, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdNotEqualTo(Integer value) {
		addCriterion("id <>", value, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdGreaterThan(Integer value) {
		addCriterion("id >", value, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdGreaterThanOrEqualTo(Integer value) {
		addCriterion("id >=", value, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdLessThan(Integer value) {
		addCriterion("id <", value, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdLessThanOrEqualTo(Integer value) {
		addCriterion("id <=", value, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdIn(List<Integer> values) {
		addCriterion("id in", values, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdNotIn(List<Integer> values) {
		addCriterion("id not in", values, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdBetween(Integer value1, Integer value2) {
		addCriterion("id between", value1, value2, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andIdNotBetween(Integer value1, Integer value2) {
		addCriterion("id not between", value1, value2, "id");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdIsNull() {
		addCriterion("create_id is null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdIsNotNull() {
		addCriterion("create_id is not null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdEqualTo(Integer value) {
		addCriterion("create_id =", value, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdNotEqualTo(Integer value) {
		addCriterion("create_id <>", value, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdGreaterThan(Integer value) {
		addCriterion("create_id >", value, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdGreaterThanOrEqualTo(Integer value) {
		addCriterion("create_id >=", value, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdLessThan(Integer value) {
		addCriterion("create_id <", value, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdLessThanOrEqualTo(Integer value) {
		addCriterion("create_id <=", value, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdIn(List<Integer> values) {
		addCriterion("create_id in", values, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdNotIn(List<Integer> values) {
		addCriterion("create_id not in", values, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdBetween(Integer value1, Integer value2) {
		addCriterion("create_id between", value1, value2, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateIdNotBetween(Integer value1, Integer value2) {
		addCriterion("create_id not between", value1, value2, "createId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerIdIsNull() {
		addCriterion("upInteger_id is null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerIdIsNotNull() {
		addCriterion("upInteger_id is not null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerIdEqualTo(Integer value) {
		addCriterion("upInteger_id =", value, "upIntegerId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerIdNotEqualTo(Integer value) {
		addCriterion("upInteger_id <>", value, "upIntegerId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerIdGreaterThan(Integer value) {
		addCriterion("upInteger_id >", value, "upIntegerId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerIdGreaterThanOrEqualTo(Integer value) {
		addCriterion("upInteger_id >=", value, "upIntegerId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerIdLessThan(Integer value) {
		addCriterion("upInteger_id <", value, "upIntegerId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerIdLessThanOrEqualTo(Integer value) {
		addCriterion("upInteger_id <=", value, "upIntegerId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerIdIn(List<Integer> values) {
		addCriterion("upInteger_id in", values, "upIntegerId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerIdNotIn(List<Integer> values) {
		addCriterion("upInteger_id not in", values, "upIntegerId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerIdBetween(Integer value1, Integer value2) {
		addCriterion("upInteger_id between", value1, value2, "upIntegerId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerIdNotBetween(Integer value1, Integer value2) {
		addCriterion("upInteger_id not between", value1, value2, "upIntegerId");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeIsNull() {
		addCriterion("create_time is null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeIsNotNull() {
		addCriterion("create_time is not null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeEqualTo(Integer value) {
		addCriterion("create_time =", value, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeNotEqualTo(Integer value) {
		addCriterion("create_time <>", value, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeGreaterThan(Integer value) {
		addCriterion("create_time >", value, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeGreaterThanOrEqualTo(Integer value) {
		addCriterion("create_time >=", value, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeLessThan(Integer value) {
		addCriterion("create_time <", value, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeLessThanOrEqualTo(Integer value) {
		addCriterion("create_time <=", value, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeIn(List<Integer> values) {
		addCriterion("create_time in", values, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeNotIn(List<Integer> values) {
		addCriterion("create_time not in", values, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeBetween(Integer value1, Integer value2) {
		addCriterion("create_time between", value1, value2, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andCreateTimeNotBetween(Integer value1, Integer value2) {
		addCriterion("create_time not between", value1, value2, "createTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerTimeIsNull() {
		addCriterion("upInteger_time is null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerTimeIsNotNull() {
		addCriterion("upInteger_time is not null");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerTimeEqualTo(Integer value) {
		addCriterion("upInteger_time =", value, "upIntegerTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerTimeNotEqualTo(Integer value) {
		addCriterion("upInteger_time <>", value, "upIntegerTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerTimeGreaterThan(Integer value) {
		addCriterion("upInteger_time >", value, "upIntegerTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerTimeGreaterThanOrEqualTo(Integer value) {
		addCriterion("upInteger_time >=", value, "upIntegerTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerTimeLessThan(Integer value) {
		addCriterion("upInteger_time <", value, "upIntegerTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerTimeLessThanOrEqualTo(Integer value) {
		addCriterion("upInteger_time <=", value, "upIntegerTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerTimeIn(List<Integer> values) {
		addCriterion("upInteger_time in", values, "upIntegerTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerTimeNotIn(List<Integer> values) {
		addCriterion("upInteger_time not in", values, "upIntegerTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerTimeBetween(Integer value1, Integer value2) {
		addCriterion("upInteger_time between", value1, value2, "upIntegerTime");
		return (GeneratedCriteria) this;
	}

	public GeneratedCriteria andUpIntegerTimeNotBetween(Integer value1, Integer value2) {
		addCriterion("upInteger_time not between", value1, value2, "upIntegerTime");
		return (GeneratedCriteria) this;
	}
}
