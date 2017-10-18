package com.ldars.oa.model;

import java.util.Date;

public class OAUser {
    private String id;

    private Boolean locked;

    private String organizationId;

    private String password;

    private String salt;

    private String username;

    private Date createtime;

    private String createuser;

    private String icon;

    private Date modifytime;

    private String modifyuser;

    private String userno;

    private Integer status;

    private String superior;

    private String telephone;

    private Integer fromtype;

    private Integer breakfast;

    private Integer dinner;

    private String realname;

    private Integer isregist;
    
    private OAOrganization company;
    private OAOrganization department;
    private OAOrgWorktime orgWorktime;

    public OAOrganization getCompany() {
		return company;
	}

	public void setCompany(OAOrganization company) {
		this.company = company;
	}

	public OAOrganization getDepartment() {
		return department;
	}

	public void setDepartment(OAOrganization department) {
		this.department = department;
	}

	public OAOrgWorktime getOrgWorktime() {
		return orgWorktime;
	}

	public void setOrgWorktime(OAOrgWorktime orgWorktime) {
		this.orgWorktime = orgWorktime;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId == null ? null : organizationId.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public String getModifyuser() {
        return modifyuser;
    }

    public void setModifyuser(String modifyuser) {
        this.modifyuser = modifyuser == null ? null : modifyuser.trim();
    }

    public String getUserno() {
        return userno;
    }

    public void setUserno(String userno) {
        this.userno = userno == null ? null : userno.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSuperior() {
        return superior;
    }

    public void setSuperior(String superior) {
        this.superior = superior == null ? null : superior.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Integer getFromtype() {
        return fromtype;
    }

    public void setFromtype(Integer fromtype) {
        this.fromtype = fromtype;
    }

    public Integer getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Integer breakfast) {
        this.breakfast = breakfast;
    }

    public Integer getDinner() {
        return dinner;
    }

    public void setDinner(Integer dinner) {
        this.dinner = dinner;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Integer getIsregist() {
        return isregist;
    }

    public void setIsregist(Integer isregist) {
        this.isregist = isregist;
    }
}