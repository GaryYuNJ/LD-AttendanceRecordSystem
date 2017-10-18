package com.ldars.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ldars.data.BootstrapTableData;
import com.ldars.mongo.bo.AttendenceReportBo;
import com.ldars.mongo.dao.AttendenceDao;
import com.ldars.mongo.dao.AttendenceReportDao;
import com.ldars.mongo.service.IMongoService;

@Controller
@RequestMapping(value = "openApi")
public class OpenAPIControl {
	
	final static Logger log = Logger.getLogger(OpenAPIControl.class);
	
	@Autowired
	private AttendenceDao attendenceDao;
	
	@Autowired
	private AttendenceReportDao attendenceReportDao;
	
	@Autowired
	private IMongoService mongoService;
	
	@RequestMapping(value="refreshDataByMonth.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String refreshDataByMonth(@RequestParam("month")  String month, ModelMap model){
		//页面菜单样式需要
		model.put("pageIndex", 1);
		
		//List<AttendenceBo> list = attendenceDao.list();
		try {
			mongoService.refreshAttendenceReportByMonth(month);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
	
	@RequestMapping(value="attendenceList",method=RequestMethod.GET)
	public String userManage( ModelMap model){
		//页面菜单样式需要
		model.put("pageIndex", 1);
		return "attendenceList";
	}
	
	@RequestMapping(value="attendenceReportList.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String attendenceReportList(@RequestParam("month")  String month, String company, 
			String mobile, String userName, @RequestParam("limit") Integer limit, 
			@RequestParam("offset") Integer offset, ModelMap model){
		
		BootstrapTableData bData = new BootstrapTableData();
		bData.setPage(offset/limit +1);
		bData.setPageSize(limit);
		
		List<AttendenceReportBo> arBos = attendenceReportDao.selectPagebyConditions(company, month, userName, mobile, bData);
		if(null != arBos && arBos.size() > 0){
			List<Map<String,String>> map = new ArrayList<Map<String,String>>();
			//重新组织数据,用于页面展示
			for(AttendenceReportBo arbo : arBos){
				map.add(arbo.getAttendenceDetail());
			}
			bData.setRows(map);
		}else{
			bData.setPage(0);
			bData.setRows(new Object());
			bData.setTotal(0L);
		}
		
		return JSON.toJSONString(bData);
	}
	
	//获取报表动态表头
	@RequestMapping(value="attendenceReportColumnName.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String attendenceReportColumnName(@RequestParam("month")  String month, String company, 
			String mobile, String userName, ModelMap model){
		
		BootstrapTableData bData = new BootstrapTableData();
		bData.setPage(1);
		bData.setPageSize(1);
		List<AttendenceReportBo> arBos = attendenceReportDao.selectPagebyConditions(company, month, userName, mobile, bData);
		List<Map<String, String>> columns = new ArrayList<Map<String, String>>();
		if(null != arBos && arBos.size() > 0){
			Map<String, String> map = arBos.get(0).getAttendenceDetail();
			for(String key : map.keySet()){
				Map<String, String> column = new LinkedHashMap<String, String>();
				column.put("column", key);
				columns.add(column);
			}
		}
		return JSON.toJSONString(columns);
	}
	
}
