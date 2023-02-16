-- 创建库
CREATE DATABASE IF NOT EXISTS learn_room;

-- 切换库
USE learn_room;

-- 用户表
CREATE TABLE IF NOT EXISTS USER (
    id           BIGINT AUTO_INCREMENT COMMENT 'id' PRIMARY KEY,
    userName     VARCHAR(256)                           NULL COMMENT '用户昵称',
    userAccount  VARCHAR(256)                           NOT NULL COMMENT '账号',
    userAvatar   VARCHAR(1024)                          NULL COMMENT '用户头像',
    gender       VARCHAR(15)                            NULL COMMENT '性别',
    userPhone    VARCHAR(15)                            NULL COMMENT '手机号',
    userRole     VARCHAR(256) DEFAULT 'user'            NOT NULL COMMENT '用户角色：user/ admin/ merchant',
    userPassword VARCHAR(512)                           NOT NULL COMMENT '密码',
    createTime   DATETIME     DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    updateTime   DATETIME     DEFAULT CURRENT_TIMESTAMP NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    isDelete     TINYINT      DEFAULT 0                 NOT NULL COMMENT '是否删除',
    CONSTRAINT uni_userAccount UNIQUE (userAccount)
) COMMENT '用户';

-- 创建自习室表
CREATE TABLE `room`
(
    `roomId`      BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT '自习室编号',
    `roomName`    VARCHAR(255)      DEFAULT NULL COMMENT '自习室名称',
    `roomSeat`    INT(11)           DEFAULT NULL COMMENT '自习室总座位数',
    `roomAddress` VARCHAR(255)      DEFAULT NULL COMMENT '自习室地址',
    `roomPhone`   VARCHAR(15)       DEFAULT NULL COMMENT '自习室电话',
    `roomDesc`    TEXT COMMENT '自习室介绍',
    `createTime`  TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updateTime`  TIMESTAMP    NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `isDelete`    TINYINT(4)        DEFAULT '0' COMMENT '是否被删除',
    `userAccount` VARCHAR(255) NOT NULL COMMENT '创建者账号',
    PRIMARY KEY (`roomId`)
)COMMENT ='自习室';

-- 创建会员表
CREATE TABLE member
(
    memberId    BIGINT AUTO_INCREMENT COMMENT '会员编号',
    userAccount VARCHAR(256)                        NULL COMMENT '用户编号',
    roomId      BIGINT                              NULL COMMENT '自习室编号',
    beginTime   TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL COMMENT '会员开始时间',
    endTime     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NULL COMMENT '会员结束时间',
    isDelete    TINYINT   DEFAULT 0                 NULL COMMENT '是否被删除',
    PRIMARY KEY (`memberId`)
)
    COMMENT '会员表';

