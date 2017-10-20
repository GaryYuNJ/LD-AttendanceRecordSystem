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
	
	//逐月更新考勤报表
	@RequestMapping(value="refreshDataByMonth.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String refreshDataByMonth(@RequestParam("month")  String month, ModelMap model){
		
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
	public String attendenceList( ModelMap model, @RequestParam("user")  String user){
		
		//页面菜单样式需要
		model.put("pageIndex", 1);
				
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
		
		//页面菜单样式需要
		model.put("pageIndex", 1);
		model.put("companys", companys);
		model.put("user", user);
		return "attendenceList";
	}
	

	@RequestMapping(value="attendenceDailyList",method=RequestMethod.GET)
	public String attendenceDailyList( ModelMap model, @RequestParam("user")  String user){
		
		//页面菜单样式需要
		model.put("pageIndex", 2);
				
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
		
		//页面菜单样式需要
		model.put("pageIndex", 1);
		model.put("companys", companys);
		model.put("user", user);
		return "attendenceList";
	}
	
	@RequestMapping(value="attendenceReportList.json",method = { RequestMethod.GET,
			RequestMethod.POST },produces = "application/json; charset=utf-8")
	@ResponseBody
	public String attendenceReportList(@RequestParam("month")  String month, @RequestParam("company")  String company, 
			String mobile, String userName, String dataType, @RequestParam("limit") Integer limit, 
			@RequestParam("offset") Integer offset, ModelMap model){
		
		if("1".equals(company)){
			company = "";
		}
		BootstrapTableData bData = new BootstrapTableData();
		bData.setPage(offset/limit +1);
		bData.setPageSize(limit);
		
		// dataType 0 全部记录，1 异常记录
		List<AttendenceReportBo> arBos = attendenceReportDao.selectPagebyConditions(company, month, userName, mobile, bData,dataType);
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
		List<AttendenceReportBo> arBos = attendenceReportDao.selectPagebyConditions(company, month, userName, mobile, bData,"0");
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
	

	//获取报表动态表头
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
	
}
