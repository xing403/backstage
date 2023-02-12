package com.lxn.backstage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lxn.backstage.model.entity.Room;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoomMapper extends BaseMapper<Room> {
}
