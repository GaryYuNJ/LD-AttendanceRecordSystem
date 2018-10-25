package com.ldars.mongo.service;

import java.text.ParseException;


public interface IMongoService {

	void refreshALLAttendenceReport() throws ParseException;

	void refreshAttendenceReportByMonth_old(String month, String mobileParam)
			throws ParseException;

	void refreshAttendenceReportByMonthExtend(String month, String mobileParam)
			throws ParseException;

	void refreshMonthlyUserUdidReport(String month) throws ParseException; 
	
	
	
}
