package com.ybkj.gun.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GunExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GunExample() {
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

        public Criteria andWebIdIsNull() {
            addCriterion("web_id is null");
            return (Criteria) this;
        }

        public Criteria andWebIdIsNotNull() {
            addCriterion("web_id is not null");
            return (Criteria) this;
        }

        public Criteria andWebIdEqualTo(Integer value) {
            addCriterion("web_id =", value, "webId");
            return (Criteria) this;
        }

        public Criteria andWebIdNotEqualTo(Integer value) {
            addCriterion("web_id <>", value, "webId");
            return (Criteria) this;
        }

        public Criteria andWebIdGreaterThan(Integer value) {
            addCriterion("web_id >", value, "webId");
            return (Criteria) this;
        }

        public Criteria andWebIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("web_id >=", value, "webId");
            return (Criteria) this;
        }

        public Criteria andWebIdLessThan(Integer value) {
            addCriterion("web_id <", value, "webId");
            return (Criteria) this;
        }

        public Criteria andWebIdLessThanOrEqualTo(Integer value) {
            addCriterion("web_id <=", value, "webId");
            return (Criteria) this;
        }

        public Criteria andWebIdIn(List<Integer> values) {
            addCriterion("web_id in", values, "webId");
            return (Criteria) this;
        }

        public Criteria andWebIdNotIn(List<Integer> values) {
            addCriterion("web_id not in", values, "webId");
            return (Criteria) this;
        }

        public Criteria andWebIdBetween(Integer value1, Integer value2) {
            addCriterion("web_id between", value1, value2, "webId");
            return (Criteria) this;
        }

        public Criteria andWebIdNotBetween(Integer value1, Integer value2) {
            addCriterion("web_id not between", value1, value2, "webId");
            return (Criteria) this;
        }

        public Criteria andGunTagIsNull() {
            addCriterion("gun_tag is null");
            return (Criteria) this;
        }

        public Criteria andGunTagIsNotNull() {
            addCriterion("gun_tag is not null");
            return (Criteria) this;
        }

        public Criteria andGunTagEqualTo(String value) {
            addCriterion("gun_tag =", value, "gunTag");
            return (Criteria) this;
        }

        public Criteria andGunTagNotEqualTo(String value) {
            addCriterion("gun_tag <>", value, "gunTag");
            return (Criteria) this;
        }

        public Criteria andGunTagGreaterThan(String value) {
            addCriterion("gun_tag >", value, "gunTag");
            return (Criteria) this;
        }

        public Criteria andGunTagGreaterThanOrEqualTo(String value) {
            addCriterion("gun_tag >=", value, "gunTag");
            return (Criteria) this;
        }

        public Criteria andGunTagLessThan(String value) {
            addCriterion("gun_tag <", value, "gunTag");
            return (Criteria) this;
        }

        public Criteria andGunTagLessThanOrEqualTo(String value) {
            addCriterion("gun_tag <=", value, "gunTag");
            return (Criteria) this;
        }

        public Criteria andGunTagLike(String value) {
            addCriterion("gun_tag like", value, "gunTag");
            return (Criteria) this;
        }

        public Criteria andGunTagNotLike(String value) {
            addCriterion("gun_tag not like", value, "gunTag");
            return (Criteria) this;
        }

        public Criteria andGunTagIn(List<String> values) {
            addCriterion("gun_tag in", values, "gunTag");
            return (Criteria) this;
        }

        public Criteria andGunTagNotIn(List<String> values) {
            addCriterion("gun_tag not in", values, "gunTag");
            return (Criteria) this;
        }

        public Criteria andGunTagBetween(String value1, String value2) {
            addCriterion("gun_tag between", value1, value2, "gunTag");
            return (Criteria) this;
        }

        public Criteria andGunTagNotBetween(String value1, String value2) {
            addCriterion("gun_tag not between", value1, value2, "gunTag");
            return (Criteria) this;
        }

        public Criteria andGunModelIsNull() {
            addCriterion("gun_model is null");
            return (Criteria) this;
        }

        public Criteria andGunModelIsNotNull() {
            addCriterion("gun_model is not null");
            return (Criteria) this;
        }

        public Criteria andGunModelEqualTo(String value) {
            addCriterion("gun_model =", value, "gunModel");
            return (Criteria) this;
        }

        public Criteria andGunModelNotEqualTo(String value) {
            addCriterion("gun_model <>", value, "gunModel");
            return (Criteria) this;
        }

        public Criteria andGunModelGreaterThan(String value) {
            addCriterion("gun_model >", value, "gunModel");
            return (Criteria) this;
        }

        public Criteria andGunModelGreaterThanOrEqualTo(String value) {
            addCriterion("gun_model >=", value, "gunModel");
            return (Criteria) this;
        }

        public Criteria andGunModelLessThan(String value) {
            addCriterion("gun_model <", value, "gunModel");
            return (Criteria) this;
        }

        public Criteria andGunModelLessThanOrEqualTo(String value) {
            addCriterion("gun_model <=", value, "gunModel");
            return (Criteria) this;
        }

        public Criteria andGunModelLike(String value) {
            addCriterion("gun_model like", value, "gunModel");
            return (Criteria) this;
        }

        public Criteria andGunModelNotLike(String value) {
            addCriterion("gun_model not like", value, "gunModel");
            return (Criteria) this;
        }

        public Criteria andGunModelIn(List<String> values) {
            addCriterion("gun_model in", values, "gunModel");
            return (Criteria) this;
        }

        public Criteria andGunModelNotIn(List<String> values) {
            addCriterion("gun_model not in", values, "gunModel");
            return (Criteria) this;
        }

        public Criteria andGunModelBetween(String value1, String value2) {
            addCriterion("gun_model between", value1, value2, "gunModel");
            return (Criteria) this;
        }

        public Criteria andGunModelNotBetween(String value1, String value2) {
            addCriterion("gun_model not between", value1, value2, "gunModel");
            return (Criteria) this;
        }

        public Criteria andGunTypeIsNull() {
            addCriterion("gun_type is null");
            return (Criteria) this;
        }

        public Criteria andGunTypeIsNotNull() {
            addCriterion("gun_type is not null");
            return (Criteria) this;
        }

        public Criteria andGunTypeEqualTo(Integer value) {
            addCriterion("gun_type =", value, "gunType");
            return (Criteria) this;
        }

        public Criteria andGunTypeNotEqualTo(Integer value) {
            addCriterion("gun_type <>", value, "gunType");
            return (Criteria) this;
        }

        public Criteria andGunTypeGreaterThan(Integer value) {
            addCriterion("gun_type >", value, "gunType");
            return (Criteria) this;
        }

        public Criteria andGunTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("gun_type >=", value, "gunType");
            return (Criteria) this;
        }

        public Criteria andGunTypeLessThan(Integer value) {
            addCriterion("gun_type <", value, "gunType");
            return (Criteria) this;
        }

        public Criteria andGunTypeLessThanOrEqualTo(Integer value) {
            addCriterion("gun_type <=", value, "gunType");
            return (Criteria) this;
        }

        public Criteria andGunTypeIn(List<Integer> values) {
            addCriterion("gun_type in", values, "gunType");
            return (Criteria) this;
        }

        public Criteria andGunTypeNotIn(List<Integer> values) {
            addCriterion("gun_type not in", values, "gunType");
            return (Criteria) this;
        }

        public Criteria andGunTypeBetween(Integer value1, Integer value2) {
            addCriterion("gun_type between", value1, value2, "gunType");
            return (Criteria) this;
        }

        public Criteria andGunTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("gun_type not between", value1, value2, "gunType");
            return (Criteria) this;
        }

        public Criteria andBluetoothMacIsNull() {
            addCriterion("bluetooth_mac is null");
            return (Criteria) this;
        }

        public Criteria andBluetoothMacIsNotNull() {
            addCriterion("bluetooth_mac is not null");
            return (Criteria) this;
        }

        public Criteria andBluetoothMacEqualTo(String value) {
            addCriterion("bluetooth_mac =", value, "bluetoothMac");
            return (Criteria) this;
        }

        public Criteria andBluetoothMacNotEqualTo(String value) {
            addCriterion("bluetooth_mac <>", value, "bluetoothMac");
            return (Criteria) this;
        }

        public Criteria andBluetoothMacGreaterThan(String value) {
            addCriterion("bluetooth_mac >", value, "bluetoothMac");
            return (Criteria) this;
        }

        public Criteria andBluetoothMacGreaterThanOrEqualTo(String value) {
            addCriterion("bluetooth_mac >=", value, "bluetoothMac");
            return (Criteria) this;
        }

        public Criteria andBluetoothMacLessThan(String value) {
            addCriterion("bluetooth_mac <", value, "bluetoothMac");
            return (Criteria) this;
        }

        public Criteria andBluetoothMacLessThanOrEqualTo(String value) {
            addCriterion("bluetooth_mac <=", value, "bluetoothMac");
            return (Criteria) this;
        }

        public Criteria andBluetoothMacLike(String value) {
            addCriterion("bluetooth_mac like", value, "bluetoothMac");
            return (Criteria) this;
        }

        public Criteria andBluetoothMacNotLike(String value) {
            addCriterion("bluetooth_mac not like", value, "bluetoothMac");
            return (Criteria) this;
        }

        public Criteria andBluetoothMacIn(List<String> values) {
            addCriterion("bluetooth_mac in", values, "bluetoothMac");
            return (Criteria) this;
        }

        public Criteria andBluetoothMacNotIn(List<String> values) {
            addCriterion("bluetooth_mac not in", values, "bluetoothMac");
            return (Criteria) this;
        }

        public Criteria andBluetoothMacBetween(String value1, String value2) {
            addCriterion("bluetooth_mac between", value1, value2, "bluetoothMac");
            return (Criteria) this;
        }

        public Criteria andBluetoothMacNotBetween(String value1, String value2) {
            addCriterion("bluetooth_mac not between", value1, value2, "bluetoothMac");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameIsNull() {
            addCriterion("warehouse_name is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameIsNotNull() {
            addCriterion("warehouse_name is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameEqualTo(String value) {
            addCriterion("warehouse_name =", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameNotEqualTo(String value) {
            addCriterion("warehouse_name <>", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameGreaterThan(String value) {
            addCriterion("warehouse_name >", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameGreaterThanOrEqualTo(String value) {
            addCriterion("warehouse_name >=", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameLessThan(String value) {
            addCriterion("warehouse_name <", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameLessThanOrEqualTo(String value) {
            addCriterion("warehouse_name <=", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameLike(String value) {
            addCriterion("warehouse_name like", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameNotLike(String value) {
            addCriterion("warehouse_name not like", value, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameIn(List<String> values) {
            addCriterion("warehouse_name in", values, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameNotIn(List<String> values) {
            addCriterion("warehouse_name not in", values, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameBetween(String value1, String value2) {
            addCriterion("warehouse_name between", value1, value2, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseNameNotBetween(String value1, String value2) {
            addCriterion("warehouse_name not between", value1, value2, "warehouseName");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdIsNull() {
            addCriterion("warehouse_id is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdIsNotNull() {
            addCriterion("warehouse_id is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdEqualTo(Integer value) {
            addCriterion("warehouse_id =", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotEqualTo(Integer value) {
            addCriterion("warehouse_id <>", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdGreaterThan(Integer value) {
            addCriterion("warehouse_id >", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("warehouse_id >=", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdLessThan(Integer value) {
            addCriterion("warehouse_id <", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("warehouse_id <=", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdIn(List<Integer> values) {
            addCriterion("warehouse_id in", values, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotIn(List<Integer> values) {
            addCriterion("warehouse_id not in", values, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdBetween(Integer value1, Integer value2) {
            addCriterion("warehouse_id between", value1, value2, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("warehouse_id not between", value1, value2, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andBulletNumberIsNull() {
            addCriterion("bullet_number is null");
            return (Criteria) this;
        }

        public Criteria andBulletNumberIsNotNull() {
            addCriterion("bullet_number is not null");
            return (Criteria) this;
        }

        public Criteria andBulletNumberEqualTo(Integer value) {
            addCriterion("bullet_number =", value, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberNotEqualTo(Integer value) {
            addCriterion("bullet_number <>", value, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberGreaterThan(Integer value) {
            addCriterion("bullet_number >", value, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("bullet_number >=", value, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberLessThan(Integer value) {
            addCriterion("bullet_number <", value, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberLessThanOrEqualTo(Integer value) {
            addCriterion("bullet_number <=", value, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberIn(List<Integer> values) {
            addCriterion("bullet_number in", values, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberNotIn(List<Integer> values) {
            addCriterion("bullet_number not in", values, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberBetween(Integer value1, Integer value2) {
            addCriterion("bullet_number between", value1, value2, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andBulletNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("bullet_number not between", value1, value2, "bulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberIsNull() {
            addCriterion("total_bullet_number is null");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberIsNotNull() {
            addCriterion("total_bullet_number is not null");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberEqualTo(Integer value) {
            addCriterion("total_bullet_number =", value, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberNotEqualTo(Integer value) {
            addCriterion("total_bullet_number <>", value, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberGreaterThan(Integer value) {
            addCriterion("total_bullet_number >", value, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_bullet_number >=", value, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberLessThan(Integer value) {
            addCriterion("total_bullet_number <", value, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberLessThanOrEqualTo(Integer value) {
            addCriterion("total_bullet_number <=", value, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberIn(List<Integer> values) {
            addCriterion("total_bullet_number in", values, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberNotIn(List<Integer> values) {
            addCriterion("total_bullet_number not in", values, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberBetween(Integer value1, Integer value2) {
            addCriterion("total_bullet_number between", value1, value2, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andTotalBulletNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("total_bullet_number not between", value1, value2, "totalBulletNumber");
            return (Criteria) this;
        }

        public Criteria andDeviceBatteryPowerIsNull() {
            addCriterion("device_battery_power is null");
            return (Criteria) this;
        }

        public Criteria andDeviceBatteryPowerIsNotNull() {
            addCriterion("device_battery_power is not null");
            return (Criteria) this;
        }

        public Criteria andDeviceBatteryPowerEqualTo(String value) {
            addCriterion("device_battery_power =", value, "deviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andDeviceBatteryPowerNotEqualTo(String value) {
            addCriterion("device_battery_power <>", value, "deviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andDeviceBatteryPowerGreaterThan(String value) {
            addCriterion("device_battery_power >", value, "deviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andDeviceBatteryPowerGreaterThanOrEqualTo(String value) {
            addCriterion("device_battery_power >=", value, "deviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andDeviceBatteryPowerLessThan(String value) {
            addCriterion("device_battery_power <", value, "deviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andDeviceBatteryPowerLessThanOrEqualTo(String value) {
            addCriterion("device_battery_power <=", value, "deviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andDeviceBatteryPowerLike(String value) {
            addCriterion("device_battery_power like", value, "deviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andDeviceBatteryPowerNotLike(String value) {
            addCriterion("device_battery_power not like", value, "deviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andDeviceBatteryPowerIn(List<String> values) {
            addCriterion("device_battery_power in", values, "deviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andDeviceBatteryPowerNotIn(List<String> values) {
            addCriterion("device_battery_power not in", values, "deviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andDeviceBatteryPowerBetween(String value1, String value2) {
            addCriterion("device_battery_power between", value1, value2, "deviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andDeviceBatteryPowerNotBetween(String value1, String value2) {
            addCriterion("device_battery_power not between", value1, value2, "deviceBatteryPower");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateIsNull() {
            addCriterion("real_time_state is null");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateIsNotNull() {
            addCriterion("real_time_state is not null");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateEqualTo(Integer value) {
            addCriterion("real_time_state =", value, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateNotEqualTo(Integer value) {
            addCriterion("real_time_state <>", value, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateGreaterThan(Integer value) {
            addCriterion("real_time_state >", value, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("real_time_state >=", value, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateLessThan(Integer value) {
            addCriterion("real_time_state <", value, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateLessThanOrEqualTo(Integer value) {
            addCriterion("real_time_state <=", value, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateIn(List<Integer> values) {
            addCriterion("real_time_state in", values, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateNotIn(List<Integer> values) {
            addCriterion("real_time_state not in", values, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateBetween(Integer value1, Integer value2) {
            addCriterion("real_time_state between", value1, value2, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andRealTimeStateNotBetween(Integer value1, Integer value2) {
            addCriterion("real_time_state not between", value1, value2, "realTimeState");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
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