package com.lxn.backstage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxn.backstage.common.BaseResponse;
import com.lxn.backstage.common.ErrorCode;
import com.lxn.backstage.common.ResultUtils;
import com.lxn.backstage.exception.BusinessException;
import com.lxn.backstage.model.dto.RoomRequest;
import com.lxn.backstage.model.entity.Member;
import com.lxn.backstage.model.entity.User;
import com.lxn.backstage.service.MemberService;
import com.lxn.backstage.service.StatisticsService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Resource
    private StatisticsService statisticsService;

    /** 商家统计页面内
     * @param user
     * @return
     */
    @PostMapping("/merchant")
    public BaseResponse<HashMap> merchantSratistics(@RequestBody User user){
        String userAccount = user.getUserAccount();
        if (StringUtils.isAllBlank(userAccount)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数错误");
        }
        // 统计每个自习室拥有的会员数
        List list = statisticsService.roomsHasMember(userAccount);
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("room_numbers",list);



        return ResultUtils.success(stringObjectHashMap,"OK");

    }
}
