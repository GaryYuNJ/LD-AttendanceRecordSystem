package com.ldars.mongo.bo;

import java.io.Serializable;


import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
  
/** 
 *  
 */  
@Document(collection = "attendenceReport")  
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
	private String workTime;  //"8:30 ~ 17:30"
	private Long updateTime;  
	private Map<String,String> attendenceDetail;  
	private int needWorkDays;
	private int realWorkDays;
	private int invalidWorkDays;
	private String remark;  
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	public Long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
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
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
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
	
	
}
