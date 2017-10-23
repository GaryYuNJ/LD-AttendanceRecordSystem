package com.ldars.mongo.service;

import java.text.ParseException;


public interface IMongoService {

	void refreshALLAttendenceReport() throws ParseException;

	void refreshAttendenceReportByMonth(String month, String mobileParam)
			throws ParseException; 
	
	
	
}
