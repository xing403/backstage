package com.lxn.backstage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lxn.backstage.model.entity.Seat;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public interface SeatService  extends IService<Seat>  {
    // 查询当前自习室座位信息
    List<Seat> selectSeatByRoomId(Long roomId);

    Boolean updateSeatStatusByRoomIdAndSeatId(Long roomId,Integer seatId,Integer seatStatus);

    Boolean insertSeat(String userAccount, Long roomId, Integer seatId, Integer seatStatus, Date time);


}
