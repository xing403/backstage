package com.lxn.backstage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxn.backstage.common.BaseResponse;
import com.lxn.backstage.common.ErrorCode;
import com.lxn.backstage.common.ResultUtils;
import com.lxn.backstage.exception.BusinessException;
import com.lxn.backstage.model.entity.Room;
import com.lxn.backstage.model.entity.Seat;
import com.lxn.backstage.model.entity.User;
import com.lxn.backstage.service.RoomService;
import com.lxn.backstage.service.SeatService;
import com.lxn.backstage.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.BuilderException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/seat")
public class SeatController {
    @Resource
    private UserService userService;
    @Resource
    private RoomService roomService;
    @Resource
    private SeatService seatService;
    // 查询座位列表信息
    @PostMapping("/list")
    public BaseResponse<List<Seat>> selectListByRoomId(@RequestBody Seat seat){
        // 获取自习室编号
        Long roomId = seat.getRoomId();
        if(roomId <=0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"参数错误");
        }
        List<Seat> seats = seatService.selectSeatByRoomId(roomId);
        return ResultUtils.success(seats,"");
    }

    @PostMapping("/insertSeat")
    public BaseResponse<Boolean> insetSeat(@RequestBody Seat seat){
        if(seat == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        Boolean aBoolean = seatService.insertSeat(seat.getUserAccount(),seat.getRoomId(),seat.getSeatId(),seat.getSeatStatus(),seat.getTime());
        return ResultUtils.success(aBoolean, aBoolean ? "成功" : "失败");
    }
}
