# DROP TABLE IF EXISTS user;
CREATE TABLE `user`
(
    `id`        int         NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    `username`  varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
    `nickname`  varchar(20) NOT NULL DEFAULT '' COMMENT '昵称',
    `sex`       varchar(1)  NOT NULL DEFAULT '' COMMENT '性别',
    `age`       int         NOT NULL DEFAULT 0 COMMENT '年龄',
    create_time datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    modify_time datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间'
) DEFAULT CHARSET = utf8 COMMENT ='用户表';

CREATE TABLE `order`
(
    `id`        int         NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    `username`  varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
    `nickname`  varchar(20) NOT NULL DEFAULT '' COMMENT '昵称',
    `sex`       varchar(1)  NOT NULL DEFAULT '' COMMENT '性别',
    `age`       int         NOT NULL DEFAULT 0 COMMENT '年龄',
    create_time datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    modify_time datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间'
) DEFAULT CHARSET = utf8 COMMENT ='订单表';

CREATE TABLE `goods`
(
    `id`        int         NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    `username`  varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
    `nickname`  varchar(20) NOT NULL DEFAULT '' COMMENT '昵称',
    `sex`       varchar(1)  NOT NULL DEFAULT '' COMMENT '性别',
    `age`       int         NOT NULL DEFAULT 0 COMMENT '年龄',
    create_time datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    modify_time datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间'
) DEFAULT CHARSET = utf8 COMMENT ='商品表';
