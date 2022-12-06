package com.news_release.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.news_release.enity.Admin;
import com.news_release.enity.User;

import java.util.List;

public interface AdminService extends IService<Admin> {

    Admin loginResult(String name, String password);

//    List<User> userList();
}
