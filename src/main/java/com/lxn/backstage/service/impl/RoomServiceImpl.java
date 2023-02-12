package com.lxn.backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.lxn.backstage.common.ErrorCode;
import com.lxn.backstage.exception.BusinessException;
import com.lxn.backstage.mapper.MemberMapper;
import com.lxn.backstage.mapper.RoomMapper;
import com.lxn.backstage.model.entity.Member;
import com.lxn.backstage.model.entity.Room;
import com.lxn.backstage.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class RoomServiceImpl extends ServiceImpl<RoomMapper, Room> implements RoomService {
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private MemberMapper memberMapper;
    @Override
    public Long roomRegister(String roomName, String roomPhone, Integer roomSeat, String roomAddress, String roomDesc, String userAccount) {
        Room room = new Room();
        room.setRoomName(roomName);
        room.setRoomPhone(roomPhone);
        room.setRoomDesc(roomDesc);
        room.setRoomSeat(roomSeat);
        room.setRoomAddress(roomAddress);
        room.setUserAccount(userAccount);
        boolean saveResult = this.save(room);
        if (!saveResult) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "自习室添加失败，数据库错误");
        }
        return room.getRoomId();
    }

    @Override
    public List<Room> roomList(String userAccount) {
        QueryWrapper<Room> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        List<Room> rooms = roomMapper.selectList(queryWrapper);
        return rooms;
    }

    @Override
    public Integer removeRoom(Integer roomId) {
        QueryWrapper<Room> roomQueryWrapper = new QueryWrapper<>();
        roomQueryWrapper.eq("roomId", roomId);
        if(roomMapper.selectCount(roomQueryWrapper) != 1){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"自习室不存在");
        }
        // 删除相关的会员信息
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
        memberQueryWrapper.eq("roomId", roomId);
        memberMapper.delete(memberQueryWrapper);
        Room room = new Room();
        room.setIsDelete(1);
        return roomMapper.delete(roomQueryWrapper);
    }
}
