package com.ldars.model;

import java.util.Date;
import java.util.List;

public class UserModel {
    private Long id;

    private String name;

    private String password;

    private String status;
    
    private Long roleId;

    private Date createDate;

    private Long createUser;
    
//    //权限判断
//    private Role userRole;
//
//    //权限判断    
//    private List<BuildingModel> buildings;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }
//
//	public Role getUserRole() {
//		return userRole;
//	}
//
//	public void setUserRole(Role userRole) {
//		this.userRole = userRole;
//	}
//
//	public List<BuildingModel> getBuildings() {
//		return buildings;
//	}
//
//	public void setBuildings(List<BuildingModel> buildings) {
//		this.buildings = buildings;
//	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
}