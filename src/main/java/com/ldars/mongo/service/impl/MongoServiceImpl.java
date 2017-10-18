package com.ldars.mongo.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ldars.dao.UserModelMapper;
import com.ldars.model.UserModel;
import com.ldars.mongo.bo.AttendenceBo;
import com.ldars.mongo.bo.AttendenceReportBo;
import com.ldars.mongo.dao.AttendenceDao;
import com.ldars.mongo.dao.AttendenceReportDao;
import com.ldars.mongo.service.IMongoService;
import com.ldars.oa.dao.OAUserMapper;
import com.ldars.oa.model.OAUser;

@Service("mongoService")
public class MongoServiceImpl implements IMongoService {

	@Autowired
	private AttendenceDao attendenceDao;
	
	@Autowired
	private AttendenceReportDao attendenceReportDao;
	
	@Autowired 
	OAUserMapper oAUserMapper;
	
	@Autowired  
	private HttpSession session;

	@Override
	public void refreshAttendenceReportByMonth(String month) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat sf = new  SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sf1 = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sf2 = new  SimpleDateFormat("HH:mm:ss");
		
		Calendar startCalendar = Calendar.getInstance();//日历对象  
		startCalendar.setTime(sf.parse(month+"-25"));//设置日期  
		startCalendar.add(Calendar.MONTH, -1);//月份减一 
        Date startDateTime = startCalendar.getTime();
        
        Calendar endCalendar = Calendar.getInstance();//日历对象 
        endCalendar.setTime(sf.parse(month+"-24"));//设置日期
        if(endCalendar.getTime().after(new Date())){
        	endCalendar.setTime(new Date());
        }
        Date endDateTime = endCalendar.getTime();
        
//		String startDateTimeStr = month + " 00:00:01";
//		String endDateTimeStr = month + " 23:59:59";
//		SimpleDateFormat sf1 = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date startDateTime = sf1.parse(startDateTimeStr);
//		Date endDateTime = sf1.parse(endDateTimeStr);
		
		//List<AttendenceBo> aBoList = attendenceDao.findByStartEndDate(startDateTime.getTime(), endDateTime.getTime());
        //test
        List<AttendenceBo> aBoList = attendenceDao.findByStartEndDateAndMobile(startDateTime.getTime()/1000, endDateTime.getTime()/1000,"13951992063");
		
		List<String> mobileList = new ArrayList<String>(); 
		
		for(AttendenceBo abo : aBoList){
			try{
				//每个手机号只循环一次
				if(mobileList.contains(abo.getMobile())){
					continue;
				}
				mobileList.add(abo.getMobile());
				
				//查看当月用户是否已有数据
				AttendenceReportBo aRBo = attendenceReportDao.findByMonthAndMobile(month, abo.getMobile());
				if(null == aRBo || StringUtils.isEmpty(aRBo.getId())){
					aRBo = new AttendenceReportBo();
					aRBo.setMonth(month);
					aRBo.setMobile(abo.getMobile());
				}
				//查询OA数据库获取数据
				OAUser oaUser = oAUserMapper.selectWithOrgInfoByMobile(abo.getMobile());
				String realName = null != oaUser?oaUser.getRealname():abo.getName();
				String company = null != oaUser && null!=oaUser.getCompany()?oaUser.getCompany().getName():"";
				String department = null != oaUser && null!=oaUser.getDepartment()?oaUser.getDepartment().getName():"";
				aRBo.setRealName(realName);
				aRBo.setCompany(company);
				aRBo.setDepartment(department);
				
				String amTime = "8:30";
				String pmTime = "17:30";
				if(null != oaUser && null != oaUser.getOrgWorktime()
						&& !StringUtils.isEmpty(oaUser.getOrgWorktime().getAmHour())
						&& !StringUtils.isEmpty(oaUser.getOrgWorktime().getPmHour())
						&& !StringUtils.isEmpty(oaUser.getOrgWorktime().getAmMinute())
						&& !StringUtils.isEmpty(oaUser.getOrgWorktime().getPmMinute())){
					amTime = oaUser.getOrgWorktime().getAmHour()+":"+oaUser.getOrgWorktime().getAmMinute();
					pmTime = oaUser.getOrgWorktime().getPmHour()+":"+oaUser.getOrgWorktime().getPmMinute();
				}
				
				aRBo.setWorkTime(amTime+" - "+pmTime);
				aRBo.setUpdateTime(new Date().getTime());
				
				Map<String,String> attendenceDetail = new LinkedHashMap<String,String> ();
				attendenceDetail.put("month", month);
				attendenceDetail.put("mobile", abo.getMobile());
				attendenceDetail.put("realName", realName);
				attendenceDetail.put("company", company);
				attendenceDetail.put("department", department);
				attendenceDetail.put("workTime", amTime+" - "+pmTime);
				
				//按这个月天循环获取考勤时间
				for(;startCalendar.getTime().getTime() < endCalendar.getTime().getTime(); 
						startCalendar.add(Calendar.DATE, +1)){
					
					String dateStr = sf.format(startCalendar.getTime());
//					//am 取当天第一条数据
//					AttendenceBo aBo = attendenceDao.findByAttendanceDateAndMobile(dateStr, abo.getMobile(),"am",0);
//					//pm 取当天第最后一条数据
//					aBo = attendenceDao.findByAttendanceDateAndMobile(dateStr, abo.getMobile(),"pm",1);
					
					//获取当天的打卡数据
					List<AttendenceBo> aBo = attendenceDao.findByStartEndDateAndMobile(startCalendar.getTime().getTime()/1000, startCalendar.getTime().getTime()/1000 + 24 * 60 * 60 , abo.getMobile());
					
					//List<AttendenceBo> aBo = attendenceDao.findByAttendanceDateAndMobile(dateStr, abo.getMobile());
					if(null != aBo && aBo.size() > 0){
						
						if(aBo.size()==1){
							
							if("am".equals( aBo.get(0).getTag())){
								attendenceDetail.put(dateStr, sf2.format(new Date(aBo.get(0).getAttendence_time() * 1000))+" - 未打卡");
							}else{
								attendenceDetail.put(dateStr, "未打卡  - " + sf2.format(new Date(aBo.get(0).getAttendence_time() * 1000)));
							}
						}else{
							attendenceDetail.put(dateStr, sf2.format(new Date(aBo.get(0).getAttendence_time() * 1000)) 
									+" - "+sf2.format(new Date(aBo.get(aBo.size()-1).getAttendence_time() * 1000)));
						}
					}else{
						//法定假日判断
						
						attendenceDetail.put(dateStr, "缺勤");
					}
				}
				
				aRBo.setAttendenceDetail(attendenceDetail);
				
				attendenceReportDao.saveOrUpdate(aRBo);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		
		
	}  

	
}
