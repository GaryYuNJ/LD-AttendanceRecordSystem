package com.ldars.oa.dao;

import com.ldars.oa.model.OAOrgWorktime;

public interface OAOrgWorktimeMapper {
    int insert(OAOrgWorktime record);

    int insertSelective(OAOrgWorktime record);
}