package com.ldars.oa.dao;

import com.ldars.oa.model.UserOtherInfo;

public interface UserOtherInfoMapper {
    int insert(UserOtherInfo record);

    int insertSelective(UserOtherInfo record);
    
    UserOtherInfo selectByMobile(String mobile);
}