package com.news_release.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.news_release.enity.User;

import javax.servlet.http.HttpSession;

public interface UserService extends IService<User> {

    User findUser(HttpSession httpSession);
}
