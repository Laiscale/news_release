package com.news_release.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.news_release.common.Result;
import com.news_release.enity.ArticleComment;
import com.news_release.mapper.ArticleCommentMapper;
import com.news_release.mapper.ArticleLikeMapper;
import com.news_release.mapper.ArticleMapper;
import com.news_release.mapper.UserMapper;
import com.news_release.service.AdminService;
import com.news_release.service.ArticleCommentService;
import com.news_release.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleCommentController {
    @Autowired
    AdminService adminService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleService articleService;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleCommentMapper articleCommentMapper;
    @Autowired
    ArticleCommentService articleCommentService;
    @Autowired
    ArticleLikeMapper articleLikeMapper;

    @PostMapping("/comment")
    public Result<?> addComment(ArticleComment articleComment){
            Date date = new Date();
            articleComment.setCommentDate(date);
            articleCommentMapper.insert(articleComment);
            return Result.success(articleComment);
    }
}
