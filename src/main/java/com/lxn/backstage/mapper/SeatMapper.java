package com.lxn.backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxn.backstage.model.entity.Seat;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SeatMapper  extends BaseMapper<Seat> {
}
