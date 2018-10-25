CREATE TABLE user_udid_monthly_report (
    month         	        varchar(10) NOT NULL,
    user_name           	varchar(40) NOT NULL,
    user_mobile         	varchar(20) NOT NULL,
    company         	    varchar(100) NULL,
    department               	varchar(200) NULL,
    udid                        varchar(300) NULL,
    count             	     int(3) not NULL DEFAULT 1,
    dates           	        varchar(400) NULL,
    create_time             	datetime  NULL,
    uidi_host_name           	varchar(40) NOT NULL,
    uidi_host_mobile         	varchar(20) NOT NULL,
    uidi_host_company         varchar(100) NULL,
    uidi_host_department      varchar(200) NULL
) ENGINE=InnoDB  DEFAULT CHARACTER SET=utf8;
