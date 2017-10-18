package com.ldars.oa.model;

import java.util.Date;

public class OAOrganization {
    private String id;

    private Boolean expanded;

    private Boolean isleaf;

    private Integer level;

    private Boolean loaded;

    private String name;

    private String parent;

    private String address;

    private String contactnumber;

    private String contactperson;

    private Date createTime;

    private String createUser;

    private Date modifyTime;

    private String modifyUser;

    private String orgno;

    private Integer status;

    private String groupId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Boolean getIsleaf() {
        return isleaf;
    }

    public void setIsleaf(Boolean isleaf) {
        this.isleaf = isleaf;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getLoaded() {
        return loaded;
    }

    public void setLoaded(Boolean loaded) {
        this.loaded = loaded;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent == null ? null : parent.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber == null ? null : contactnumber.trim();
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson == null ? null : contactperson.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
        this.modifyUser = modifyUser == null ? null : modifyUser.trim();
    }

    public String getOrgno() {
        return orgno;
    }

    public void setOrgno(String orgno) {
        this.orgno = orgno == null ? null : orgno.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId == null ? null : groupId.trim();
    }
}