package com.lxn.backstage.config;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.github.yulichang.injector.MPJSqlInjector;
import com.github.yulichang.method.*;

import java.util.List;

public class MySqlInjector extends MPJSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass) {
        //将原来的保持
        List<AbstractMethod> methodList = super.getMethodList(mapperClass);
        //多表查询sql注入 从连表插件里移植过来的
        methodList.add(new SelectJoinOne());
        methodList.add(new SelectJoinList());
        methodList.add(new SelectJoinPage());
        methodList.add(new SelectJoinMap());
        methodList.add(new SelectJoinMaps());
        methodList.add(new SelectJoinMapsPage());
        return methodList;
    }
}