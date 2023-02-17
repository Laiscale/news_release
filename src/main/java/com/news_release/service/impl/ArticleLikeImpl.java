package com.news_release.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news_release.enity.ArticleLike;
import com.news_release.enity.User;
import com.news_release.mapper.ArticleLikeMapper;
import com.news_release.mapper.UserMapper;
import com.news_release.service.ArticleLikeService;
import com.news_release.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class ArticleLikeImpl extends ServiceImpl<ArticleLikeMapper, ArticleLike> implements ArticleLikeService {
}
