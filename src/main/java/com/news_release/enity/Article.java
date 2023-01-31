package com.news_release.enity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
@TableName("article")
public class Article {

    private int id;
    private String jokeId;
    private String jokeUserId;
    private String title;
    private String content;
    private int status;
    private String contentHtml;
    private String coverImg;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date postTime;

    private int artLikeCount;
    private int artCommentCount;
    private String category;
    private String tags;

//    private String jokeUserNick;
//    private String jokeUserIcon;
//
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//    private Date selfApprovalTime;
}
