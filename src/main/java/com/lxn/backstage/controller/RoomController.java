package com.lxn.backstage.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lxn.backstage.common.BaseResponse;
import com.lxn.backstage.common.ErrorCode;
import com.lxn.backstage.common.ResultUtils;
import com.lxn.backstage.exception.BusinessException;
import com.lxn.backstage.model.dto.RoomRequest;
import com.lxn.backstage.model.dto.RoomRegisterRequest;
import com.lxn.backstage.model.entity.Room;
import com.lxn.backstage.model.entity.User;
import com.lxn.backstage.service.RoomService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController

@RequestMapping("/room")
public class RoomController {
    @Resource
    private RoomService roomService;

    /**
     * 添加自习室
     *
     * @param roomRegisterRequest
     * @return
     */
    @PostMapping("/addition")
    public BaseResponse<Long> roomRegister(@RequestBody RoomRegisterRequest roomRegisterRequest) {
        if (roomRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String roomName = roomRegisterRequest.getRoomName();
        String roomPhone = roomRegisterRequest.getRoomPhone();
        Integer roomSeat = roomRegisterRequest.getRoomSeat();
        String roomDesc = roomRegisterRequest.getRoomDesc();
        String roomAddress = roomRegisterRequest.getRoomAddress();
        String userAccount = roomRegisterRequest.getUserAccount();
        if (StringUtils.isAnyBlank(userAccount, roomName, roomDesc, roomPhone)) {
            return null;
        }
        long result = roomService.roomRegister(roomName, roomPhone, roomSeat, roomAddress, roomDesc, userAccount);
        return ResultUtils.success(result, "添加成功");
    }

    /**
     * 查询自习室列表 仅指定商家
     *
     * @param user
     * @return
     */
    @PostMapping("/list")
    public BaseResponse<List<Room>> roomList(@RequestBody User user) {
        String userAccount = user.getUserAccount();
        if (StringUtils.isAnyBlank(userAccount)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        List<Room> rooms = roomService.roomList(userAccount);
        return ResultUtils.success(rooms, "");
    }

    /**
     * 一个自习室的详细信息
     *
     * @param room
     * @return
     */
    @PostMapping("/information")
    public BaseResponse<Room> roomInformation(@RequestBody Room room) {
        Long roomId = room.getRoomId();
        String userAccount = room.getUserAccount();
        System.out.println(room);
        if (roomId == null || StringUtils.isAllBlank(userAccount)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }
        QueryWrapper<Room> roomQueryWrapper = new QueryWrapper<>();
        roomQueryWrapper
                .eq("roomId", roomId)
                .eq("userAccount", userAccount);
        Room one = roomService.getOne(roomQueryWrapper);
        if (one == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "自习室不存在");
        }
        return ResultUtils.success(one, "已获取自习室信息");
    }

    /**
     * 移除一个自习室
     *
     * @param roomRequest
     * @return
     */
    @PostMapping("/remove")
    public BaseResponse<Integer> roomRemove(@RequestBody RoomRequest roomRequest) {
        System.out.println(roomRequest);
        Integer result = roomService.removeRoom(roomRequest.getRoomId());
        return ResultUtils.success(result, "删除成功");
    }

    /**
     * 编辑自习室
     * @param room
     * @return
     */
    @PostMapping("/edit")
    public BaseResponse<Boolean> roomEdit(@RequestBody Room room) {
        System.out.println(room);
        Long roomId = room.getRoomId();
        String userAccount = room.getUserAccount();
        String roomName = room.getRoomName();
        String roomAddress = room.getRoomAddress();
        String roomPhone = room.getRoomPhone();
        String roomDesc = room.getRoomDesc();
        Integer roomSeat = room.getRoomSeat();
        if (StringUtils.isAllBlank(userAccount, roomName, roomAddress, roomPhone, roomDesc) || roomSeat < 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数错误");
        }

        QueryWrapper<Room> roomQueryWrapper = new QueryWrapper<>();
        roomQueryWrapper
                .eq("roomId", roomId)
                .eq("userAccount", userAccount);
        Room one = roomService.getOne(roomQueryWrapper);
        if (one == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "自习室不存在");
        }

        Room r = new Room();
        r.setRoomAddress(roomAddress);
        r.setRoomName(roomName);
        r.setRoomSeat(roomSeat);
        r.setRoomPhone(roomPhone);
        r.setRoomDesc(roomDesc);
        boolean update = roomService.update(r, roomQueryWrapper);

        return ResultUtils.success(update, "更新成功");

    }
}
