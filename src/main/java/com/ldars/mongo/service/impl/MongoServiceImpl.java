package com.ldars.mongo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ldars.mongo.bo.AttendenceBo;
import com.ldars.mongo.bo.AttendenceMonthlySummaryBo;
import com.ldars.mongo.bo.AttendenceReportBo;
import com.ldars.mongo.dao.AttendenceDao;
import com.ldars.mongo.dao.AttendenceMonthlySummaryDao;
import com.ldars.mongo.dao.AttendenceReportDao;
import com.ldars.mongo.service.IMongoService;
import com.ldars.oa.dao.OAUserMapper;
import com.ldars.oa.dao.UserOtherInfoMapper;
import com.ldars.oa.dao.UserUdidMonthlyReportMapper;
import com.ldars.oa.model.UserOtherInfo;
import com.ldars.oa.model.UserUdidMonthlyReport;

@Service("mongoService")
public class MongoServiceImpl implements IMongoService {

	final static Logger logger = Logger.getLogger(MongoServiceImpl.class);
	
	@Autowired
	private AttendenceDao attendenceDao;
	
	@Autowired
	private AttendenceMonthlySummaryDao attenMonthlySummaryDao;
	
	@Autowired
	private AttendenceReportDao attendenceReportDao;
	
	@Autowired 
	OAUserMapper oAUserMapper;
	
	@Autowired 
	UserOtherInfoMapper userOtherInfoMapper;
	
	@Autowired  
	private HttpSession session;
	@Autowired  
	UserUdidMonthlyReportMapper userUdidMonthlyReportMapper;
	
	//临时功能
		@Override
		public void refreshMonthlyUserUdidReport(String month) throws ParseException {
			
			//获取每月使用不同打卡手机数量超过0次用户月报表
			List<AttendenceReportBo> userMonthReports = attendenceReportDao.findByMonthAndDeviceCount(month, 0);
			
			//获取每月起始时间与结束时间
	        SimpleDateFormat sf = new  SimpleDateFormat("yyyy-MM-dd");
	        SimpleDateFormat sf2 = new  SimpleDateFormat("HH:mm");
	        SimpleDateFormat sf3 = new  SimpleDateFormat("yyyy-MM-dd HH:mm");
	        SimpleDateFormat sf4 = new  SimpleDateFormat("MM-dd");
	        
	        Calendar startCalendar = Calendar.getInstance();//日历对象 
	        Calendar startCalendarTMP = Calendar.getInstance();//用于for循环获取当月的日期列表
	        startCalendar.setTime(sf3.parse(month+"-24 06:00"));//设置起始日期 //当天的打卡数据（早上6点到第二天6点算当这一天的有效时间，同APP） 
	        startCalendar.add(Calendar.MONTH, -1);//月份减一 
	        
	        Calendar endCalendar = Calendar.getInstance();//日历对象 
	        endCalendar.setTime(sf3.parse(month+"-24 06:00"));//设置结束日期//获取的打卡数据（早上6点到第二天6点算当这一天的有效时间，同APP）
	        if(endCalendar.getTime().after(new Date())){
	            //通过new Date()得到日期+06：00
	            String date = sf.format(new Date());                
	            endCalendar.setTime( sf3.parse(date+" 06:00"));
	        }       
	        
			//循环处理每个用户
			for(AttendenceReportBo reportBo : userMonthReports){
				try{
					for(String udid : reportBo.getDeviceList()){
						//udid处理下，去除=
						//"udid=6e7c54525f870b3b,phonemodel=HUAWEI Y600D-C00"
						//"udid=14bd1236142b060392e51c1e3b7dcd76db4d70f7,phonemodel=iPhone"
						
						String udidTmp = udid;
						if(StringUtils.isEmpty(udid)){
							continue;
						}else if(udid.contains("=")){
							udidTmp = udid.split("=")[1].split(",")[0];
						}
						
						List<AttendenceBo> attBos 
							= attendenceDao.findByStartEndDateMobileAndUdid
								(startCalendar.getTime().getTime()/1000, endCalendar.getTime().getTime()/1000, reportBo.getMobile(),udidTmp);
						
						//获取打卡日期列表
						StringBuffer datesStr = new StringBuffer("");
						for(AttendenceBo attendenceBo : attBos){
							if(datesStr.toString().contains(attendenceBo.getAttendence_date())){
								continue;
							}
							datesStr.append(attendenceBo.getAttendence_date()+",");
						}
						//获取打卡次数
						int count = attBos.size();
						
						UserUdidMonthlyReport userUdidMonthlyReport = new UserUdidMonthlyReport();
						userUdidMonthlyReport.setCompany(reportBo.getCompany());
						userUdidMonthlyReport.setDeviceCount(reportBo.getDeviceTotal());
						userUdidMonthlyReport.setCount(count);
						userUdidMonthlyReport.setCreateTime(new Date());
						userUdidMonthlyReport.setDates(datesStr.toString());
						userUdidMonthlyReport.setDepartment(reportBo.getDepartment());
						userUdidMonthlyReport.setUdid(udid);
						userUdidMonthlyReport.setMonth(month);
						userUdidMonthlyReport.setUserMobile(reportBo.getMobile());
						userUdidMonthlyReport.setUserName(reportBo.getRealName());
						
						userUdidMonthlyReportMapper.insertSelective(userUdidMonthlyReport);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			//获取每个udid最多的用户为主人，并更新到报表
			//先获取每月udid list
			List<String> udidList = userUdidMonthlyReportMapper.selectUdidByMonth(month);
			//循环找到最多的记录为主人，并批量更新
			UserUdidMonthlyReport userUdidMonthlyReportTmp = null;
			for(String udid : udidList){
				List<UserUdidMonthlyReport> list = userUdidMonthlyReportMapper.selectByMonthAndudid(month, udid);
				if(null == list || list.size() == 0){
					continue;
				}
				userUdidMonthlyReportTmp = new UserUdidMonthlyReport();
				userUdidMonthlyReportTmp.setUdid(udid);
				userUdidMonthlyReportTmp.setMonth(month);
				userUdidMonthlyReportTmp.setUidiHostCompany(list.get(0).getCompany());
				userUdidMonthlyReportTmp.setUidiHostDepartment(list.get(0).getDepartment());
				userUdidMonthlyReportTmp.setUidiHostMobile(list.get(0).getUserMobile());
				userUdidMonthlyReportTmp.setUidiHostName(list.get(0).getUserName());
				userUdidMonthlyReportMapper.updateByUdidAndMonthSelective(userUdidMonthlyReportTmp);
				//list.get(0)
			}
		}
	
	//临时功能
	@Override
	public void refreshALLAttendenceReport() throws ParseException {
		
//		List<String> months = new ArrayList<String>();
//		months.add("2017-09");
//		months.add("2017-08");
//		months.add("2017-07");
//		months.add("2017-06");
//		months.add("2017-05");
//		months.add("2017-04");
//		months.add("2017-03");
//		months.add("2017-02");
//		months.add("2017-01");
//		months.add("2016-12");
//		months.add("2016-11");
//		months.add("2016-10");
//		months.add("2016-09");
//		months.add("2016-08");
//		months.add("2016-07");
//		months.add("2016-06");
//		months.add("2016-05");
//		
//		for(String month : months){
//			refreshAttendenceReportByMonth(month);
//		}
		
	}

	//以打卡记录为准
	@Override
	public void refreshAttendenceReportByMonth_old(String month, String mobileParam) throws ParseException {
		
		// TODO Auto-generated method stub
		SimpleDateFormat sf = new  SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf2 = new  SimpleDateFormat("HH:mm");
		SimpleDateFormat sf3 = new  SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sf4 = new  SimpleDateFormat("MM-dd");
		
		Calendar startCalendar = Calendar.getInstance();//日历对象 
		Calendar startCalendarTMP = Calendar.getInstance();//用于for循环获取当月的日期列表
		startCalendar.setTime(sf3.parse(month+"-24 06:00"));//设置起始日期 //当天的打卡数据（早上6点到第二天6点算当这一天的有效时间，同APP） 
		startCalendar.add(Calendar.MONTH, -1);//月份减一 
        Date startDateTime = startCalendar.getTime();
        
        Calendar endCalendar = Calendar.getInstance();//日历对象 
        endCalendar.setTime(sf3.parse(month+"-24 06:00"));//设置结束日期//获取的打卡数据（早上6点到第二天6点算当这一天的有效时间，同APP）
        if(endCalendar.getTime().after(new Date())){
        	endCalendar.setTime(new Date());
        }
        Date endDateTime = endCalendar.getTime();
        
//		String startDateTimeStr = month + " 00:00:01";
//		String endDateTimeStr = month + " 23:59:59";
//		SimpleDateFormat sf1 = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date startDateTime = sf1.parse(startDateTimeStr);
//		Date endDateTime = sf1.parse(endDateTimeStr);
        List<AttendenceBo> aBoList;
        
        //手机号参数为空，查询整月所有人数据
        if(StringUtils.isEmpty(mobileParam)){
        	aBoList = attendenceDao.findByStartEndDate(startDateTime.getTime()/1000, endDateTime.getTime()/1000);
        //手机号不为空，查询指定用户
        }else{
        	aBoList = attendenceDao.findByStartEndDateAndMobile(startDateTime.getTime()/1000, endDateTime.getTime()/1000,mobileParam);
        }
		
        //记录处理过的手机号，每个手机号只循环处理一次
		List<String> mobileList = new ArrayList<String>(); 
		for(AttendenceBo abo : aBoList){
			try{
				//每个手机号只循环一次
				if(mobileList.contains(abo.getMobile())){
					continue;
				}
				mobileList.add(abo.getMobile());
				
				//for monthly summary 
				int unCheckAmount = 0; //未打卡总数
				int lateAmount = 0; //迟到数量
				int earlyLeaveAmount = 0; //早退数量
				
				//查询当月用户是否已有数据
				AttendenceReportBo aRBo = attendenceReportDao.findByMonthAndMobile(month, abo.getMobile());
				if(null == aRBo || StringUtils.isEmpty(aRBo.getId())){
					aRBo = new AttendenceReportBo();
					aRBo.setMonth(month);
					aRBo.setMobile(abo.getMobile());
				}
				
				//查询OA数据库获取组织,姓名,上下班时间数据
				//OAUser oaUser = oAUserMapper.selectWithOrgInfoByMobile(abo.getMobile());
//				String realName = null != oaUser?oaUser.getRealname():abo.getName();
//				String company = null != oaUser && null!=oaUser.getCompany()?oaUser.getCompany().getName():"";
//				String department = null != oaUser && null!=oaUser.getDepartment()?oaUser.getDepartment().getName():"";
//				String amTime = "8:30";
//				String pmTime = "17:30";
				//				if(null != oaUser && null != oaUser.getOrgWorktime()
//						&& !StringUtils.isEmpty(oaUser.getOrgWorktime().getAmHour())
//						&& !StringUtils.isEmpty(oaUser.getOrgWorktime().getPmHour())
//						&& !StringUtils.isEmpty(oaUser.getOrgWorktime().getAmMinute())
//						&& !StringUtils.isEmpty(oaUser.getOrgWorktime().getPmMinute())){
//					
//					String amMi = oaUser.getOrgWorktime().getAmMinute() == 0 ? "00":(oaUser.getOrgWorktime().getAmMinute()+"");
//					String pmMi	= oaUser.getOrgWorktime().getPmMinute() == 0 ? "00":(oaUser.getOrgWorktime().getPmMinute()+"");
//					amTime = oaUser.getOrgWorktime().getAmHour()+":" + amMi;
//					pmTime = oaUser.getOrgWorktime().getPmHour()+":" + pmMi;
//				}
				
				UserOtherInfo uinfo = userOtherInfoMapper.selectByMobile(abo.getMobile());
				String realName = null != uinfo?uinfo.getName():abo.getName();
				String company = null != uinfo && null!=uinfo.getCompany()?uinfo.getCompany():"";
				String department = null != uinfo && null!=uinfo.getDepartment()?uinfo.getDepartment():"";
				int sequence =  null != uinfo && null!=uinfo.getSequence()?uinfo.getSequence():10000;
				String project = null != uinfo && null!=uinfo.getProject()?uinfo.getProject():"";
				String amTime = null != uinfo && null !=uinfo.getAmTime() && !"".equals(StringUtils.trimAllWhitespace(uinfo.getAmTime()))?StringUtils.trimAllWhitespace(uinfo.getAmTime()):"08:30";
				String pmTime = null != uinfo && null!=uinfo.getPmTime() && !"".equals(StringUtils.trimAllWhitespace(uinfo.getPmTime()))?StringUtils.trimAllWhitespace(uinfo.getPmTime()):"17:30";
//				amTime = StringUtils.trimAllWhitespace(amTime);
//				pmTime = StringUtils.trimAllWhitespace(pmTime);
				amTime.replaceAll("：", ":");
				pmTime.replaceAll("：", ":");
				
				//8:30要改成 08:30
				if(amTime.length() == 4){
					amTime = "0" + amTime;
				}
				if(pmTime.length() == 4){
					pmTime = "0" + pmTime;
				}
				
				aRBo.setRealName(realName);
				aRBo.setCompany(company);
				aRBo.setDepartment(department);
				aRBo.setProject(project);
				aRBo.setSequence(sequence);
				aRBo.setAmWorkTime(amTime);
				aRBo.setPmWorkTime(pmTime);
				
				aRBo.setWorkTime(amTime +" - "+ pmTime);
				aRBo.setUpdateTime(new Date().getTime());
				
				//考勤数据
				Map<String,String> attendenceDetail = new LinkedHashMap<String,String> ();
				attendenceDetail.put("month", month);
				attendenceDetail.put("realName", realName);
				attendenceDetail.put("company", company);
				attendenceDetail.put("department", department);
				attendenceDetail.put("project", project);
				//attendenceDetail.put("workTime", amTime+" - "+pmTime);
				attendenceDetail.put("mobile", abo.getMobile());
				attendenceDetail.put("amWorkTime", amTime);
				attendenceDetail.put("pmWorkTime", pmTime);
				
				startCalendarTMP.setTime(startCalendar.getTime());//重新赋值
				
				List<String> devices = new ArrayList<String>();
				
				//按这个月天循环获取考勤时间
				for(;startCalendarTMP.getTime().getTime() < endCalendar.getTime().getTime(); 
						startCalendarTMP.add(Calendar.DATE, +1)){
					
					String dateStr = sf4.format(startCalendarTMP.getTime());
					String dateWithYearStr = sf.format(startCalendarTMP.getTime());
					
					//获取当天的打卡数据（早上6点到第二天6点算当这一天的有效时间，同APP）
					List<AttendenceBo> aBos = attendenceDao.findByStartEndDateAndMobile(startCalendarTMP.getTime().getTime()/1000 , 
							startCalendarTMP.getTime().getTime()/1000 + 24 * 60 * 60 , abo.getMobile());
					
//					Long starttmp = startCalendarTMP.getTime().getTime()/1000 + 6 * 60 * 60;
//					Long endtmp = startCalendarTMP.getTime().getTime()/1000 + 30 * 60 * 60;
//					System.out.println(starttmp);
//					System.out.println(sf1.format(new Date(starttmp*1000)));
//					System.out.println(endtmp);
//					System.out.println(sf1.format(new Date(endtmp*1000)));
					
					//List<AttendenceBo> aBo = attendenceDao.findByAttendanceDateAndMobile(dateStr, abo.getMobile());
					Date amCheckTime = null;
					Date pmCheckTime = null;
					if(null != aBos && aBos.size() > 0){
						if(aBos.size()==1){
							if("am".equals( aBos.get(0).getTag())){
								amCheckTime = new Date(aBos.get(0).getAttendence_time() * 1000);
								attendenceDetail.put(dateStr, sf2.format(amCheckTime)+" - 无");
							}else{
								pmCheckTime = new Date(aBos.get(0).getAttendence_time() * 1000);
								attendenceDetail.put(dateStr, "无  - " + sf2.format(pmCheckTime));
							}
						}else{
							amCheckTime = new Date(aBos.get(0).getAttendence_time() * 1000);
							pmCheckTime = new Date(aBos.get(aBos.size()-1).getAttendence_time() * 1000);
							attendenceDetail.put(dateStr, sf2.format(new Date(aBos.get(0).getAttendence_time() * 1000)) 
									+" - "+sf2.format(new Date(aBos.get(aBos.size()-1).getAttendence_time() * 1000)));
						}
					}else{
						//法定假日判断、出差、请假判断
						//.....
						//attendenceDetail.put(dateStr, "缺勤");
						attendenceDetail.put(dateStr, "-");
					}
					
//					int unCheckAmount = 0; //未打卡总数
//					int lateAmount = 0; //迟到数量
//					int earlyLeaveAmount = 0; //早退数量
					
					////累加迟到、早退、未打卡次数(要跟每个人的上下班时间做对比，每个人配置不一样。默认8:30-17:30)
//					//先判断是否节假日
//					Calendar calendarT = Calendar.getInstance();//日历对象
//					calendarT.setTime(amCheckTime);
					int weekday = startCalendarTMP.get(Calendar.DAY_OF_WEEK) - 1;
					if( weekday != 6 && weekday != 0){
						
						//累加迟到、早退、未打卡次数(要跟每个人的上下班时间做对比，每个人配置不一样。默认8:30-17:30)
						if(null == amCheckTime){
							unCheckAmount ++;
						}else{
							//组装当日要求打卡时间比较
							String userAmCheckTimeStr = sf.format(amCheckTime) + " " + amTime;
							Date userAmCheckTime = sf3.parse(userAmCheckTimeStr);
							//实际打卡时间与要求打卡时间比较
							//if(amCheckTime.after(userAmCheckTime)){  // 从数据看取值
							if((amCheckTime.getTime()-userAmCheckTime.getTime())/1000 >= 60){ //8点31前打卡的都不算
								lateAmount ++;
							}
						}
						
						//累加迟到、早退、未打卡次数
						if(null == pmCheckTime){
							unCheckAmount ++;
						}else{
							//组装当日要求打卡时间比较
							String userPmCheckTimeStr = dateWithYearStr + " " + pmTime;
							Date userPmCheckTime = sf3.parse(userPmCheckTimeStr);
							//实际打卡时间与要求打卡时间比较
							if(pmCheckTime.before(userPmCheckTime)){  // 从数据看取值
								earlyLeaveAmount ++;
							}
						}
					}
					
					//截取手机信息
					for(AttendenceBo aboT : aBos){
						String deviceInfo = aboT.getDevice_info();
						if(!StringUtils.isEmpty(deviceInfo)){
							String udid = "";
							String[] udidStrs =  deviceInfo.split("udid");
							if(udidStrs.length > 1){
								udid = StringUtils.trimWhitespace(udidStrs[1].split(",")[0]);
							}
							String phonemodel = "";
							String[] phonemodels =  deviceInfo.split("phonemodel");
							if(phonemodels.length > 1){
								phonemodel = StringUtils.trimWhitespace(phonemodels[1].split(",")[0]);
							}
							
							deviceInfo = "udid"+udid+",phonemodel"+ phonemodel;
							if(!devices.contains(deviceInfo)){
								devices.add(deviceInfo);
							}
						}
					}
				}
				aRBo.setDeviceList(devices);
				aRBo.setDeviceTotal(devices.size());
				aRBo.setAttendenceDetail(attendenceDetail);
				aRBo.setEarlyLeaveAmount(earlyLeaveAmount);
				aRBo.setLateAmount(lateAmount);
				aRBo.setUnCheckAmount(unCheckAmount);
				
				//更新每月报表
				attendenceReportDao.saveOrUpdate(aRBo);
				
			}
			catch(Exception e){
				//e.printStackTrace();
				logger.error("refreshData() error. ",e);
			}
		}
		
	}  
	
	//以OA的员工信息为基准
	@Override
	public void refreshAttendenceReportByMonthExtend(String month, String mobileParam) throws ParseException {
		
		// TODO Auto-generated method stub
		SimpleDateFormat sf = new  SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf2 = new  SimpleDateFormat("HH:mm");
		SimpleDateFormat sf3 = new  SimpleDateFormat("yyyy-MM-dd HH:mm");
		SimpleDateFormat sf4 = new  SimpleDateFormat("MM-dd");
		
		Calendar startCalendar = Calendar.getInstance();//日历对象 
		Calendar startCalendarTMP = Calendar.getInstance();//用于for循环获取当月的日期列表
		startCalendar.setTime(sf3.parse(month+"-24 06:00"));//设置起始日期 //当天的打卡数据（早上6点到第二天6点算当这一天的有效时间，同APP） 
		startCalendar.add(Calendar.MONTH, -1);//月份减一 
        Date startDateTime = startCalendar.getTime();
        
        Calendar endCalendar = Calendar.getInstance();//日历对象 
        endCalendar.setTime(sf3.parse(month+"-24 06:00"));//设置结束日期//获取的打卡数据（早上6点到第二天6点算当这一天的有效时间，同APP）
        if(endCalendar.getTime().after(new Date())){
        	endCalendar.setTime(new Date());
        }
        Date endDateTime = endCalendar.getTime();
        
//		String startDateTimeStr = month + " 00:00:01";
//		String endDateTimeStr = month + " 23:59:59";
//		SimpleDateFormat sf1 = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date startDateTime = sf1.parse(startDateTimeStr);
//		Date endDateTime = sf1.parse(endDateTimeStr);
        List<UserOtherInfo> userOtherInfoList = new ArrayList<UserOtherInfo>();;
        
        //手机号参数为空，查询整月所有人数据
        if(StringUtils.isEmpty(mobileParam)){
        	userOtherInfoList = userOtherInfoMapper.selectAll();
        //手机号不为空，查询指定用户
        }else{
        	UserOtherInfo userInfo = userOtherInfoMapper.selectByMobile(mobileParam);
        	if(null != userInfo){
        		
        		userOtherInfoList.add(userInfo);
        	}
        }
        
        
		
        //记录处理过的手机号，每个手机号只循环处理一次
		List<String> mobileList = new ArrayList<String>(); 
		//for(AttendenceBo abo : aBoList){
		for(UserOtherInfo uinfo : userOtherInfoList){
			try{

				//每个手机号只循环一次
				if(mobileList.contains(uinfo.getMobile())){
					continue;
				}
				mobileList.add(uinfo.getMobile());
				
				//for monthly summary 
				int unCheckAmount = 0; //未打卡总数
				int lateAmount = 0; //迟到数量
				int earlyLeaveAmount = 0; //早退数量
				
				//查询当月用户是否已有数据
				AttendenceReportBo aRBo = attendenceReportDao.findByMonthAndMobile(month, uinfo.getMobile());
				if(null == aRBo || StringUtils.isEmpty(aRBo.getId())){
					aRBo = new AttendenceReportBo();
					aRBo.setMonth(month);
					aRBo.setMobile(uinfo.getMobile());
				}
				
				//查询OA数据库获取组织,姓名,上下班时间数据
				//OAUser oaUser = oAUserMapper.selectWithOrgInfoByMobile(uinfo.getMobile());
//				String realName = null != oaUser?oaUser.getRealname():abo.getName();
//				String company = null != oaUser && null!=oaUser.getCompany()?oaUser.getCompany().getName():"";
//				String department = null != oaUser && null!=oaUser.getDepartment()?oaUser.getDepartment().getName():"";
//				String amTime = "8:30";
//				String pmTime = "17:30";
				//				if(null != oaUser && null != oaUser.getOrgWorktime()
//						&& !StringUtils.isEmpty(oaUser.getOrgWorktime().getAmHour())
//						&& !StringUtils.isEmpty(oaUser.getOrgWorktime().getPmHour())
//						&& !StringUtils.isEmpty(oaUser.getOrgWorktime().getAmMinute())
//						&& !StringUtils.isEmpty(oaUser.getOrgWorktime().getPmMinute())){
//					
//					String amMi = oaUser.getOrgWorktime().getAmMinute() == 0 ? "00":(oaUser.getOrgWorktime().getAmMinute()+"");
//					String pmMi	= oaUser.getOrgWorktime().getPmMinute() == 0 ? "00":(oaUser.getOrgWorktime().getPmMinute()+"");
//					amTime = oaUser.getOrgWorktime().getAmHour()+":" + amMi;
//					pmTime = oaUser.getOrgWorktime().getPmHour()+":" + pmMi;
//				}
				
				//UserOtherInfo uinfo = userOtherInfoMapper.selectByMobile(uinfo.getMobile());
				String realName = null != uinfo?uinfo.getName():uinfo.getMobile();
				String company = null != uinfo && null!=uinfo.getCompany()?uinfo.getCompany():"";
				String department = null != uinfo && null!=uinfo.getDepartment()?uinfo.getDepartment():"";
				int sequence =  null != uinfo && null!=uinfo.getSequence()?uinfo.getSequence():10000;
				String project = null != uinfo && null!=uinfo.getProject()?uinfo.getProject():"";
				String amTime = null != uinfo && null !=uinfo.getAmTime() && !"".equals(StringUtils.trimAllWhitespace(uinfo.getAmTime()))?StringUtils.trimAllWhitespace(uinfo.getAmTime()):"08:30";
				String pmTime = null != uinfo && null!=uinfo.getPmTime() && !"".equals(StringUtils.trimAllWhitespace(uinfo.getPmTime()))?StringUtils.trimAllWhitespace(uinfo.getPmTime()):"17:30";
//				amTime = StringUtils.trimAllWhitespace(amTime);
//				pmTime = StringUtils.trimAllWhitespace(pmTime);
				amTime.replaceAll("：", ":");
				pmTime.replaceAll("：", ":");
				
				//8:30要改成 08:30
				if(amTime.length() == 4){
					amTime = "0" + amTime;
				}
				if(pmTime.length() == 4){
					pmTime = "0" + pmTime;
				}
				
				aRBo.setRealName(realName);
				aRBo.setCompany(company);
				aRBo.setDepartment(department);
				aRBo.setProject(project);
				aRBo.setSequence(sequence);
				aRBo.setAmWorkTime(amTime);
				aRBo.setPmWorkTime(pmTime);
				
				aRBo.setWorkTime(amTime +" - "+ pmTime);
				aRBo.setUpdateTime(new Date().getTime());
				
				//考勤数据
				Map<String,String> attendenceDetail = new LinkedHashMap<String,String> ();
				attendenceDetail.put("month", month);
				attendenceDetail.put("realName", realName);
				attendenceDetail.put("company", company);
				attendenceDetail.put("department", department);
				attendenceDetail.put("project", project);
				//attendenceDetail.put("workTime", amTime+" - "+pmTime);
				attendenceDetail.put("mobile", uinfo.getMobile());
				attendenceDetail.put("amWorkTime", amTime);
				attendenceDetail.put("pmWorkTime", pmTime);
				
				startCalendarTMP.setTime(startCalendar.getTime());//重新赋值
				
				List<String> devices = new ArrayList<String>();
				
				//按这个月天循环获取考勤时间
				for(;startCalendarTMP.getTime().getTime() < endCalendar.getTime().getTime(); 
						startCalendarTMP.add(Calendar.DATE, +1)){
					
					String dateStr = sf4.format(startCalendarTMP.getTime());
					String dateWithYearStr = sf.format(startCalendarTMP.getTime());
					
					//获取当天的打卡数据（早上6点到第二天6点算当这一天的有效时间，同APP）
					List<AttendenceBo> aBos = attendenceDao.findByStartEndDateAndMobile(startCalendarTMP.getTime().getTime()/1000 , 
							startCalendarTMP.getTime().getTime()/1000 + 24 * 60 * 60 , uinfo.getMobile());
					
//					Long starttmp = startCalendarTMP.getTime().getTime()/1000 + 6 * 60 * 60;
//					Long endtmp = startCalendarTMP.getTime().getTime()/1000 + 30 * 60 * 60;
//					System.out.println(starttmp);
//					System.out.println(sf1.format(new Date(starttmp*1000)));
//					System.out.println(endtmp);
//					System.out.println(sf1.format(new Date(endtmp*1000)));
					
					//List<AttendenceBo> aBo = attendenceDao.findByAttendanceDateAndMobile(dateStr, uinfo.getMobile());
					Date amCheckTime = null;
					Date pmCheckTime = null;
					if(null != aBos && aBos.size() > 0){
						if(aBos.size()==1){
							if("am".equals( aBos.get(0).getTag())){
								amCheckTime = new Date(aBos.get(0).getAttendence_time() * 1000);
								attendenceDetail.put(dateStr, sf2.format(amCheckTime)+" - 无");
							}else{
								pmCheckTime = new Date(aBos.get(0).getAttendence_time() * 1000);
								attendenceDetail.put(dateStr, "无  - " + sf2.format(pmCheckTime));
							}
						}else{
							amCheckTime = new Date(aBos.get(0).getAttendence_time() * 1000);
							pmCheckTime = new Date(aBos.get(aBos.size()-1).getAttendence_time() * 1000);
							attendenceDetail.put(dateStr, sf2.format(new Date(aBos.get(0).getAttendence_time() * 1000)) 
									+" - "+sf2.format(new Date(aBos.get(aBos.size()-1).getAttendence_time() * 1000)));
						}
					}else{
						//法定假日判断、出差、请假判断
						//.....
						//attendenceDetail.put(dateStr, "缺勤");
						attendenceDetail.put(dateStr, "-");
					}
					
//					int unCheckAmount = 0; //未打卡总数
//					int lateAmount = 0; //迟到数量
//					int earlyLeaveAmount = 0; //早退数量
					
					////累加迟到、早退、未打卡次数(要跟每个人的上下班时间做对比，每个人配置不一样。默认8:30-17:30)
//					//先判断是否节假日
//					Calendar calendarT = Calendar.getInstance();//日历对象
//					calendarT.setTime(amCheckTime);
					int weekday = startCalendarTMP.get(Calendar.DAY_OF_WEEK) - 1;
					if( weekday != 6 && weekday != 0){
						
						//累加迟到、早退、未打卡次数(要跟每个人的上下班时间做对比，每个人配置不一样。默认8:30-17:30)
						if(null == amCheckTime){
							unCheckAmount ++;
						}else{
							//组装当日要求打卡时间比较
							String userAmCheckTimeStr = sf.format(amCheckTime) + " " + amTime;
							Date userAmCheckTime = sf3.parse(userAmCheckTimeStr);
							//实际打卡时间与要求打卡时间比较
							//if(amCheckTime.after(userAmCheckTime)){  // 从数据看取值
							if((amCheckTime.getTime()-userAmCheckTime.getTime())/1000 >= 60){ //8点31前打卡的都不算
								lateAmount ++;
							}
						}
						
						//累加迟到、早退、未打卡次数
						if(null == pmCheckTime){
							unCheckAmount ++;
						}else{
							//组装当日要求打卡时间比较
							String userPmCheckTimeStr = dateWithYearStr + " " + pmTime;
							Date userPmCheckTime = sf3.parse(userPmCheckTimeStr);
							//实际打卡时间与要求打卡时间比较
							if(pmCheckTime.before(userPmCheckTime)){  // 从数据看取值
								earlyLeaveAmount ++;
							}
						}
					}
					
					//截取手机信息
					for(AttendenceBo aboT : aBos){
						String deviceInfo = aboT.getDevice_info();
						if(!StringUtils.isEmpty(deviceInfo)){
							String udid = "";
							String[] udidStrs =  deviceInfo.split("udid");
							if(udidStrs.length > 1){
								udid = StringUtils.trimWhitespace(udidStrs[1].split(",")[0]);
							}
							String phonemodel = "";
							String[] phonemodels =  deviceInfo.split("phonemodel");
							if(phonemodels.length > 1){
								phonemodel = StringUtils.trimWhitespace(phonemodels[1].split(",")[0]);
							}
							
							deviceInfo = "udid"+udid+",phonemodel"+ phonemodel;
							if(!devices.contains(deviceInfo)){
								devices.add(deviceInfo);
							}
						}
					}
				}
				aRBo.setDeviceList(devices);
				aRBo.setDeviceTotal(devices.size());
				aRBo.setAttendenceDetail(attendenceDetail);
				aRBo.setEarlyLeaveAmount(earlyLeaveAmount);
				aRBo.setLateAmount(lateAmount);
				aRBo.setUnCheckAmount(unCheckAmount);
				
				//更新每月报表
				attendenceReportDao.saveOrUpdate(aRBo);
				
			}
			catch(Exception e){
				//e.printStackTrace();
				logger.error("refreshData() error. ",e);
			}
		}
		
	}  
}
