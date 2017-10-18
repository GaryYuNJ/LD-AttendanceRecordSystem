package com.ldars.oa.dao;

import com.ldars.oa.model.OAUser;

public interface OAUserMapper {
    int deleteByPrimaryKey(String id);

    int insert(OAUser record);

    int insertSelective(OAUser record);

    OAUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OAUser record);

    int updateByPrimaryKey(OAUser record);
    
    OAUser selectWithOrgInfoByMobile(String mobile);
}