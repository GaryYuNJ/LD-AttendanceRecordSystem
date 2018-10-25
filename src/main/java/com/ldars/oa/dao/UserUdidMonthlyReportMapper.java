package com.ldars.oa.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ldars.oa.model.UserUdidMonthlyReport;

public interface UserUdidMonthlyReportMapper {
    int insert(UserUdidMonthlyReport record);

    int insertSelective(UserUdidMonthlyReport record);
    
    List<String> selectUdidByMonth(@Param("month")String month);
    
    List<UserUdidMonthlyReport> selectByMonthAndudid(@Param("month")String month, @Param("udid")String udid);
    
    int updateByUdidAndMonthSelective(UserUdidMonthlyReport record);
}