package com.ldars.oa.dao;

import java.util.List;

import com.ldars.oa.model.UserOtherInfo;

public interface UserOtherInfoMapper {
    int insert(UserOtherInfo record);

    int insertSelective(UserOtherInfo record);
    
    UserOtherInfo selectByMobile(String mobile);
    
    List<UserOtherInfo> selectNotCheckedUser(String mobiles);
    
    List<UserOtherInfo> selectAll();
}