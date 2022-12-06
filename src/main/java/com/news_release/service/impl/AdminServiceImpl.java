package com.news_release.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news_release.enity.Admin;
import com.news_release.enity.User;
import com.news_release.mapper.AdminMapper;
import com.news_release.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;


    @Override
    public Admin loginResult(String name, String password) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(name != null, Admin::getName, name)
                .eq(password != null, Admin::getPassword, password);
        return getOne(wrapper);
    }

//    @Override
//    public List<User> userList() {
//        IPage<User> iPage = new Page<>(1, 5);
//        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
//        wrapper.like("name",1);
//    }
}
