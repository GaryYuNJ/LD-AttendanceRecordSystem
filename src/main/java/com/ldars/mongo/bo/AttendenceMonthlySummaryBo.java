package com.ldars.mongo.bo;

import java.io.Serializable;


import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
  
/** 
 *  
 */  
public class AttendenceMonthlySummaryBo implements Serializable {
	
	private String month;  //"2017-8"

	private int unCheckAmount; //未打卡总数
	private int lateAmount; //迟到数量
	private int earlyLeaveAmount; //早退数量
	
	
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
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
	
}
