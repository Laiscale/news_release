package com.news_release.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.news_release.enity.Article;

public interface ArticleService extends IService<Article> {
    IPage<Article> findAllByStatus(Integer pageNum, Integer pageSize, String search);
}
