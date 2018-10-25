package com.ldars.mongo.bo;

import java.io.Serializable;


import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
  
/** 
 *  每月的报表记录，每天job刷新
 */  
@Document(collection = "attendenceReportMonth")  
public class AttendenceReportBo implements Serializable {
/*
{
    "_id" : ObjectId("59b482196c9f4432d150dbec"),
    "bluetooth_name" : "MC368CDE52EDF846",
    "attendence_date" : "2017-09-10",
    "name" : "洪磊",
    "mobile" : "13851753152",
    "created_at" : 1505002009,
    "attendence_time" : 1505002009,
    "device_info" : "{appver=3.4.1,os=iOS,osver=10.0.2,udid=99fb69ce83c0424c8c93fc4f8b1cc344f1724277,phonemodel=iPhone,network=4G,app_name=com.GreenLand.jslife}",
    "tag" : "am",
    "mac_addr" : "8C:DE:52:ED:F8:46"
}	
*/	
	
	private static final long serialVersionUID = 2928923917001675021L;  
	@Id
	private String id; //对应文档里面的 _id 
	
	private String month;  //"2017-8"
	private String mobile;  
	private String realName;  
	private String company;  
	private String department;  
	private String project;  
	private String amWorkTime;  //"8:30 
	private String pmWorkTime;  //17:30"
	private String workTime;  //"8:30 ~ 17:30"
	private Long updateTime;  
	private Map<String,String> attendenceDetail;  
	private int needWorkDays;
	private int realWorkDays;
	private int invalidWorkDays;
	private String remark;  
	private int deviceTotal;
	private List<String> deviceList;
	private int sequence;
	
	private int unCheckAmount; //当月未打卡总数
	private int lateAmount; //当月迟到数量
	private int earlyLeaveAmount; //当月早退数量
	
	public int getUnCheckAmount() {
		return unCheckAmount;
	}
	public void setUnCheckAmount(int unCheckAmount) {
		this.unCheckAmount = unCheckAmount;
	}
	public int getLateAmount() {
		return lateAmount;
	}
	public void setLateAmount(int lateAmount) {
		this.lateAmount = lateAmount;
	}
	public int getEarlyLeaveAmount() {
		return earlyLeaveAmount;
	}
	public void setEarlyLeaveAmount(int earlyLeaveAmount) {
		this.earlyLeaveAmount = earlyLeaveAmount;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public String getAmWorkTime() {
		return amWorkTime;
	}
	public void setAmWorkTime(String amWorkTime) {
		this.amWorkTime = amWorkTime;
	}
	public String getPmWorkTime() {
		return pmWorkTime;
	}
	public void setPmWorkTime(String pmWorkTime) {
		this.pmWorkTime = pmWorkTime;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}
	public Map<String, String> getAttendenceDetail() {
		return attendenceDetail;
	}
	public void setAttendenceDetail(Map<String, String> attendenceDetail) {
		this.attendenceDetail = attendenceDetail;
	}
	public int getNeedWorkDays() {
		return needWorkDays;
	}
	public void setNeedWorkDays(int needWorkDays) {
		this.needWorkDays = needWorkDays;
	}
	public int getRealWorkDays() {
		return realWorkDays;
	}
	public void setRealWorkDays(int realWorkDays) {
		this.realWorkDays = realWorkDays;
	}
	public int getInvalidWorkDays() {
		return invalidWorkDays;
	}
	public void setInvalidWorkDays(int invalidWorkDays) {
		this.invalidWorkDays = invalidWorkDays;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getDeviceTotal() {
		return deviceTotal;
	}
	public void setDeviceTotal(int deviceTotal) {
		this.deviceTotal = deviceTotal;
	}
	public List<String> getDeviceList() {
		return deviceList;
	}
	public void setDeviceList(List<String> deviceList) {
		this.deviceList = deviceList;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
	
	
}
