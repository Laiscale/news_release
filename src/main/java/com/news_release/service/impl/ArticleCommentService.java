package com.news_release.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news_release.enity.ArticleComment;
import com.news_release.mapper.ArticleCommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ArticleCommentService extends ServiceImpl<ArticleCommentMapper, ArticleComment> implements com.news_release.service.ArticleCommentService {

    @Autowired
    ArticleCommentMapper articleCommentMapper;
    @Override
    public Boolean postComment(String commentId, String jokeId, String userId, String commentDetail, Date time) {
        return true;
    }
}
