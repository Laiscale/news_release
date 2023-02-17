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
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@CrossOrigin
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

    @PostMapping("/commentAdd")
    public Result<?> addComment(ArticleComment articleComment){
            Date date = new Date();
            articleComment.setCommentDate(date);
            articleComment.setCommentId("2");
            articleCommentMapper.insert(articleComment);
            return Result.success(articleComment);
    }
}
