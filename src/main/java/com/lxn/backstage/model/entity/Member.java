package com.lxn.backstage.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName(value = "member")
public class Member implements Serializable {
    /**
     * 会员编号
     */
    @TableId(type = IdType.AUTO)
    private Long memberId;
    /**
     * 用户账号
     */
    private String userAccount;
    /**
     * 自习室编号
     */
    private Integer roomId;

    /**
     * 会员开始时间
     */
    private Date beginTime;
    /**
     * 会员结束时间
     */
    private Date endTime;
    /**
     * 是否被删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
