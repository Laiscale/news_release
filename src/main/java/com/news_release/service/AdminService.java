package com.news_release.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.news_release.enity.Admin;
import com.news_release.enity.Article;

import java.util.List;

public interface AdminService extends IService<Admin> {

    Admin loginResult(String name, String password);
    int deleteUser(String user_id);
    int deleteUserList(List<String> user_ids);
    int deleteArticle(String joke_id);
    int deleteArticleList(List<String> joke_ids);
    int deleteComment(String joke_id);
    int deleteCommentList(List<String> comment_ids);

}
