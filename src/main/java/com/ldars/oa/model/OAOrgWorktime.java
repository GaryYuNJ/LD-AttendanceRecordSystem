package com.ldars.oa.model;

public class OAOrgWorktime {
    private String orgId;

    private Integer amHour;

    private Integer amMinute;

    private Integer pmHour;

    private Integer pmMinute;

    private String status;

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId == null ? null : orgId.trim();
    }

    public Integer getAmHour() {
        return amHour;
    }

    public void setAmHour(Integer amHour) {
        this.amHour = amHour;
    }

    public Integer getAmMinute() {
        return amMinute;
    }

    public void setAmMinute(Integer amMinute) {
        this.amMinute = amMinute;
    }

    public Integer getPmHour() {
        return pmHour;
    }

    public void setPmHour(Integer pmHour) {
        this.pmHour = pmHour;
    }

    public Integer getPmMinute() {
        return pmMinute;
    }

    public void setPmMinute(Integer pmMinute) {
        this.pmMinute = pmMinute;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}