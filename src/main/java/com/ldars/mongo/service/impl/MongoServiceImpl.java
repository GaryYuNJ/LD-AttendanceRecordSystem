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
import com.ldars.mongo.bo.AttendenceReportBo;
import com.ldars.mongo.dao.AttendenceDao;
import com.ldars.mongo.dao.AttendenceReportDao;
import com.ldars.mongo.service.IMongoService;
import com.ldars.oa.dao.OAUserMapper;
import com.ldars.oa.dao.UserOtherInfoMapper;
import com.ldars.oa.model.UserOtherInfo;

@Service("mongoService")
public class MongoServiceImpl implements IMongoService {

	final static Logger logger = Logger.getLogger(MongoServiceImpl.class);
	
	@Autowired
	private AttendenceDao attendenceDao;
	
	@Autowired
	private AttendenceReportDao attendenceReportDao;
	
	@Autowired 
	OAUserMapper oAUserMapper;
	
	@Autowired 
	UserOtherInfoMapper userOtherInfoMapper;
	
	@Autowired  
	private HttpSession session;
	
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

	@Override
	public void refreshAttendenceReportByMonth(String month) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sf = new  SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf2 = new  SimpleDateFormat("HH:mm");
		
		Calendar startCalendar = Calendar.getInstance();//日历对象 
		Calendar startCalendarTMP = Calendar.getInstance();//用于for循环获取当月的日期列表
		startCalendar.setTime(sf.parse(month+"-24"));//设置起始日期  
		startCalendar.add(Calendar.MONTH, -1);//月份减一 
        Date startDateTime = startCalendar.getTime();
        
        Calendar endCalendar = Calendar.getInstance();//日历对象 
        endCalendar.setTime(sf.parse(month+"-24"));//设置结束日期
        if(endCalendar.getTime().after(new Date())){
        	endCalendar.setTime(new Date());
        }
        Date endDateTime = endCalendar.getTime();
        
//		String startDateTimeStr = month + " 00:00:01";
//		String endDateTimeStr = month + " 23:59:59";
//		SimpleDateFormat sf1 = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date startDateTime = sf1.parse(startDateTimeStr);
//		Date endDateTime = sf1.parse(endDateTimeStr);
		
		List<AttendenceBo> aBoList = attendenceDao.findByStartEndDate(startDateTime.getTime()/1000, endDateTime.getTime()/1000);
        //test
        //List<AttendenceBo> aBoList = attendenceDao.findByStartEndDateAndMobile(startDateTime.getTime()/1000, endDateTime.getTime()/1000,"13951992063");
		
		List<String> mobileList = new ArrayList<String>(); 
		
		for(AttendenceBo abo : aBoList){
			try{
				//每个手机号只循环一次
				if(mobileList.contains(abo.getMobile())){
					continue;
				}
				mobileList.add(abo.getMobile());
				
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
				String amTime = null != uinfo && null!=uinfo.getAmTime()?uinfo.getAmTime():"8:30";
				String pmTime = null != uinfo && null!=uinfo.getPmTime()?uinfo.getPmTime():"17:30";
				
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
					
					String dateStr = sf.format(startCalendarTMP.getTime());
//					//am 取当天第一条数据
//					AttendenceBo aBoAM = attendenceDao.findByAttendanceDateMobileAndTag(dateStr, abo.getMobile(),"am",0);
//					String aBoAMStr = null != aBoAM && null != aBoAM.getAttendence_time()?sf2.format(new Date(aBoAM.getAttendence_time()*1000)):"";
////					//pm 取当天第最后一条数据
//					AttendenceBo aBoPM = attendenceDao.findByAttendanceDateMobileAndTag(dateStr, abo.getMobile(),"pm",1);
//					String aBoPMStr = null != aBoPM && null != aBoPM.getAttendence_time()?sf2.format(new Date(aBoPM.getAttendence_time()*1000)):"";
//					attendenceDetail.put(dateStr, aBoAMStr+" - "+aBoPMStr);
//					
//					if(null != aBoAM && null != aBoAM.getDevice_info()){
//						String deviceInfo = aBoAM.getDevice_info();
//						if(!StringUtils.isEmpty(deviceInfo)){
//							String[] infos =  deviceInfo.split("udid");
//							if(infos.length > 1){
//								if(!devices.contains(infos[1].split("network")[0])){
//									devices.add(infos[1].split("network")[0]);
//								}
//							}
//						}
//					} 
//					if(null != aBoPM && null != aBoPM.getDevice_info()){
//						String deviceInfo = aBoPM.getDevice_info();
//						if(!StringUtils.isEmpty(deviceInfo)){
//							String[] infos =  deviceInfo.split("udid");
//							if(infos.length > 1){
//								if(!devices.contains(infos[1].split("network")[0])){
//									devices.add(infos[1].split("network")[0]);
//								}
//							}
//						}
//					}
					
					//获取当天的打卡数据（早上6点到第二天6点算当这一天的有效时间，同APP）
					List<AttendenceBo> aBos = attendenceDao.findByStartEndDateAndMobile(startCalendarTMP.getTime().getTime()/1000 + 6 * 60 * 60, 
							startCalendarTMP.getTime().getTime()/1000 + 30 * 60 * 60 , abo.getMobile());
					
//					Long starttmp = startCalendarTMP.getTime().getTime()/1000 + 6 * 60 * 60;
//					Long endtmp = startCalendarTMP.getTime().getTime()/1000 + 30 * 60 * 60;
//					System.out.println(starttmp);
//					System.out.println(sf1.format(new Date(starttmp*1000)));
//					System.out.println(endtmp);
//					System.out.println(sf1.format(new Date(endtmp*1000)));
					
					//List<AttendenceBo> aBo = attendenceDao.findByAttendanceDateAndMobile(dateStr, abo.getMobile());
					if(null != aBos && aBos.size() > 0){
						
						if(aBos.size()==1){
							
							if("am".equals( aBos.get(0).getTag())){
								attendenceDetail.put(dateStr, sf2.format(new Date(aBos.get(0).getAttendence_time() * 1000))+" - 未打卡");
							}else{
								attendenceDetail.put(dateStr, "未打卡  - " + sf2.format(new Date(aBos.get(0).getAttendence_time() * 1000)));
							}
						}else{
							attendenceDetail.put(dateStr, sf2.format(new Date(aBos.get(0).getAttendence_time() * 1000)) 
									+" - "+sf2.format(new Date(aBos.get(aBos.size()-1).getAttendence_time() * 1000)));
						}
					}else{
						//法定假日判断、出差、请假判断
						//.....
						//attendenceDetail.put(dateStr, "缺勤");
						attendenceDetail.put(dateStr, "");
					}
					
					for(AttendenceBo aboT : aBos){
						String deviceInfo = aboT.getDevice_info();
						if(!StringUtils.isEmpty(deviceInfo)){
							String[] infos =  deviceInfo.split("udid");
							if(infos.length > 1){
								if(!devices.contains(infos[1].split("network")[0])){
									devices.add(infos[1].split("network")[0]);
								}
							}
						}
					}
				}
				aRBo.setDeviceList(devices);
				aRBo.setDeviceTotal(devices.size());
				aRBo.setAttendenceDetail(attendenceDetail);
				
				attendenceReportDao.saveOrUpdate(aRBo);
			}
			catch(Exception e){
				//e.printStackTrace();
				logger.error("refreshData() error. ",e);
			}
		}
		
		
		
		
	}  
	
	  /**  
     * 获取系统当前默认时区与指定时区的时间差.(单位:毫秒)  
     *   
     * @param timeZoneId  
     *            时区Id  
     * @return 系统当前默认时区与指定时区的时间差.(单位:毫秒)  
     */  
    private static int getDiffTimeZoneRawOffset(String timeZoneId) {   
        return TimeZone.getDefault().getRawOffset()   
                - TimeZone.getTimeZone(timeZoneId).getRawOffset();   
    }   
	
}
