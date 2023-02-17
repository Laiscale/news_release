package com.news_release.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.news_release.common.Result;
import com.news_release.enity.Article;
import com.news_release.enity.ArticleLike;
import com.news_release.enity.User;
import com.news_release.mapper.ArticleCommentMapper;
import com.news_release.mapper.ArticleLikeMapper;
import com.news_release.mapper.ArticleMapper;
import com.news_release.mapper.UserMapper;
import com.news_release.service.AdminService;
import com.news_release.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {
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
    ArticleLikeMapper articleLikeMapper;

    //文章详情列表展示
    @GetMapping("/jokedetaillist")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "5") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<Article> wrapper = Wrappers.<Article>lambdaQuery();
        if(StrUtil.isNotBlank(search)) {
            wrapper.like(Article::getJokeId, search);
        }
        IPage<Article> articleDetailIPage = articleMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(articleDetailIPage);
    }

    //首页搜索功能，搜索结果可以按需修改
    @GetMapping("/search")
    public Result<?> findArticles(@RequestParam String words){
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(Article::getTitle,words);
        List<Article> articles = articleMapper.selectList(wrapper);
        return Result.success(articles);
    }

    //文章发布
    @PostMapping("/release")
    public Result<?> releaseArticles(@RequestBody Article article){
        article.setPostTime(LocalDateTime.now());
        article.setStatus(1);
        articleService.save(article);
        return Result.success("发布成功");
    }

    //首页新闻列表
    @GetMapping("/getnews")
    public Result<?> getnews(){
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getStatus,3);
        List<Article> articles = articleMapper.selectList(wrapper);
        return Result.success(articles);
    }
}


