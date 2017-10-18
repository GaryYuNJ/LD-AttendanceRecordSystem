package com.ldars.oa.dao;

import com.ldars.oa.model.OAOrganization;

public interface OAOrganizationMapper {
    int deleteByPrimaryKey(String id);

    int insert(OAOrganization record);

    int insertSelective(OAOrganization record);

    OAOrganization selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OAOrganization record);

    int updateByPrimaryKey(OAOrganization record);
}