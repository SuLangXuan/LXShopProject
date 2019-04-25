//用户信息管理类

//lxshopproject

第一步，首先完成用户信息的功能（登录，注册）

1.创建数据库 与 用户表
-- 创建用户表
CREATE TABLE tb_user(
	user_id INT PRIMARY KEY AUTO_INCREMENT,
	username VARCHAR(32) NOT NULL,
	mobliephonenumber VARCHAR(32) NOT NULL UNIQUE,
	userpassword VARCHAR(32)NOT NULL
);


1.用户注册