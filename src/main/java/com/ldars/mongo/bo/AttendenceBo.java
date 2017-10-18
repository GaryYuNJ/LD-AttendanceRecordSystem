package com.ldars.mongo.bo;

import java.io.Serializable;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
  
/** 
 * 系统操作人员 
 */  
@Document(collection = "attendence")  
public class AttendenceBo implements Serializable {
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
	
	private String bluetooth_name;  
	private String attendence_date;  
	private String name;  
	private String mobile;  
	private Long created_at;  
	private Long attendence_time;  
	private String device_info;  
	private String tag;  
	private String mac_addr;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBluetooth_name() {
		return bluetooth_name;
	}
	public void setBluetooth_name(String bluetooth_name) {
		this.bluetooth_name = bluetooth_name;
	}
	public String getAttendence_date() {
		return attendence_date;
	}
	public void setAttendence_date(String attendence_date) {
		this.attendence_date = attendence_date;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Long getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Long created_at) {
		this.created_at = created_at;
	}
	public Long getAttendence_time() {
		return attendence_time;
	}
	public void setAttendence_time(Long attendence_time) {
		this.attendence_time = attendence_time;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String device_info) {
		this.device_info = device_info;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getMac_addr() {
		return mac_addr;
	}
	public void setMac_addr(String mac_addr) {
		this.mac_addr = mac_addr;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}  
	
	
	
	
	
	
}
