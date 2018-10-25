package com.ldars.oa.model;

import java.util.Date;

public class UserUdidMonthlyReport {
    private String month;

    private String userName;

    private String userMobile;

    private String company;

    private String department;

    private String udid;
    
    private Integer deviceCount;

    private Integer count;

    private String dates;

    private Date createTime;

    private String uidiHostName;

    private String uidiHostMobile;

    private String uidiHostCompany;

    private String uidiHostDepartment;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company == null ? null : company.trim();
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department == null ? null : department.trim();
    }

    public String getUdid() {
        return udid;
    }

    public void setUdid(String udid) {
        this.udid = udid == null ? null : udid.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates == null ? null : dates.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUidiHostName() {
        return uidiHostName;
    }

    public void setUidiHostName(String uidiHostName) {
        this.uidiHostName = uidiHostName == null ? null : uidiHostName.trim();
    }

    public String getUidiHostMobile() {
        return uidiHostMobile;
    }

    public void setUidiHostMobile(String uidiHostMobile) {
        this.uidiHostMobile = uidiHostMobile == null ? null : uidiHostMobile.trim();
    }

    public String getUidiHostCompany() {
        return uidiHostCompany;
    }

    public void setUidiHostCompany(String uidiHostCompany) {
        this.uidiHostCompany = uidiHostCompany == null ? null : uidiHostCompany.trim();
    }

    public String getUidiHostDepartment() {
        return uidiHostDepartment;
    }

    public void setUidiHostDepartment(String uidiHostDepartment) {
        this.uidiHostDepartment = uidiHostDepartment == null ? null : uidiHostDepartment.trim();
    }

	public Integer getDeviceCount() {
		return deviceCount;
	}

	public void setDeviceCount(Integer deviceCount) {
		this.deviceCount = deviceCount;
	}
    
}