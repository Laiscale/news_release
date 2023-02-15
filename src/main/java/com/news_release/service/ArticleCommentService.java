package com.news_release.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.news_release.enity.ArticleComment;

import java.util.Date;

public interface ArticleCommentService {
    Boolean postComment(String commentId, String jokeId, String userId, String commentDetail, Date time);
}
