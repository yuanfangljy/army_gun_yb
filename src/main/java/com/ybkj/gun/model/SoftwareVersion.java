package com.ybkj.gun.model;

public class SoftwareVersion {
    private Integer id;

    private Integer type;

    private String versionNum;

    private Integer needForceUpdate;

    private String updateContent;

    private String uploadUrl;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum == null ? null : versionNum.trim();
    }

    public Integer getNeedForceUpdate() {
        return needForceUpdate;
    }

    public void setNeedForceUpdate(Integer needForceUpdate) {
        this.needForceUpdate = needForceUpdate;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public void setUpdateContent(String updateContent) {
        this.updateContent = updateContent == null ? null : updateContent.trim();
    }

    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl == null ? null : uploadUrl.trim();
    }
}