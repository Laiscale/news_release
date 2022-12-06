package com.news_release.enity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("article_comment")
public class ArticleComment {
    private String commentId;
    private String jokeId;
    private String commentUserId;
    private String commentDetails;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date commentDate;

    private String commentNick;
    private String commentIcon;
}
