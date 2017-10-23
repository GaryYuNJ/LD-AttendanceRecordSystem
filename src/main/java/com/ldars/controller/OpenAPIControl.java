package com.ldars.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.ldars.data.APIMessage;
import com.ldars.data.BootstrapTableData;
import com.ldars.data.SelectCompany;
import com.ldars.mongo.bo.AttendenceReportBo;
import com.ldars.mongo.dao.AttendenceDao;
import com.ldars.mongo.dao.AttendenceReportDao;
import com.ldars.mongo.service.IMongoService;
import com.mysql.jdbc.StringUtils;

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
	
	
	//测试，修改临时数据
	@RequestMapping(value="test",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String test(@RequestParam("month")  String month, @RequestParam("mobile")  String mobile, ModelMap model){
		
		//List<AttendenceBo> list = attendenceDao.list();
		try {
			
			AttendenceReportBo aRBO = attendenceReportDao.findByMonthAndMobile(month, mobile);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
	
	@RequestMapping(value="refreshDataByMonthAndMobile.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String refreshDataByMonthAndMobile(@RequestParam("month")  String month, @RequestParam("mobile")  String mobile, ModelMap model){
		
		try {
			mongoService.refreshAttendenceReportByMonth(month, mobile);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
	
	
	//更新月度考勤报表
	@RequestMapping(value="refreshDataByMonth.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String refreshDataByMonth(@RequestParam("month")  String month, ModelMap model){
		
		//List<AttendenceBo> list = attendenceDao.list();
		try {
			mongoService.refreshAttendenceReportByMonth(month,"");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
	
	
	//临时功能
	@RequestMapping(value="refreshALLData.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String refreshALLData( ModelMap model){
		
		//List<AttendenceBo> list = attendenceDao.list();
		try {
			mongoService.refreshALLAttendenceReport();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "error";
		}
		return "ok";
	}
	
	List<SelectCompany> getCompanyList(String user){
		//pz001  admin000 admin001 admin002 admin003 admin004
			//临时应急，先写死
			SelectCompany company1 = new SelectCompany();
			company1.setName("全部公司");
			company1.setValue("1");
			SelectCompany company2 = new SelectCompany();
			company2.setName("江苏事业部");
			company2.setValue("江苏事业部");
			SelectCompany company3 = new SelectCompany();
			company3.setName("一公司");
			company3.setValue("一公司");
			SelectCompany company4 = new SelectCompany();
			company4.setName("二公司");
			company4.setValue("二公司");
			SelectCompany company5 = new SelectCompany();
			company5.setName("三公司");
			company5.setValue("三公司");
			SelectCompany company6 = new SelectCompany();
			company6.setName("四公司");
			company6.setValue("四公司");
			SelectCompany company7 = new SelectCompany();
			company7.setName("区域公司");
			company7.setValue("区域公司");
			
			List<SelectCompany> companys = new ArrayList<SelectCompany>();
			
			if("pz001".equals(user)){
				companys.add(company1);
				companys.add(company2);
				companys.add(company3);
				companys.add(company4);
				companys.add(company5);
				companys.add(company6);
				companys.add(company7);
			}else if("admin000".equals(user)){
				companys.add(company2);
			}else if("admin001".equals(user)){
				companys.add(company3);
			}else if("admin002".equals(user)){
				companys.add(company4);
			}else if("admin003".equals(user)){
				companys.add(company5);
			}else if("admin004".equals(user)){
				companys.add(company6);
			}else if("admin005".equals(user)){
				companys.add(company7);
			}
			
			return companys;
	}
	
	@RequestMapping(value="attendenceList",method=RequestMethod.GET)
	public String attendenceList( HttpServletRequest request,
			ModelMap model,  String user){
		
		//页面菜单样式需要
		model.put("pageIndex", 0);
				
		if(!StringUtils.isNullOrEmpty(user) ){
			request.getSession().setAttribute("user", user);
		}
		//如果用户未登陆
		if(null == request.getSession().getAttribute("user") || StringUtils.isNullOrEmpty(request.getSession().getAttribute("user").toString())){
			return "redirect:http://admin.greenlandjs.com/auth/login";
		}
		
		model.put("companys", this.getCompanyList(null == request.getSession().getAttribute("user")?"":request.getSession().getAttribute("user").toString()));
		model.put("user", user);
		return "attendenceList";
	}
	

	@RequestMapping(value="attendenceDailyList",method=RequestMethod.GET)
	public String attendenceDailyList(HttpServletRequest request, ModelMap model, String user){
		
		//页面菜单样式需要
		model.put("pageIndex", 1);
				
		if(!StringUtils.isNullOrEmpty(user) ){
			request.getSession().setAttribute("user", user);
		}
		
		//如果用户未登陆
		if(null == request.getSession().getAttribute("user") || StringUtils.isNullOrEmpty(request.getSession().getAttribute("user").toString())){
			return "redirect:http://admin.greenlandjs.com/auth/login";
		}
		
		model.put("companys", this.getCompanyList(null == request.getSession().getAttribute("user")?"":request.getSession().getAttribute("user").toString()));
		model.put("user", user);
		return "attendenceDailyList";
	}
	
	@RequestMapping(value="attendenceReportList.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String attendenceReportList(HttpServletRequest request, @RequestParam("month")  String month, @RequestParam("company")  String company, 
			String department, String mobile, String userName, String dataType, Integer limit, 
			Integer offset, ModelMap model){
		
		//如果用户未登陆
		if(null == request.getSession().getAttribute("user") || StringUtils.isNullOrEmpty(request.getSession().getAttribute("user").toString())){
			BootstrapTableData bData = new BootstrapTableData();
			return JSON.toJSONString(bData);
		}
		
		if("1".equals(company)){
			company = "";
		}
		
		BootstrapTableData bData = new BootstrapTableData();
		List<AttendenceReportBo> arBos;
		//判断是分页查还是全量查询
		if(null == offset || null == limit){
			arBos = attendenceReportDao.selectPagebyConditions(company,department, month, userName, mobile,null,dataType);
			
			
			if(null != arBos && arBos.size() > 0){
				bData.setPage(1);
				bData.setPageSize(arBos.size());
				bData.setTotal(Long.parseLong(arBos.size()+""));
			}
		}else{
			bData.setPage(offset/limit +1);
			bData.setPageSize(limit);
			// dataType 0 全部记录，1 异常记录
			arBos = attendenceReportDao.selectPagebyConditions(company,department, month, userName, mobile, bData,dataType);
		}
		
		if(null != arBos && arBos.size() > 0){
			List<Map<String,String>> map = new ArrayList<Map<String,String>>();
			//重新组织数据,用于页面展示
			for(AttendenceReportBo arbo : arBos){
				//把备注添加到页面返回列表种
				Map<String, String> newMap= arbo.getAttendenceDetail();
				newMap.put("remark", arbo.getRemark());
				newMap.put("deviceTotal", arbo.getDeviceTotal()+"");
				map.add(newMap);
			}
			bData.setRows(map);
		}else{
			bData.setPage(0);
			bData.setRows(new Object());
			bData.setTotal(0L);
			bData.setPageSize(0);
		}
		
		return JSON.toJSONString(bData);
	}
	
	@RequestMapping(value="attendenceDailyReportList.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String attendenceDailyReportList(HttpServletRequest request, @RequestParam("startDate")  String startDateStr, 
			@RequestParam("endDate")  String endDateStr, @RequestParam("company")  String company, String department,
			String mobile, String userName, String dataType, Integer limit, 
			 Integer offset, ModelMap model){
		
		SimpleDateFormat sfDay = new  SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sfDate = new  SimpleDateFormat("yyyy-MM");
		Date startDate;
		try {
			startDate = sfDay.parse(startDateStr);
			//根据startDate获取月份
			//每月日期未上月24号到当月23号
			Calendar startCalendar = Calendar.getInstance();//日历对象 
			startCalendar.setTime(startDate);//设置日期 
			if(startDate.getDate() >= 24){
				startCalendar.add(Calendar.MONTH, 1);//月份加
			}
			String month = sfDate.format(startCalendar.getTime());
			return this.attendenceReportList(request, month, company, department, mobile, userName, dataType, limit, offset, model);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	//获取报表动态表头,整月的时间段
	@RequestMapping(value="attendenceReportColumnName.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String attendenceReportColumnName(@RequestParam("month")  String month, String company, String department, 
			String mobile, String userName, ModelMap model){
		
		BootstrapTableData bData = new BootstrapTableData();
		bData.setPage(1);
		bData.setPageSize(1);
		List<AttendenceReportBo> arBos = attendenceReportDao.selectPagebyConditions(company,department, month, userName, mobile, bData,"0");
		List<Map<String, String>> columns = new ArrayList<Map<String, String>>();
		if(null != arBos && arBos.size() > 0){
			Map<String, String> map = arBos.get(0).getAttendenceDetail();
			for(String key : map.keySet()){
				Map<String, String> column = new LinkedHashMap<String, String>();
				column.put("column", key);
				columns.add(column);
			}
			//加上打卡手机数量
			Map<String, String> column1 = new LinkedHashMap<String, String>();
			column1.put("column", "deviceTotal");
			columns.add(column1);
			//最后加上备注
			Map<String, String> column = new LinkedHashMap<String, String>();
			column.put("column", "remark");
			columns.add(column);
		}
		
		return JSON.toJSONString(columns);
	}
	
	//获取报表动态表头,根据时间段，获取表头（目前是从月度报表搜素，所以只能搜索正月之内的时间）
	@RequestMapping(value="attendenceDailyReportColumnName.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String attendenceDailyReportColumnName(@RequestParam("startDate")  String startDateStr,
			@RequestParam("endDate")  String endDateStr, String company,  String department,
			String mobile, String userName, ModelMap model){
		
		
		
		//通过起始时间获取月度
		SimpleDateFormat sf = new  SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf1 = new  SimpleDateFormat("yyyy-MM");
		List<Map<String, String>> columns = new ArrayList<Map<String, String>>();
		
		try {
			Date startDate = sf.parse(startDateStr);
			Date endDate = sf.parse(endDateStr);
			//根据startDate获取月份
			//每月日期未上月24号到当月23号
			Calendar startCalendar = Calendar.getInstance();//日历对象 
			startCalendar.setTime(startDate);//设置日期  
			if(startDate.getDate() >= 24){
				startCalendar.add(Calendar.MONTH, 1);//月份加
			} 
			
			//获取指定月份，和 上个月份的 string
			String month = sf1.format(startCalendar.getTime());
			startCalendar.add(Calendar.MONTH, -1);
			String lastMonth = sf1.format(startCalendar.getTime());
			
			//获取整月的表头，再剔除所选时间范围外的
			BootstrapTableData bData = new BootstrapTableData();
			bData.setPage(1);
			bData.setPageSize(1);
			List<AttendenceReportBo> arBos = attendenceReportDao.selectPagebyConditions(company,department, month, userName, mobile, bData,"0");
			
			if(null != arBos && arBos.size() > 0){
				Map<String, String> map = arBos.get(0).getAttendenceDetail();
				for(String key : map.keySet()){
					//从月度columns中，剔除所选时间范围之外的
					//只判断日期字段, 并且在所选时间范围外，不加入表单头
					//month 与 startDate 可能不一样，都要判断
					//去掉月份的展示
					if(key.equals("month") ||
							((key.contains(month) || key.contains(lastMonth)) && (sf.parse(key).before(startDate) || sf.parse(key).after(endDate)))){
							continue;
					}
					
					Map<String, String> column = new LinkedHashMap<String, String>();
					column.put("column", key);
					columns.add(column);
				}
				//加上打卡手机数量
//				Map<String, String> column1 = new LinkedHashMap<String, String>();
//				column1.put("column", "deviceTotal");
//				columns.add(column1);
				//最后加上备注
				Map<String, String> column = new LinkedHashMap<String, String>();
				column.put("column", "remark");
				columns.add(column);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return JSON.toJSONString(columns);
	}
	

	//修改备注
	@RequestMapping(value="modifyRemark.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String modifyRemark(@RequestParam("month")  String month, @RequestParam("mobile")  String mobile, 
			@RequestParam("remark") String remark, ModelMap model){
		
		APIMessage message = new APIMessage();
		AttendenceReportBo arBo = attendenceReportDao.findByMonthAndMobile(month, mobile);
		
		if(null != arBo && !StringUtils.isNullOrEmpty(arBo.getId())){
			arBo.setRemark(remark);
			attendenceReportDao.saveOrUpdate(arBo);
			message.setStatus(1);
		}else{
			message.setStatus(0);
		}
		
		
		return JSON.toJSONString(message);
	}
	
	@RequestMapping(value="queryMonthSummary.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String queryMonthSummary(HttpServletRequest request, @RequestParam("month")  String month, @RequestParam("company")  String company, 
			String department, String mobile, String userName, String dataType, Integer limit, 
			Integer offset, ModelMap model){
		APIMessage message = new APIMessage();
		Map<String, Long> result = new HashMap<String, Long>();
		//如果用户未登陆
		if(null == request.getSession().getAttribute("user") || StringUtils.isNullOrEmpty(request.getSession().getAttribute("user").toString())){
			BootstrapTableData bData = new BootstrapTableData();
			
			result.put("unCheckAmount", 0L);
			result.put("lateAmount", 0L);
			result.put("earlyLeaveAmount", 0L);
			
			message.setContent(result);
			message.setStatus(-10);
			return JSON.toJSONString(message);
		}
		
		if("1".equals(company)){
			company = "";
		}
		Long unCheckAmount = attendenceReportDao.calculateMonthSumbyConditions(company, department, month, userName, mobile, "unCheckAmount");
		Long lateAmount = attendenceReportDao.calculateMonthSumbyConditions(company, department, month, userName, mobile, "lateAmount");
		Long earlyLeaveAmount = attendenceReportDao.calculateMonthSumbyConditions(company, department, month, userName, mobile, "earlyLeaveAmount");
		

		result.put("unCheckAmount", unCheckAmount);
		result.put("lateAmount", lateAmount);
		result.put("earlyLeaveAmount", earlyLeaveAmount);
		
		message.setContent(result);
		message.setStatus(1);
		return JSON.toJSONString(message);
	}
	
}
