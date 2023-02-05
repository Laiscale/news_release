package com.news_release.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news_release.enity.Admin;
import com.news_release.enity.Article;
import com.news_release.enity.ArticleComment;
import com.news_release.enity.User;
import com.news_release.mapper.AdminMapper;
import com.news_release.mapper.ArticleCommentMapper;
import com.news_release.mapper.ArticleMapper;
import com.news_release.mapper.UserMapper;
import com.news_release.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    ArticleCommentMapper articleCommentMapper;


    @Override
    public Admin loginResult(String name, String password) {
        LambdaQueryWrapper<Admin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(name != null, Admin::getName, name)
                .eq(password != null, Admin::getPassword, password);
        return getOne(wrapper);
    }

    @Override
    public int deleteUser(String user_id) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUserId, user_id);
        return userMapper.delete(wrapper);
    }

    @Override
    public int deleteUserList(List<String> user_ids) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(User::getUserId, user_ids);
        return userMapper.delete(wrapper);
    }

    @Override
    public int deleteArticle(String joke_id) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Article::getJokeId, joke_id);
        return articleMapper.delete(wrapper);
    }

    @Override
    public int deleteArticleList(List<String> joke_ids) {
        LambdaQueryWrapper<Article> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Article::getJokeId, joke_ids);
        return articleMapper.delete(wrapper);
    }

    @Override
    public int deleteComment(String joke_id) {
        LambdaQueryWrapper<ArticleComment> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ArticleComment::getJokeId, joke_id);
        return articleCommentMapper.delete(wrapper);
    }

    @Override
    public int deleteCommentList(List<String> comment_ids) {
        LambdaQueryWrapper<ArticleComment> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(ArticleComment::getCommentId, comment_ids);
        return articleCommentMapper.delete(wrapper);
    }


}
