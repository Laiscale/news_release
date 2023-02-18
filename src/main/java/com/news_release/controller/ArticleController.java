package com.news_release.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
import com.news_release.service.ArticleLikeService;
import com.news_release.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Slf4j
@RestController
@CrossOrigin
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
    @Autowired
    ArticleLikeService articleLikeService;

    //文章详情列表-单篇展示
    @GetMapping("/jokedetail")
    public Result<?> jokedetail(@RequestParam String jokeid){
        LambdaQueryWrapper<Article> wrapper = Wrappers.lambdaQuery();
        wrapper.like(Article::getJokeId,jokeid);
        List<Article> articles = articleMapper.selectList(wrapper);
        return Result.success(articles);
    }

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
    public Result<?> releaseArticles(Article article){
        article.setPostTime(LocalDateTime.now());
        article.setStatus(1);
        Random jokeId = new Random(123456);
        article.setJokeId(Objects.toString(jokeId.nextInt()));
        articleService.save(article);
        return Result.success("发布成功");
    }

    @PostMapping("/addlike")
    public  Result<?> addLike(@RequestParam String jokeid,  @RequestParam String jokeuserid){
        ArticleLike articleLike = new ArticleLike();
        articleLike.setJokeId(jokeid);
        articleLike.setJokeUserId(jokeuserid);
        articleLike.setApprovalTime(LocalDateTime.now());
        articleLikeService.save(articleLike);
        UpdateWrapper<Article> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("joke_id",jokeid);
        updateWrapper.setSql("art_like_count=art_like_count+"+1);
        articleMapper.update(null,updateWrapper);

        QueryWrapper<ArticleLike> queryWrapper = new QueryWrapper();
        queryWrapper.eq("joke_id", jokeid);
        Integer count = articleLikeMapper.selectCount(queryWrapper);
        String ss = count.toString();
        return Result.success(ss);
    }

    // 按文章类型查询文章
    @GetMapping("/searchWithCategory")
    @PreAuthorize("hasRole('user')")
    public  Result<?> searchWithCategory(@RequestParam String category){
        List<Article> article = articleMapper.selectArticleWithCategory(category);
        return Result.success(article);
    }
}


