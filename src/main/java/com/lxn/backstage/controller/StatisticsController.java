package com.lxn.backstage.controller;

import com.lxn.backstage.common.BaseResponse;
import com.lxn.backstage.common.ErrorCode;
import com.lxn.backstage.common.ResultUtils;
import com.lxn.backstage.exception.BusinessException;
import com.lxn.backstage.model.dto.RoomRequest;
import com.lxn.backstage.model.entity.Member;
import com.lxn.backstage.model.entity.User;
import com.lxn.backstage.service.MemberService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Resource
    private MemberService memberService;
    /**
     * 会员统计
     *
     * @param roomRequest
     * @return
     */
    @PostMapping("/detail")
    public BaseResponse<HashMap> memberStatistics(@RequestBody RoomRequest roomRequest) {
        HashMap<String, Long> dataMap = new HashMap<>();
        System.out.println(roomRequest);
        Integer roomId = roomRequest.getRoomId();
        // 统计当前自习室的会员数
        Long count = memberService.statisticsMemberByRoomId(roomId);
        if (count == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "自习室不存在");
        }
        dataMap.put("statisticsMemberByRoomId",count);
        // 统计所有自习室的会员数
        String userAccount = roomRequest.getUserAccount();
        count = memberService.statisticsMemeberByUserAccount(userAccount);
        if (count == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在");
        }
        dataMap.put("statisticsMemberByUserAccount",count);
        return ResultUtils.success(dataMap, "");
    }

    /** 根据登录账号统计当前账号开设自习室列表
     * @param user
     * @return {count:Int}
     */
//    @PostMapping("/room")
//    public BaseResponse <HashMap> StatisticsRoom(@RequestBody User user){
//
//    }
}
