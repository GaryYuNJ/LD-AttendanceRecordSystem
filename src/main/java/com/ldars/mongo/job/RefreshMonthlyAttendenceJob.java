package com.ldars.mongo.job;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ldars.mongo.service.IMongoService;
/*
	定时刷新当月的月度考勤报表
*/

@Component
public class RefreshMonthlyAttendenceJob {
	private static Logger logger = Logger
			.getLogger(RefreshMonthlyAttendenceJob.class);
	@Autowired
	private IMongoService mongoService;
	
	@Scheduled(cron = "0 0 5 * * ?")
	public void run() {
		SimpleDateFormat sf1 = new  SimpleDateFormat("yyyy-MM");
		//根据startDate获取月份
		//每月日期未上月24号到当月23号
		Calendar startCalendar = Calendar.getInstance();//日历对象 
		startCalendar.setTime(new Date());//设置日期  
		if((new Date()).getDate() >= 24){
			startCalendar.add(Calendar.MONTH, 1);//月份加
		} 
		
		//获取指定月份，和 上个月份的 string
		String month = sf1.format(startCalendar.getTime());
		//List<AttendenceBo> list = attendenceDao.list();
		try {
			mongoService.refreshAttendenceReportByMonth(month, "");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.error("refreshData() error. ",e);
		}
	}
	
}
