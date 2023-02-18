package com.news_release.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news_release.enity.Article;
import com.news_release.mapper.ArticleMapper;
import com.news_release.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Override
    public IPage<Article> findAllByStatus(Integer pageNum, Integer pageSize, String search) {
        IPage<Article> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, 1);
        List<Article> uncheckarticle = articleMapper.selectList(queryWrapper);
        System.out.println(uncheckarticle);
        page.setRecords(uncheckarticle);
        queryWrapper.like(search != null, Article::getTitle, search);
        IPage<Article> PageStatus = articleMapper.selectPage(page, queryWrapper);
        return PageStatus;
    }

    @Override
    public IPage<Article> findPerByStatus(Integer pageNum, Integer pageSize, String search) {
        IPage<Article> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, 3);
        List<Article> checkarticle = articleMapper.selectList(queryWrapper);
        System.out.println(checkarticle);
        page.setRecords(checkarticle);
        queryWrapper.like(search != null, Article::getTitle, search);
        IPage<Article> PageStatuss = articleMapper.selectPage(page, queryWrapper);
        return PageStatuss;
    }

    @Override
    public IPage<Article> findDnyByStatus(Integer pageNum, Integer pageSize, String search) {
        IPage<Article> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getStatus, 2);
        List<Article> nocheckarticle = articleMapper.selectList(queryWrapper);
        System.out.println(nocheckarticle);
        page.setRecords(nocheckarticle);
        queryWrapper.like(search != null, Article::getTitle, search);
        IPage<Article> PageStatu = articleMapper.selectPage(page, queryWrapper);
        return PageStatu;
    }
}
