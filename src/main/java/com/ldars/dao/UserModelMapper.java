package com.ldars.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ldars.model.UserModel;

public interface UserModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserModel record);

    int insertSelective(UserModel record);

    UserModel selectByPrimaryKey(Long id);
    
    UserModel selectByUnamePword(@Param(value="userName")String userName,@Param(value="password")String password); 
    int selectByUname(@Param(value="name")String name);
    int updateByPrimaryKeySelective(UserModel record);

    int updateByPrimaryKey(UserModel record);
    List<UserModel> selectUserWithCondition(@Param("name")String name,@Param("startRow") Integer startRow,
			@Param("pageSize") Integer pageSize);
	int selectCountWithCondition(@Param("name")String name);
	int UpdateRoleEmptyByRoleId(Long roleId);
}