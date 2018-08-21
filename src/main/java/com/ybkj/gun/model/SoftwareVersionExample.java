package com.ybkj.gun.model;

import java.util.ArrayList;
import java.util.List;

public class SoftwareVersionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SoftwareVersionExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andVersionNumIsNull() {
            addCriterion("version_num is null");
            return (Criteria) this;
        }

        public Criteria andVersionNumIsNotNull() {
            addCriterion("version_num is not null");
            return (Criteria) this;
        }

        public Criteria andVersionNumEqualTo(String value) {
            addCriterion("version_num =", value, "versionNum");
            return (Criteria) this;
        }

        public Criteria andVersionNumNotEqualTo(String value) {
            addCriterion("version_num <>", value, "versionNum");
            return (Criteria) this;
        }

        public Criteria andVersionNumGreaterThan(String value) {
            addCriterion("version_num >", value, "versionNum");
            return (Criteria) this;
        }

        public Criteria andVersionNumGreaterThanOrEqualTo(String value) {
            addCriterion("version_num >=", value, "versionNum");
            return (Criteria) this;
        }

        public Criteria andVersionNumLessThan(String value) {
            addCriterion("version_num <", value, "versionNum");
            return (Criteria) this;
        }

        public Criteria andVersionNumLessThanOrEqualTo(String value) {
            addCriterion("version_num <=", value, "versionNum");
            return (Criteria) this;
        }

        public Criteria andVersionNumLike(String value) {
            addCriterion("version_num like", value, "versionNum");
            return (Criteria) this;
        }

        public Criteria andVersionNumNotLike(String value) {
            addCriterion("version_num not like", value, "versionNum");
            return (Criteria) this;
        }

        public Criteria andVersionNumIn(List<String> values) {
            addCriterion("version_num in", values, "versionNum");
            return (Criteria) this;
        }

        public Criteria andVersionNumNotIn(List<String> values) {
            addCriterion("version_num not in", values, "versionNum");
            return (Criteria) this;
        }

        public Criteria andVersionNumBetween(String value1, String value2) {
            addCriterion("version_num between", value1, value2, "versionNum");
            return (Criteria) this;
        }

        public Criteria andVersionNumNotBetween(String value1, String value2) {
            addCriterion("version_num not between", value1, value2, "versionNum");
            return (Criteria) this;
        }

        public Criteria andNeedForceUpdateIsNull() {
            addCriterion("need_force_update is null");
            return (Criteria) this;
        }

        public Criteria andNeedForceUpdateIsNotNull() {
            addCriterion("need_force_update is not null");
            return (Criteria) this;
        }

        public Criteria andNeedForceUpdateEqualTo(Integer value) {
            addCriterion("need_force_update =", value, "needForceUpdate");
            return (Criteria) this;
        }

        public Criteria andNeedForceUpdateNotEqualTo(Integer value) {
            addCriterion("need_force_update <>", value, "needForceUpdate");
            return (Criteria) this;
        }

        public Criteria andNeedForceUpdateGreaterThan(Integer value) {
            addCriterion("need_force_update >", value, "needForceUpdate");
            return (Criteria) this;
        }

        public Criteria andNeedForceUpdateGreaterThanOrEqualTo(Integer value) {
            addCriterion("need_force_update >=", value, "needForceUpdate");
            return (Criteria) this;
        }

        public Criteria andNeedForceUpdateLessThan(Integer value) {
            addCriterion("need_force_update <", value, "needForceUpdate");
            return (Criteria) this;
        }

        public Criteria andNeedForceUpdateLessThanOrEqualTo(Integer value) {
            addCriterion("need_force_update <=", value, "needForceUpdate");
            return (Criteria) this;
        }

        public Criteria andNeedForceUpdateIn(List<Integer> values) {
            addCriterion("need_force_update in", values, "needForceUpdate");
            return (Criteria) this;
        }

        public Criteria andNeedForceUpdateNotIn(List<Integer> values) {
            addCriterion("need_force_update not in", values, "needForceUpdate");
            return (Criteria) this;
        }

        public Criteria andNeedForceUpdateBetween(Integer value1, Integer value2) {
            addCriterion("need_force_update between", value1, value2, "needForceUpdate");
            return (Criteria) this;
        }

        public Criteria andNeedForceUpdateNotBetween(Integer value1, Integer value2) {
            addCriterion("need_force_update not between", value1, value2, "needForceUpdate");
            return (Criteria) this;
        }

        public Criteria andUpdateContentIsNull() {
            addCriterion("update_content is null");
            return (Criteria) this;
        }

        public Criteria andUpdateContentIsNotNull() {
            addCriterion("update_content is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateContentEqualTo(String value) {
            addCriterion("update_content =", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentNotEqualTo(String value) {
            addCriterion("update_content <>", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentGreaterThan(String value) {
            addCriterion("update_content >", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentGreaterThanOrEqualTo(String value) {
            addCriterion("update_content >=", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentLessThan(String value) {
            addCriterion("update_content <", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentLessThanOrEqualTo(String value) {
            addCriterion("update_content <=", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentLike(String value) {
            addCriterion("update_content like", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentNotLike(String value) {
            addCriterion("update_content not like", value, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentIn(List<String> values) {
            addCriterion("update_content in", values, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentNotIn(List<String> values) {
            addCriterion("update_content not in", values, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentBetween(String value1, String value2) {
            addCriterion("update_content between", value1, value2, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUpdateContentNotBetween(String value1, String value2) {
            addCriterion("update_content not between", value1, value2, "updateContent");
            return (Criteria) this;
        }

        public Criteria andUploadUrlIsNull() {
            addCriterion("upload_url is null");
            return (Criteria) this;
        }

        public Criteria andUploadUrlIsNotNull() {
            addCriterion("upload_url is not null");
            return (Criteria) this;
        }

        public Criteria andUploadUrlEqualTo(String value) {
            addCriterion("upload_url =", value, "uploadUrl");
            return (Criteria) this;
        }

        public Criteria andUploadUrlNotEqualTo(String value) {
            addCriterion("upload_url <>", value, "uploadUrl");
            return (Criteria) this;
        }

        public Criteria andUploadUrlGreaterThan(String value) {
            addCriterion("upload_url >", value, "uploadUrl");
            return (Criteria) this;
        }

        public Criteria andUploadUrlGreaterThanOrEqualTo(String value) {
            addCriterion("upload_url >=", value, "uploadUrl");
            return (Criteria) this;
        }

        public Criteria andUploadUrlLessThan(String value) {
            addCriterion("upload_url <", value, "uploadUrl");
            return (Criteria) this;
        }

        public Criteria andUploadUrlLessThanOrEqualTo(String value) {
            addCriterion("upload_url <=", value, "uploadUrl");
            return (Criteria) this;
        }

        public Criteria andUploadUrlLike(String value) {
            addCriterion("upload_url like", value, "uploadUrl");
            return (Criteria) this;
        }

        public Criteria andUploadUrlNotLike(String value) {
            addCriterion("upload_url not like", value, "uploadUrl");
            return (Criteria) this;
        }

        public Criteria andUploadUrlIn(List<String> values) {
            addCriterion("upload_url in", values, "uploadUrl");
            return (Criteria) this;
        }

        public Criteria andUploadUrlNotIn(List<String> values) {
            addCriterion("upload_url not in", values, "uploadUrl");
            return (Criteria) this;
        }

        public Criteria andUploadUrlBetween(String value1, String value2) {
            addCriterion("upload_url between", value1, value2, "uploadUrl");
            return (Criteria) this;
        }

        public Criteria andUploadUrlNotBetween(String value1, String value2) {
            addCriterion("upload_url not between", value1, value2, "uploadUrl");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}