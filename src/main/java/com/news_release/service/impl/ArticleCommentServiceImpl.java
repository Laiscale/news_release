package com.news_release.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news_release.enity.Article;
import com.news_release.enity.ArticleComment;
import com.news_release.mapper.ArticleCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.news_release.service.ArticleCommentService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleCommentServiceImpl extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements ArticleCommentService {

    @Autowired
    ArticleCommentMapper articleCommentMapper;
//    @Override
//    public IPage<ArticleComment> findAllByStatus(Integer pageNum, Integer pageSize, String search) {
//        IPage<ArticleComment> page = new Page<>(pageNum, pageSize);
//        LambdaQueryWrapper<ArticleComment> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(ArticleComment::getStatus, 1);
//        List<ArticleComment> uncheckarticle = ArticleCommentMapper.selectList(queryWrapper);
//        System.out.println(uncheckarticle);
//        page.setRecords(uncheckarticle);
//        queryWrapper.like(search != null, Article::getTitle, search);
//        IPage<Article> PageStatus = ArticleCommentMapper.selectPage(page, queryWrapper);
//        return PageStatus;
//    }
}
