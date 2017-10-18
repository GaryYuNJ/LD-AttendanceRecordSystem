package com.ldars.service;

import java.util.List;
import com.ldars.model.UserModel;

public interface IUserService {
	public UserModel getUserById(long userId); 
	UserModel getUserByUP(String userName,String password);
	int changePassword(UserModel currentUserModel, String newPassword);
	UserModel getSessionUserModel();
	Long  getSessionUserId();
	List<UserModel> queryUserWithPageIndex(String name, Integer pageNo, Integer pageSize);
	int queryCountByCondition(String name);
	int saveOrUpdate(UserModel currentUserModel);
	int getUserByName(String name);
	int delBuser(Long id) ;
	int delBUByRole(Long roleId);
}
