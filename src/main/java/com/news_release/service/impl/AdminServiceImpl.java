package com.news_release.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news_release.config.Result;
import com.news_release.enity.Admin;
import com.news_release.mapper.AdminMapper;
import com.news_release.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;


    @Override
    public Admin getAdminByName(String name, String password) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(name != null, Admin::getName, name).eq(password != null, Admin::getPassword, password);
        return getOne(wrapper);
    }
}
