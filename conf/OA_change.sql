


--资源详情表
--drop table admin_org_worktime;
CREATE TABLE admin_org_worktime    (
	org_id varchar(255) NOT NULL COMMENT '资源名称', 
    am_hour int(2) NOT NULL  COMMENT '上班小时',
    am_minute int(2) NOT NULL  COMMENT '上班分钟',
    pm_hour int(2) NOT NULL  COMMENT '下班小时',
    pm_minute int(2) NOT NULL  COMMENT '下班分钟',
	STATUS CHAR(1) DEFAULT 'Y' NOT NULL  COMMENT '可用状态', 

	FOREIGN KEY (org_id) REFERENCES admin_organization(ID)
)ENGINE=InnoDB AUTO_INCREMENT=1000 DEFAULT CHARSET=utf8;




select u.username, u.telephone,o.name dep,o1.name org, o1.contactperson, o1.contactnumber
from admin_user u 
left join admin_organization o on o.id=u.organization_id 
left join admin_organization o1 on o1.id=o.parent
where o1.name is not null