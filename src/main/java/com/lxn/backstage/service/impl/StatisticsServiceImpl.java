package com.lxn.backstage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.lxn.backstage.mapper.MemberMapper;
import com.lxn.backstage.mapper.RoomMapper;
import com.lxn.backstage.mapper.StatisticsMapper;
import com.lxn.backstage.mapper.UserMapper;
import com.lxn.backstage.model.entity.Member;
import com.lxn.backstage.model.unite.Statistics;
import com.lxn.backstage.service.StatisticsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service
public class StatisticsServiceImpl extends MPJBaseServiceImpl<MemberMapper, Member> implements StatisticsService {
    @Resource
    private MemberMapper memberMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private StatisticsMapper statisticsMapper;

    @Override
    public List<Statistics> roomsHasMember(String userAccount) {
        return statisticsMapper.roomsHasMember(userAccount);
    }

    @Override
    public List MemberStatus(String userAccount) {
        QueryWrapper<Member> memberQueryWrapper = new QueryWrapper<>();
//        memberQueryWrapper.eq()


        return null;
    }
}
