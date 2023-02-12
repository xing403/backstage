package com.lxn.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.yulichang.base.MPJBaseService;
import com.lxn.backstage.model.entity.Room;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RoomService extends IService<Room> {
    /**
     *
     * @param roomName
     * @param roomPhone
     * @param roomSeat
     * @param roomAddress
     * @param roomDesc
     * @param userAccount
     * @return
     */
    Long roomRegister(String roomName, String roomPhone, Integer roomSeat,String roomAddress, String roomDesc, String userAccount);

    /**
     *
     * @param userAccount
     * @return
     */
    List<Room> roomList(String userAccount);

    Integer removeRoom(Integer roomId);

}
