package com.news_release.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news_release.enity.User;
import com.news_release.mapper.*;
import com.news_release.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleCommentMapper articleCommentMapper;
    @Autowired
    ArticleLikeMapper articleLikeMapper;

    @Override
    public User findUser(HttpSession httpSession) {
        return null;
    }
}
