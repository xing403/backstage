package com.lxn.backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxn.backstage.common.ErrorCode;
import com.lxn.backstage.exception.BusinessException;
import com.lxn.backstage.mapper.RoomMapper;
import com.lxn.backstage.mapper.SeatMapper;
import com.lxn.backstage.mapper.UserMapper;
import com.lxn.backstage.model.entity.Room;
import com.lxn.backstage.model.entity.Seat;
import com.lxn.backstage.model.entity.User;
import com.lxn.backstage.service.RoomService;
import com.lxn.backstage.service.SeatService;
import com.lxn.backstage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service
@Slf4j
public class SeatServiceImpl  extends ServiceImpl<SeatMapper, Seat> implements SeatService {
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private SeatMapper seatMapper;
    @Override
    public List<Seat> selectSeatByRoomId(Long roomId) {
        QueryWrapper<Seat> seatQueryWrapper = new QueryWrapper<>();
        seatQueryWrapper.eq("roomId", roomId);
        List<Seat> seats = seatMapper.selectList(seatQueryWrapper);


        return seats;
    }

    @Override
    public Boolean updateSeatStatusByRoomIdAndSeatId(Long roomId, Integer seatId, Integer seatStatus) {
        return null;
    }

    // 添加座位状态
    @Override
    public Boolean insertSeat(String userAccount, Long roomId, Integer seatId, Integer seatStatus, Date time) {

        // 检查参数
        if(StringUtils.isAllBlank(userAccount)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        // 查看用户是否存在
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userAccount", userAccount);
        if(userMapper.selectOne(userQueryWrapper) == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "用户不存在");
        }
        // 检查座位和自习室是否存在
        QueryWrapper<Room> roomQueryWrapper = new QueryWrapper<>();
        roomQueryWrapper.eq("roomId", roomId);
        Room room = roomMapper.selectOne(roomQueryWrapper);
        if(room == null ||  room.getRoomSeat() < seatId){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "请检查自习室或座位是否存在");
        }

        QueryWrapper<Seat> seatQueryWrapper = new QueryWrapper<>();
        seatQueryWrapper.eq("roomId", roomId)
                .eq("seatId", seatId);
        Seat seat1 =seatMapper.selectOne(seatQueryWrapper);
        if(seat1 != null){
            String msg = seat1.getSeatStatus() == 1 ? "座位已被预约" : "座位已有人";
            throw new BusinessException(ErrorCode.OPERATION_ERROR, msg);
        }
        Seat seat2 = new Seat();
        seat2.setRoomId(roomId);
        seat2.setSeatStatus(seatStatus);
        seat2.setSeatId(seatId);
        seat2.setUserAccount(userAccount);
        seat2.setTime(time);
        return this.save(seat2);

    }



}
