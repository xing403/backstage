package com.lxn.backstage.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@TableName(value = "room")
@Data
public class Room {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Long roomId;
    /**
     * 名称
     */
    private String roomName;
    /**
     * 联系电话
     */
    private String roomPhone;
    /**
     * 地址
     */
    private String roomAddress;
    /**
     * 介绍
     */
    private String roomDesc;
    /**
     * 座位量
     */
    private Integer roomSeat;
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;
    private String userAccount;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}
