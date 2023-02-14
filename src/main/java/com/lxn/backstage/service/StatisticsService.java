package com.lxn.backstage.service;

import com.github.yulichang.base.MPJBaseService;
import com.lxn.backstage.model.entity.Member;

import java.util.HashMap;
import java.util.List;

public interface StatisticsService  extends MPJBaseService<Member> {
    /**
     * 统计每个在自习室有多少会员
     * @param userAccount
     * @return
     */
    List roomsHasMember(String userAccount);
}

