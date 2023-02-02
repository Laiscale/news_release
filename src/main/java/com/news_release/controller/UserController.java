package com.news_release.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.news_release.common.Result;
import com.news_release.enity.Article;
import com.news_release.enity.ArticleComment;
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

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
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

    //用户点赞,用于显示一篇文章的点赞数（不懂是不是这个意思）
    @PostMapping("/userlike")
    public Result<?> userLike(@RequestParam String jokeId){
        QueryWrapper<ArticleLike> queryWrapper = new QueryWrapper();
        queryWrapper.eq("joke_id",jokeId);
        Integer count = articleLikeMapper.selectCount(queryWrapper);
        String ss = count.toString();
        return Result.success(ss);
    }

    //用户点赞列表展示（可输入用户的user_id，由于user表和articlelike表的userid差的远，做了各总展示）
    @GetMapping("/userlikelist")
    public Result<?> findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "5") Integer pageSize,
                              @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<ArticleLike> wrapper = Wrappers.<ArticleLike>lambdaQuery();
        if(StrUtil.isNotBlank(search)) {
            wrapper.like(ArticleLike::getJokeUserId, search);
        }
        IPage<ArticleLike> articleLikeIPage = articleLikeMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(articleLikeIPage);
    }



}
